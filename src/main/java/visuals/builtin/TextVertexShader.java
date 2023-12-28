package visuals.builtin;

import visuals.lwjgl.render.VertexShader;

public class TextVertexShader {

	private static VertexShader shader;

	private TextVertexShader() {
	}

	private static final String TEXTURED_TRANSFORMATION_VERTEX_SHADER_SOURCE = "#version 330 core\n"
			+ "layout (location = 0) in vec3 vertexPos;\n"
			+ "layout (location = 1) in vec2 textureCoord;\n"
			+ "layout (location = 2) in vec4 atlas;\n"
			+ "layout (location = 3) in vec2 offset;\n"
			+ "\n"
			+ "out vec2 texCoord;\n"
			+ "\n"
			+ "uniform mat4 transform;\n"
			+ "uniform float fontSize;\n"
			+ "uniform vec2 textureDim;"
			+ "\n"
			+ "mat4 scale(float x, float y, float z);\n"
			+ "mat4 translate(float x, float y, float z);\n"
			+ "\n"
			+ "void main() {\n"
			+ "    mat4 translateMatrix = translate(offset.x, offset.y, 0);\n"
			+ "    vec2 resize = fontSize * atlas.zw / textureDim;\n"
			+ "    mat4 scaleMatrix = scale(resize.x, resize.y, 1);\n"
			+ "    gl_Position = scaleMatrix * transform * vec4(vertexPos, 1);\n"
			+ "    texCoord = textureCoord;\n"
//			+ "    gl_Position = vec4(vertexPos, 1);\n"
			+ "}\n"
			+ "\n"
			+ "mat4 scale(float x, float y, float z) {\n"
			+ "	   return mat4(\n"
			+ "		   vec4(x, 0, 0, 0),\n"
			+ "		   vec4(0, y, 0, 0),\n"
			+ "		   vec4(0, 0, z, 0),\n"
			+ "		   vec4(0, 0, 0, 1));\n"
			+ "}\n"
			+ "\n"
			+ "mat4 translate(float x, float y, float z) {\n"
			+ "	   return mat4(\n"
			+ "		   vec4(1, 0, 0, x),\n"
			+ "		   vec4(0, 1, 0, y),\n"
			+ "		   vec4(0, 0, 1, z),\n"
			+ "		   vec4(0, 0, 0, 1));\n"
			+ "}\n";

	public static VertexShader instance() {
		if (shader == null) {
			shader = new VertexShader()
					.source(TEXTURED_TRANSFORMATION_VERTEX_SHADER_SOURCE)
					.load();
		}
		return shader;
	}

}
