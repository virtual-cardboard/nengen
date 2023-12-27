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
			+ "in vec2 texCoord;\n"
			+ "out vec4 fragColor;\n"
			+ "\n"
			+ "uniform sampler2D textureSampler;\n"
			+ "uniform int texWidth, texHeight;\n"
			+ "uniform vec4 fill;\n"
			+ "\n"
			+ "void main() {\n"
//			+ "    vec4 atlasPosDim = atlas[gl_InstanceID];\n"
//			+ "    vec2 atlasPos = atlasPosDim.xy;"
//			+ "    vec2 atlasDim = atlasPosDim.zw;"
//			+ "    vec2 offsetPos = offset[gl_InstanceID];"
//			+ "    vec2 newTexCoord;"
//			+ "    newTexCoord.x = (texCoord.x * atlasDim.x + atlasPos.x) / texWidth;"
//			+ "    newTexCoord.y = (texCoord.y * atlasDim.y + atlasPos.y) / texHeight;"
//			+ "    fragColor = texture(textureSampler, newTexCoord) * fill;"
//			+ "    if (fragColor.a == 0) {"
			+ "        fragColor = vec4(1, 1, 0, 0);"
//			+ "    }"
//			+ "    "
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
