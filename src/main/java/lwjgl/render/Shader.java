package lwjgl.render;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glShaderSource;

import lwjgl.ResourcePack;

public class Shader extends GLRegularObject {

	private int id;
	private final ShaderType shaderType;

	public Shader(ShaderType shaderType) {
		this.shaderType = shaderType;
	}

	public Shader verifyShaderType(ShaderType type) {
		if (shaderType != type) {
			throw new RuntimeException("Expected shader type: " + type + " actual shader type: " + shaderType);
		}
		return this;
	}

	public void compile(String source) {
		verifyInitialized();
		glShaderSource(id, source);
		glCompileShader(id);
		if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(id, 500));
			throw new RuntimeException("Could not compile shader source:\n" + source);
		}
	}

	public void delete() {
		glDeleteShader(id);
	}

	public int id() {
		return id;
	}

	public void genID() {
		this.id = glCreateShader(shaderType.type);
		initialize();
	}

	public ShaderType getShaderType() {
		return shaderType;
	}

	@Override
	public void putInto(String name, ResourcePack resourcePack) {
	}

}
