package visuals.builtin;

import static visuals.lwjgl.render.ShaderType.VERTEX;

import visuals.lwjgl.render.Shader;

public class TexturedTransformationVertexShader {

	private static Shader shader;

	private TexturedTransformationVertexShader() {
	}

	private static final String TEXTURED_TRANSFORMATION_VERTEX_SHADER_SOURCE = "#version 330 core\n"
			+ "layout (location = 0) in vec3 vertexPos;"
			+ "layout (location = 1) in vec2 textureCoord;"
			+ "out vec2 texCoord;"
			+ "uniform mat4 matrix4f;"
			+ "void main() {"
			+ "    gl_Position = matrix4f * vec4(vertexPos, 1);"
			+ "    texCoord = textureCoord;"
			+ "}";

	public static Shader instance() {
		if (shader == null) {
			shader = new Shader()
					.type(VERTEX)
					.source(TEXTURED_TRANSFORMATION_VERTEX_SHADER_SOURCE)
					.load();
		}
		return shader;
	}

}
