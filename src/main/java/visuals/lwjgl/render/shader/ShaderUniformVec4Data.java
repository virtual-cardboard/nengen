package visuals.lwjgl.render.shader;

import common.math.Vector4f;
import visuals.lwjgl.render.ShaderProgram;

public class ShaderUniformVec4Data extends ShaderUniformData<Vector4f> {

	public ShaderUniformVec4Data(String name) {
		super(name, Vector4f.class);
	}

	@Override
	public void setForProgram(ShaderProgram program) {
		if (value != null)
			program.set(name, value);
	}

}
