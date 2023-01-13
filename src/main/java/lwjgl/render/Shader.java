package lwjgl.render;

import static nengen.EngineConfiguration.DEBUG;
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

	private ShaderType shaderType;

	private transient String source = null;

	public Shader() {
	}

	public Shader(ShaderType shaderType) {
		this.shaderType = shaderType;
	}

	public Shader type(ShaderType shaderType) {
		this.shaderType = shaderType;
		return this;
	}

	@Override
	public void genID() {
		this.id = glCreateShader(shaderType.type);
		initialize();
	}

	public Shader source(String source) {
		this.source = source;
		return this;
	}

	public Shader load() {
		this.id = glCreateShader(shaderType.type);
		glShaderSource(id, source);
		glCompileShader(id);
		if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(glGetShaderInfoLog(id, 500));
			throw new RuntimeException("Could not compile shader source:\n" + source);
		}
		if (!DEBUG) {
			// If we're not debugging, we don't need the source anymore.
			// This frees up some memory.
			source = null;
		}
		initialize();
		return this;
	}

	public void delete() {
		glDeleteShader(id);
	}

	public int id() {
		return id;
	}

	public ShaderType getShaderType() {
		return shaderType;
	}

	@Override
	public void putInto(String name, ResourcePack resourcePack) {
	}

}
