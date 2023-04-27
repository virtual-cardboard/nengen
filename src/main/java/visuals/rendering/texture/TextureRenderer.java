package visuals.rendering.texture;

import common.colour.Colour;
import common.math.Matrix4f;
import visuals.builtin.RectangleVertexArrayObject;
import visuals.builtin.TextureFragmentShader;
import visuals.builtin.TexturedTransformationVertexShader;
import visuals.lwjgl.GLContext;
import visuals.lwjgl.render.Shader;
import visuals.lwjgl.render.ShaderProgram;
import visuals.lwjgl.render.Texture;
import visuals.lwjgl.render.VertexArrayObject;

/**
 * @author Jay
 */
public class TextureRenderer {

	/**
	 * The {@link ShaderProgram} to use when rendering textures.
	 */
	private final ShaderProgram program;
	private final VertexArrayObject vao;

	private final GLContext glContext;

	private int diffuse = -1;

	public TextureRenderer(GLContext glContext) {
		this.glContext = glContext;
		Shader vertex = TexturedTransformationVertexShader.instance();
		Shader fragment = TextureFragmentShader.instance();
		this.program = new ShaderProgram().attach(vertex, fragment).load();
		this.vao = RectangleVertexArrayObject.instance();
	}

	/**
	 * Renders a texture with default proportions in pixel coordinates.
	 *
	 * @param texture the {@link Texture} to render
	 * @param centerX the x position in pixels of the center of the texture
	 * @param centerY the y position in pixels of the center of the texture
	 * @param scale   the scale of the texture
	 */
	public void render(Texture texture, float centerX, float centerY, float scale) {
		renderDepth(texture, centerX, centerY, 0f, scale);
	}

	/**
	 * Renders a texture with default proportions in pixel coordinates.
	 *
	 * @param texture the {@link Texture} to render
	 * @param centerX the x position in pixels of the center of the texture
	 * @param centerY the y position in pixels of the center of the texture
	 * @param depth   the depth of the texture
	 * @param scale   the scale of the texture
	 */
	public void renderDepth(Texture texture, float centerX, float centerY, float depth, float scale) {
		Matrix4f matrix4f = new Matrix4f()
				.translate(-1, 1, depth)
				.scale(2, -2)
				.scale(1 / glContext.width(), 1 / glContext.height())
				.translate(centerX, centerY)
				.scale(texture.width() * scale, texture.height() * scale)
				.translate(-0.5f, -0.5f);
		render(texture, matrix4f);
	}

	/**
	 * Renders a texture in pixel coordinates.
	 *
	 * @param texture the {@link Texture} to render
	 * @param x       the x position in pixels of the top left corner of the texture
	 * @param y       the y position in pixels of the top left corner of the texture
	 * @param w       the width in pixels
	 * @param h       the height in pixels
	 */
	public void render(Texture texture, float x, float y, float w, float h) {
		Matrix4f matrix4f = new Matrix4f()
				.translate(-1, 1)
				.scale(2, -2)
				.scale(1 / glContext.width(), 1 / glContext.height())
				.translate(x, y).scale(w, h);
		render(texture, matrix4f);
	}

	/**
	 * Renders a texture using a transformation matrix.
	 *
	 * @param texture  the texture to render
	 * @param matrix4f the transformation matrix
	 */
	public void render(Texture texture, Matrix4f matrix4f) {
		program.use(glContext);
		texture.bind(glContext, 0);
		program.set("matrix4f", matrix4f);
		program.set("textureSampler", 0);
		program.set("diffuse", Colour.toRangedVector(diffuse));
		vao.draw(glContext);
	}

	public int getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(int diffuse) {
		this.diffuse = diffuse;
	}

	public void resetDiffuse() {
		diffuse = -1;
	}

}
