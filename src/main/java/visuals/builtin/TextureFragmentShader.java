package visuals.builtin;

import visuals.lwjgl.render.FragmentShader;
import visuals.lwjgl.render.Shader;

public class TextureFragmentShader {

	private static Shader shader;

	private TextureFragmentShader() {
	}

	public static final String TEXT_FRAGMENT_SHADER_SOURCE = "#version 330 core\n"
			+ "out vec4 fragColor;"
			+ ""
			+ "in vec2 texCoord;"
			+ ""
			+ "uniform sampler2D textureSampler;"
			+ "uniform vec4 diffuseColour = vec4(1, 1, 1, 1);"
			+ ""
			+ "void main()"
			+ "{"
			+ "    fragColor = texture(textureSampler, texCoord) * diffuseColour;"
			+ "    if (fragColor.a == 0) {"
			+ "        discard;"
			+ "    }"
			+ "}";

	public static Shader instance() {
		if (shader == null) {
			shader = new FragmentShader()
					.source(TEXT_FRAGMENT_SHADER_SOURCE)
					.load();
		}
		return shader;
	}

}
