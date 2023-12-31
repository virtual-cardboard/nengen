package visuals.rendering.texture;

import static common.colour.Colour.rgb;

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

	private int diffuse = rgb(255, 255, 255);

	public TextureRenderer(GLContext glContext) {
		this.glContext = glContext;
		Shader vertex = TexturedTransformationVertexShader.instance();
		Shader fragment = TextureFragmentShader.instance();
		this.program = new ShaderProgram().attach(vertex, fragment).load();
		this.vao = RectangleVertexArrayObject.instance();
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
		// By default, the rectangle VAO is positioned at (0, 0) in normalized device coordinates with the other corner
		// at (1, 1) which is the top right corner.
		// This matrix does the following:
		//     1. translates the rectangle to (-1, 1) top left corner with other corner at (0, 2) off the screen.
		//     2. scales it so the (0, 2) corner goes to (1, -1), the rectangle now covers the entire screen.
		//     3. scales it so the rectangle is one pixel in size.
		//     4. translates it to the correct position.
		//     5. scales it to the correct size.
		Matrix4f matrix4f = new Matrix4f()
				.translate(-1, 1)
				.scale(2, -2)
				.scale(1 / glContext.width(), 1 / glContext.height())
				.translate(x, y)
				.scale(w, h);
		render(texture, matrix4f);
	}

	/**
	 * Renders a texture using a transformation matrix.
	 *
	 * @param texture  the texture to render
	 * @param matrix4f the transformation matrix
	 */
	private void render(Texture texture, Matrix4f matrix4f) {
		program.use(glContext);
		texture.bind(glContext, 0);
		program.uniforms()
				.set("transform", matrix4f)
				.set("textureSampler", 0)
				.set("diffuseColour", Colour.toRangedVector(diffuse))
				.complete();
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
