package visuals.lwjgl.render.shader;

import common.math.Matrix4f;
import visuals.lwjgl.render.ShaderProgram;

public class ShaderUniformMat4Data extends ShaderUniformData<Matrix4f> {

	public ShaderUniformMat4Data(String name) {
		super(name, Matrix4f.class);
	}

	@Override
	public void setForProgram(ShaderProgram program) {
		if (value != null)
			program.set(name, value);
	}

}
