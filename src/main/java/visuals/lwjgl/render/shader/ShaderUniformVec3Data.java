package visuals.lwjgl.render.shader;

import common.math.Vector3f;
import visuals.lwjgl.render.ShaderProgram;

public class ShaderUniformVec3Data extends ShaderUniformData<Vector3f> {

	public ShaderUniformVec3Data(String name) {
		super(name, Vector3f.class);
	}

	@Override
	public void setForProgram(ShaderProgram program) {
		if (value != null)
			program.set(name, value);
	}

}
