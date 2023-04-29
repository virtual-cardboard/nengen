package visuals.lwjgl.render;

import static visuals.lwjgl.render.ShaderType.GEOMETRY;

public class GeometryShader extends Shader {

	public GeometryShader() {
		super(GEOMETRY);
	}

	public GeometryShader source(String source) {
		this.source = source;
		return this;
	}

	public GeometryShader load() {
		super.doLoad();
		return this;
	}

}