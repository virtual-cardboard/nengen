package visuals.lwjgl.render;

import static visuals.lwjgl.render.ShaderType.VERTEX;

public class VertexShader extends Shader {

	public VertexShader() {
		super(VERTEX);
	}

	public VertexShader source(String source) {
		this.source = source;
		return this;
	}

	public VertexShader load() {
		super.doLoad();
		return this;
	}

}