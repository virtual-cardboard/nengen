package visuals.builtin;

import visuals.lwjgl.render.FragmentShader;
import visuals.lwjgl.render.Shader;

/**
 * A fragment {@link Shader} that renders text.
 */
public class TextFragmentShader {

	private static FragmentShader shader;

	private TextFragmentShader() {
	}

	public static final String TEXT_FRAGMENT_SHADER_SOURCE = "#version 330 core\n"
			+ "in vec2 texCoord;"
			+ "out vec4 fragColor;"
			+ ""
			+ "uniform sampler2D textureSampler;"
//			+ "uniform int texWidth, texHeight;"
			+ "uniform vec4 fill;"
			+ ""
			+ "uniform vec4[] atlas;"
			+ ""
			+ "void main() {"
			+ "    vec4 atlasPosDim = atlas[gl_InstanceID];"
			+ "    vec2 newTexCoord = texCoord.xy + atlasPosDim.xy;"
			+ "    newTexCoord.x = (texCoord.x * width + x) / texWidth;"
			+ "    newTexCoord.y = (texCoord.y * height + y) / texHeight;"
			+ "    fragColor = texture(textureSampler, texCoord) * fill;"
			+ "    if (fragColor.a == 0) {"
			+ "        fragColor = vec4(0, 1, 0, 1);"
			+ "    }"
			+ "    "
			+ "}";

	public static FragmentShader instance() {
		if (shader == null) {
			shader = new FragmentShader()
					.source(TEXT_FRAGMENT_SHADER_SOURCE)
					.load();
		}
		return shader;
	}

}
