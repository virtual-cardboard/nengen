package visuals.builtin;

import visuals.lwjgl.render.VertexShader;

public class InstancedTexturedTransformationVertexShader {

	private static VertexShader shader;

	private InstancedTexturedTransformationVertexShader() {
	}

	private static final String TEXTURED_TRANSFORMATION_VERTEX_SHADER_SOURCE = "#version 330 core\n"
			+ "layout (location = 0) in vec3 vertexPos;"
			+ "layout (location = 1) in vec2 textureCoord;"
			+ "out vec2 texCoord;"
			+ ""
			+ "uniform mat4 matrix4f;"
			+ ""
//			+ "uniform vec2[] offset;"
			+ ""
			+ "void main() {"
			+ "    gl_Position = matrix4f * vec4(vertexPos, 1);"
			+ "    texCoord = textureCoord;"
			+ "}";

	public static VertexShader instance() {
		if (shader == null) {
			shader = new VertexShader()
					.source(TEXTURED_TRANSFORMATION_VERTEX_SHADER_SOURCE)
					.load();
		}
		return shader;
	}

}
