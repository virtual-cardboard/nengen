package visuals.lwjgl.render;

import static visuals.lwjgl.render.ShaderType.FRAGMENT;

public class FragmentShader extends Shader {

	public FragmentShader() {
		super(FRAGMENT);
	}

	public FragmentShader source(String source) {
		this.source = source;
		return this;
	}

	public FragmentShader load() {
		super.doLoad();
		return this;
	}

}