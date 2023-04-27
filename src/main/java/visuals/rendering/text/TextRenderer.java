package visuals.rendering.text;

import static common.colour.Colour.toRangedVector;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;

import java.util.ArrayList;
import java.util.List;

import common.colour.Colour;
import common.math.Matrix4f;
import visuals.builtin.RectangleVertexArrayObject;
import visuals.builtin.TextFragmentShader;
import visuals.builtin.TexturedTransformationVertexShader;
import visuals.lwjgl.GLContext;
import visuals.lwjgl.render.FrameBufferObject;
import visuals.lwjgl.render.Shader;
import visuals.lwjgl.render.ShaderProgram;
import visuals.lwjgl.render.Texture;
import visuals.lwjgl.render.VertexArrayObject;
import visuals.rendering.texture.TextureRenderer;

/**
 * @author Jay
 */
public class TextRenderer {

	private static final int ALIGN_LEFT = 0;
	private static final int ALIGN_RIGHT = 1;
	private static final int ALIGN_TOP = 2;
	private static final int ALIGN_BOTTOM = 3;
	private static final int ALIGN_CENTER = 4;

	private final TextureRenderer textureRenderer;
	/**
	 * The {@link ShaderProgram} to use when rendering text.
	 */
	private final ShaderProgram shaderProgram;

	/**
	 * The {@link VertexArrayObject} to use when rendering text. It is a rectangle.
	 */
	private final VertexArrayObject vao;
	private final FrameBufferObject fbo;

	private final GLContext glContext;

	private int hAlign = ALIGN_LEFT;
	private int vAlign = ALIGN_TOP;

	/**
	 * Creates a TextRenderer
	 *
	 * @param shaderProgram the <code>TextShaderProgram</code>
	 * @param vao           the <code>RectangleVertexArrayObject</code>
	 * @param fbo           the <code>FrameBufferObject</code>
	 */
	public TextRenderer(GLContext glContext, TextureRenderer textureRenderer, ShaderProgram shaderProgram, VertexArrayObject vao, FrameBufferObject fbo) {
		this.glContext = glContext;
		this.textureRenderer = textureRenderer;
		this.shaderProgram = shaderProgram;
		this.vao = vao;
		this.fbo = fbo;
	}

	public TextRenderer(GLContext glContext) {
		this.textureRenderer = new TextureRenderer(glContext);
		this.glContext = glContext;
		this.vao = RectangleVertexArrayObject.instance();
		Shader vertex = TexturedTransformationVertexShader.instance();
		Shader fragment = TextFragmentShader.instance();
		this.shaderProgram = new ShaderProgram().attach(vertex, fragment).load();
		Texture texture = new Texture().load();
		this.fbo = new FrameBufferObject().texture(texture).load();
	}

	/**
	 * Renders text.
	 *
	 * @param x         the <code>x</code> offset of the text from the left side of the screen
	 * @param y         the <code>y</code> offset of the text from the top of the screen
	 * @param text      the {@link String} to display
	 * @param lineWidth the max width of each line of text in pixels, or 0 to indicate no wrapping
	 * @param font      the {@link GameFont} of the text
	 * @param fontSize  the size of the text
	 * @param colour    the colour of the text
	 * @return the number of lines of text rendered
	 */
	public int render(float x, float y, String text, float lineWidth, GameFont font, float fontSize, int colour) {
		return render(new Matrix4f().translate(x, y), text, lineWidth, font, fontSize, colour);
	}

	/**
	 * Renders text.
	 *
	 * @param transform the transformation matrix to be applied to the text at the end
	 * @param text      the text
	 * @param lineWidth the max width of each line of text in pixels, or 0 to indicate no wrapping
	 * @param font      the <code>GameFont</code> of the text
	 * @param fontSize  the font size
	 * @param colour    the {@link Colour} (int)
	 * @return the number of lines of text rendered
	 */
	public int render(Matrix4f transform, String text, float lineWidth, GameFont font, float fontSize, int colour) {
		fbo.bind();
		font.texture().bind();
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		shaderProgram.use();
		shaderProgram.set("textureSampler", 0);
		shaderProgram.set("texWidth", font.texture().width());
		shaderProgram.set("texHeight", font.texture().height());
		shaderProgram.set("fill", toRangedVector(colour));

		int numLines;
		if (lineWidth == 0) {
			renderOneLine(0, 0, text, font, fontSize);
			numLines = 1;
		} else {
			List<StringRenderWidth> stringPairs = convertToStringPairs(text, font, fontSize, lineWidth);
			numLines = stringPairs.size();
			if (hAlign == ALIGN_LEFT) {
				for (int i = 0; i < stringPairs.size(); i++) {
					StringRenderWidth pair = stringPairs.get(i);
					renderOneLine(0, i * fontSize, pair.string, font, fontSize);
				}
			} else if (hAlign == ALIGN_CENTER) {
				for (int i = 0; i < stringPairs.size(); i++) {
					StringRenderWidth pair = stringPairs.get(i);
					renderOneLine((lineWidth - pair.width) * 0.5f, i * fontSize, pair.string, font, fontSize);
				}
			} else if (hAlign == ALIGN_RIGHT) {
				for (int i = 0; i < stringPairs.size(); i++) {
					StringRenderWidth pair = stringPairs.get(i);
					renderOneLine(lineWidth - pair.width, i * fontSize, pair.string, font, fontSize);
				}
			}
		}
		FrameBufferObject.unbind();
		Texture tex = fbo.texture();
		Matrix4f m = new Matrix4f()
				.translate(-1, 1).scale(2 / glContext.width(), -2 / glContext.height())
				.multiply(transform);
		if (vAlign == ALIGN_CENTER) {
			m = m.translate(0, -numLines * fontSize / 2);
		} else if (vAlign == ALIGN_BOTTOM) {
			m = m.translate(0, -numLines * fontSize);
		}
		m = m.scale(tex.width(), tex.height());
		textureRenderer.render(tex, m);
		return numLines;
	}

	private void renderOneLine(float xOffset, float yOffset, String text, GameFont font, float fontSize) {
		char[] chars = text.toCharArray();
		Matrix4f transform = new Matrix4f().translate(-1, -1).scale(2, 2).scale(1 / glContext.width(), 1 / glContext.height());
		float sizeMultiplier = fontSize / font.getFontSize();
		fbo.bind(glContext);

		CharacterData[] characterDataArray = font.getCharacterDatas();
		for (char ch : chars) {
			CharacterData c = characterDataArray[ch];
			short xAdvance = c.xAdvance();
			if (ch == ' ' || ch == '\t') {
				xOffset += xAdvance * sizeMultiplier;
				continue;
			}
			Matrix4f copy = transform.copy()
					.translate(xOffset + c.xOffset() * sizeMultiplier, yOffset + c.yOffset() * sizeMultiplier)
					.scale(c.width() * sizeMultiplier, c.height() * sizeMultiplier);
			shaderProgram.set("matrix4f", copy);
			shaderProgram.set("width", c.width());
			shaderProgram.set("height", c.height());
			shaderProgram.set("x", c.x());
			shaderProgram.set("y", c.y());
			vao.draw(glContext);
			xOffset += xAdvance * sizeMultiplier;
		}
	}

	public int calculateNumLines(String text, GameFont font, float fontSize, float lineWidth) {
		return convertToStringPairs(text, font, fontSize, lineWidth).size();
	}

	private List<StringRenderWidth> convertToStringPairs(String text, GameFont font, float fontSize, float lineWidth) {
		String[] splitByNewLines = text.split("\\n");
		List<StringRenderWidth> stringPairs = new ArrayList<>();
		for (String line : splitByNewLines) {
			stringPairs.addAll(convertParagraphToStringPairs(line, font, fontSize, lineWidth));
		}
		return stringPairs;
	}

	private List<StringRenderWidth> convertParagraphToStringPairs(String text, GameFont font, float fontSize, float lineWidth) {
		CharacterData[] characterDatas = font.getCharacterDatas();

		List<StringRenderWidth> pairs = new ArrayList<>();

		String[] words = text.split(" ");
		float sizeMultiplier = fontSize / font.getFontSize();

		float currentStringWidth = 0;
		StringBuilder currentString = new StringBuilder();
		for (String word : words) {
			float wordWidth = 0;
			for (int j = 0; j < word.length(); j++) {
				wordWidth += characterDatas[word.charAt(j)].xAdvance() * sizeMultiplier;
			}

			if (currentStringWidth + wordWidth <= lineWidth) {
				if (currentString.length() > 0) {
					currentString.append(" ");
					currentStringWidth += characterDatas[' '].xAdvance() * sizeMultiplier;
				}
				currentStringWidth += wordWidth;
				currentString.append(word);
			} else {
				pairs.add(new StringRenderWidth(currentString.toString(), currentStringWidth));
				currentStringWidth = wordWidth;
				currentString = new StringBuilder(word);
			}
		}
		pairs.add(new StringRenderWidth(currentString.toString(), currentStringWidth));
		return pairs;
	}

	public void alignLeft() {
		hAlign = ALIGN_LEFT;
	}

	public void alignCenterHorizontal() {
		hAlign = ALIGN_CENTER;
	}

	public void alignRight() {
		hAlign = ALIGN_RIGHT;
	}

	public void alignTop() {
		vAlign = ALIGN_TOP;
	}

	public void alignCenterVertical() {
		vAlign = ALIGN_CENTER;
	}

	public void alignBottom() {
		vAlign = ALIGN_BOTTOM;
	}

	public int hAlign() {
		return hAlign;
	}

	public int vAlign() {
		return vAlign;
	}

}
