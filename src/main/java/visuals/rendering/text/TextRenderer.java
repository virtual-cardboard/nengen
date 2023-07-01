package visuals.rendering.text;

import static common.colour.Colour.toRangedVector;

import java.util.ArrayList;
import java.util.List;

import common.colour.Colour;
import common.math.Matrix4f;
import common.math.Vector2f;
import common.math.Vector4f;
import visuals.builtin.RectangleVertexArrayObject;
import visuals.builtin.TextFragmentShader;
import visuals.builtin.TexturedTransformationVertexShader;
import visuals.lwjgl.GLContext;
import visuals.lwjgl.render.FragmentShader;
import visuals.lwjgl.render.FrameBufferObject;
import visuals.lwjgl.render.ShaderProgram;
import visuals.lwjgl.render.Texture;
import visuals.lwjgl.render.VertexArrayObject;
import visuals.lwjgl.render.VertexShader;
import visuals.lwjgl.render.shader.ShaderUniformInputList;
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

	private final GLContext glContext;

	private int hAlign = ALIGN_LEFT;
	private int vAlign = ALIGN_TOP;

	/**
	 * Creates a TextRenderer
	 *
	 * @param shaderProgram the <code>TextShaderProgram</code>
	 * @param vao           the <code>RectangleVertexArrayObject</code>
	 */
	public TextRenderer(GLContext glContext, TextureRenderer textureRenderer, ShaderProgram shaderProgram, VertexArrayObject vao) {
		this.glContext = glContext;
		this.textureRenderer = textureRenderer;
		this.shaderProgram = shaderProgram;
		this.vao = vao;
	}

	public TextRenderer(GLContext glContext) {
		this.textureRenderer = new TextureRenderer(glContext);
		this.glContext = glContext;
		this.vao = RectangleVertexArrayObject.instance();
		VertexShader vertex = TexturedTransformationVertexShader.instance();
		FragmentShader fragment = TextFragmentShader.instance();
		this.shaderProgram = new ShaderProgram().attach(vertex, fragment).load();
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
	private int render(Matrix4f transform, String text, float lineWidth, GameFont font, float fontSize, int colour) {
		shaderProgram.use(glContext);
		shaderProgram.set("transform", transform);
		shaderProgram.set("textureSampler", 0);
		shaderProgram.set("fill", toRangedVector(colour));
//		ShaderUniformInputList list =
//				shaderProgram.uniforms()
//						.set("textureSampler", 0)
//						.set("texWidth", font.texture().width())
//						.set("texHeight", font.texture().height())
//						.set("fill", toRangedVector(colour));
//				.complete();

		float totalXOffset = 0;
		float totalYOffset = 0;
		for (int i = 0, m = text.length(); i < m; i++) {
			char c = text.charAt(i);
			CharacterData data = font.getCharacterDatas()[c];
			shaderProgram.set("atlas[" + i + "]", new Vector4f(data.x(), data.y(), data.width(), data.height()));
			shaderProgram.set("offset[" + i + "]", new Vector2f(totalXOffset + data.xOffset(), totalYOffset + data.yOffset()));
			totalXOffset += data.xAdvance();
			if (totalXOffset > lineWidth) {
				totalXOffset = 0;
				totalYOffset += fontSize;
			}
		}
		vao.drawInstanced(glContext, text.length());

//		int numLines;
//		if (lineWidth == 0) {
//			renderOneLine(0, 0, text, font, fontSize);
//			numLines = 1;
//		} else {
//			List<StringRenderWidth> stringPairs = convertToStringPairs(text, font, fontSize, lineWidth);
//			numLines = stringPairs.size();
//			if (hAlign == ALIGN_LEFT) {
//				for (int i = 0; i < stringPairs.size(); i++) {
//					StringRenderWidth pair = stringPairs.get(i);
//					renderOneLine(0, i * fontSize, pair.string, font, fontSize);
//				}
//			} else if (hAlign == ALIGN_CENTER) {
//				for (int i = 0; i < stringPairs.size(); i++) {
//					StringRenderWidth pair = stringPairs.get(i);
//					renderOneLine((lineWidth - pair.width) * 0.5f, i * fontSize, pair.string, font, fontSize);
//				}
//			} else if (hAlign == ALIGN_RIGHT) {
//				for (int i = 0; i < stringPairs.size(); i++) {
//					StringRenderWidth pair = stringPairs.get(i);
//					renderOneLine(lineWidth - pair.width, i * fontSize, pair.string, font, fontSize);
//				}
//			}
//		}
//		FrameBufferObject.unbind();
//		Texture tex = fbo.texture();
//		Matrix4f m = new Matrix4f()
//				.translate(-1, 1).scale(2 / glContext.width(), -2 / glContext.height())
//				.multiply(transform);
//		if (vAlign == ALIGN_CENTER) {
//			m = m.translate(0, -numLines * fontSize / 2);
//		} else if (vAlign == ALIGN_BOTTOM) {
//			m = m.translate(0, -numLines * fontSize);
//		}
//		m = m.scale(tex.width(), tex.height());
//		textureRenderer.render(tex, m);
//		DefaultFrameBuffer.instance().bind();
//		return numLines;
		return 0;
	}

//	private void renderOneLine(float xOffset, float yOffset, String text, GameFont font, float fontSize) {
//		char[] chars = text.toCharArray();
//		Matrix4f transform = new Matrix4f().translate(-1, -1).scale(2, 2).scale(1 / glContext.width(), 1 / glContext.height());
//		float sizeMultiplier = fontSize / font.getFontSize();
//
//		CharacterData[] characterDataArray = font.getCharacterDatas();
//		for (char ch : chars) {
//			CharacterData c = characterDataArray[ch];
//			int xAdvance = c.xAdvance();
//			if (ch == ' ' || ch == '\t') {
//				xOffset += xAdvance * sizeMultiplier;
//				continue;
//			}
//			Matrix4f copy = transform.copy()
//					.translate(xOffset + c.xOffset() * sizeMultiplier, yOffset + c.yOffset() * sizeMultiplier)
//					.scale(c.width() * sizeMultiplier, c.height() * sizeMultiplier);
//			shaderProgram.uniforms()
//					.set("matrix4f", copy)
//					.set("width", c.width())
//					.set("height", c.height())
//					.set("x", c.x())
//					.set("y", c.y())
//					.complete();
//			vao.draw(glContext);
//			xOffset += xAdvance * sizeMultiplier;
//		}
//	}
//
//	public int calculateNumLines(String text, GameFont font, float fontSize, float lineWidth) {
//		return convertToStringPairs(text, font, fontSize, lineWidth).size();
//	}
//
//	private List<StringRenderWidth> convertToStringPairs(String text, GameFont font, float fontSize, float lineWidth) {
//		String[] splitByNewLines = text.split("\\n");
//		List<StringRenderWidth> stringPairs = new ArrayList<>();
//		for (String line : splitByNewLines) {
//			stringPairs.addAll(convertParagraphToStringPairs(line, font, fontSize, lineWidth));
//		}
//		return stringPairs;
//	}
//
//	private List<StringRenderWidth> convertParagraphToStringPairs(String text, GameFont font, float fontSize, float lineWidth) {
//		CharacterData[] characterDatas = font.getCharacterDatas();
//
//		List<StringRenderWidth> pairs = new ArrayList<>();
//
//		String[] words = text.split(" ");
//		float sizeMultiplier = fontSize / font.getFontSize();
//
//		float currentStringWidth = 0;
//		StringBuilder currentString = new StringBuilder();
//		for (String word : words) {
//			float wordWidth = 0;
//			for (int j = 0; j < word.length(); j++) {
//				wordWidth += characterDatas[word.charAt(j)].xAdvance() * sizeMultiplier;
//			}
//
//			if (currentStringWidth + wordWidth <= lineWidth) {
//				if (currentString.length() > 0) {
//					currentString.append(" ");
//					currentStringWidth += characterDatas[' '].xAdvance() * sizeMultiplier;
//				}
//				currentStringWidth += wordWidth;
//				currentString.append(word);
//			} else {
//				pairs.add(new StringRenderWidth(currentString.toString(), currentStringWidth));
//				currentStringWidth = wordWidth;
//				currentString = new StringBuilder(word);
//			}
//		}
//		pairs.add(new StringRenderWidth(currentString.toString(), currentStringWidth));
//		return pairs;
//	}

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
