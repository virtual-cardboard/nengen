package visuals.lwjgl.render;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import visuals.lwjgl.GLContext;

/**
 * An object that contains data about the available vertices able to be used in a {@link VertexArrayObject}. Use
 * {@link ElementBufferObject} to determine which vertices to use.
 *
 * @author Jay
 */
public class VertexBufferObject extends GLRegularObject {

	protected float[] data;
	protected int dimensions;
	protected int index;

	@Override
	public void genID() {
		// TODO: Delete
	}

	public VertexBufferObject data(float[] data) {
		this.data = data;
		return this;
	}

	public VertexBufferObject dimensions(int dimensions) {
		this.dimensions = dimensions;
		return this;
	}

	public VertexBufferObject index(int index) {
		this.index = index;
		return this;
	}

	public VertexBufferObject load() {
		id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, id);
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		return this;
	}

	protected void enableVertexAttribArray(GLContext glContext) {
		bind(glContext);
		glVertexAttribPointer(index, dimensions, GL_FLOAT, false, dimensions * Float.BYTES, 0);
		glEnableVertexAttribArray(index);
	}

	protected void enableVertexAttribArray() {
		glBindBuffer(GL_ARRAY_BUFFER, id);
		glVertexAttribPointer(index, dimensions, GL_FLOAT, false, dimensions * Float.BYTES, 0);
		glEnableVertexAttribArray(index);
	}

	private void bind(GLContext glContext) {
		verifyInitialized();
		if (glContext.bufferID == id) {
			return;
		}
		glBindBuffer(GL_ARRAY_BUFFER, id);
		glContext.bufferID = id;
	}

	public void delete() {
		glDeleteBuffers(id);
	}

}
