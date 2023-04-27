package visuals.builtin;

import static visuals.lwjgl.render.ShaderType.FRAGMENT;

import visuals.lwjgl.render.Shader;

public class TextFragmentShader {

	private static Shader shader;

	private TextFragmentShader() {
	}

	public static final String TEXT_FRAGMENT_SHADER_SOURCE = "#version 330 core\n"
			+ "in vec2 texCoord;"
			+ "out vec4 fragColor;"
			+ ""
			+ "uniform sampler2D textureSampler;"
			+ "uniform int texWidth, texHeight;"
			+ "uniform int width, height;"
			+ "uniform int x, y;"
			+ "uniform vec4 fill;"
			+ ""
			+ "void main() {"
			+ "    vec2 newTexCoord = texCoord.xy;"
			+ "    newTexCoord.x = (texCoord.x * width + x) / texWidth;"
			+ "    newTexCoord.y = (texCoord.y * height + y) / texHeight;"
			+ "    fragColor = texture(textureSampler, newTexCoord) * fill;"
			+ "    if (fragColor.a == 0) {"
			+ "        discard;"
			+ "    }"
			+ "}";

	public static Shader instance() {
		if (shader == null) {
			shader = new Shader()
					.type(FRAGMENT)
					.source(TEXT_FRAGMENT_SHADER_SOURCE)
					.load();
		}
		return shader;
	}

}
