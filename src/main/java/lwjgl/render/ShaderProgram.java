package lwjgl.render;

import static nengen.EngineConfiguration.DEBUG;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

import common.math.Matrix4f;
import common.math.Vector2f;
import common.math.Vector3f;
import common.math.Vector4f;
import lwjgl.GLContext;
import lwjgl.ResourcePack;

public class ShaderProgram extends GLRegularObject {

	private final Queue<Shader> toAttach = new ArrayDeque<>(3);
	private final Queue<Integer> toDelete = new ArrayDeque<>(3);

	@Override
	public void genID() {
		id = glCreateProgram();
		initialize();
	}

	/**
	 * Adds a shader that will be attached in the next {@link #load()} call.
	 *
	 * @param shader The shader to attach.
	 */
	public ShaderProgram attach(Shader shader) {
		toAttach.add(shader);
		return this;
	}

	public ShaderProgram data(Shader... shaders) {
		Collections.addAll(toAttach, shaders);
		return this;
	}

	/**
	 * Links the shader program to OpenGL. Should only be called once.
	 */
	public ShaderProgram load() {
		verifyNotInitialized();
		genID();
		for (int i = 0, m = toAttach.size(); i < m; i++) {
			Shader shader = toAttach.poll();
			if (DEBUG) {
				toAttach.add(shader);
			}
			assert shader != null;
			int shaderID = shader.id();
			glAttachShader(id, shaderID);
			toDelete.add(shaderID);
		}
		glLinkProgram(id);
		return this;
	}

	/**
	 * Uses the current shader program to handle any glDrawArrays() or glDrawElements() calls. This is likely to be
	 * called multiple times. You must call {@link #load()} before using bind().
	 *
	 * @param glContext the <code>GLContext</code>
	 */
	public void use(GLContext glContext) {
		verifyInitialized();
		if (glContext.shaderProgramID == id) {
			return;
		}
		glUseProgram(id);
		glContext.shaderProgramID = id;
	}

	public static void unbind() {
		glUseProgram(0);
	}

	public void delete() {
		verifyInitialized();
		glUseProgram(0);
		for (int i = 0; i < toDelete.size(); i++) {
			int shaderID = toDelete.poll();
			if (DEBUG) {
				toDelete.add(shaderID);
			}
			glDetachShader(id, shaderID);
			glDeleteShader(shaderID);
		}
		glDeleteProgram(id);
	}

	public void set(String uniform, boolean value) {
		verifyInitialized();
		glUniform1f(glGetUniformLocation(id, uniform), value ? 1 : 0);
	}

	public void set(String uniform, int i) {
		verifyInitialized();
		glUniform1i(glGetUniformLocation(id, uniform), i);
	}

	public void set(String uniform, float value) {
		verifyInitialized();
		glUniform1f(glGetUniformLocation(id, uniform), value);
	}

	public void set(String uniform, Vector2f vec2) {
		verifyInitialized();
		glUniform2f(glGetUniformLocation(id, uniform), vec2.x(), vec2.y());
	}

	public void set(String uniform, Vector3f vec3) {
		verifyInitialized();
		glUniform3f(glGetUniformLocation(id, uniform), vec3.x(), vec3.y(), vec3.z());
	}

	public void set(String uniform, Vector4f vec4) {
		verifyInitialized();
		glUniform4f(glGetUniformLocation(id, uniform), vec4.x(), vec4.y(), vec4.z(), vec4.w());
	}

	public void set(String uniform, Matrix4f mat4) {
		verifyInitialized();
		float[] buffer = new float[16];
		mat4.store(buffer);
		glUniformMatrix4fv(glGetUniformLocation(id, uniform), false, buffer);
	}

	@Override
	public void putInto(String name, ResourcePack resourcePack) {
		resourcePack.putShaderProgram(name, this);
	}

}
