package lwjgl.render;

import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import lwjgl.GLContext;

/**
 * An object that tells OpenGL which vertices in a {@link VertexBufferObject} to display.
 *
 * @author Jay
 * @see VertexArrayObject
 */
public class ElementBufferObject extends GLRegularObject {

	private int id;
	private final int[] data;

	public ElementBufferObject(final int[] indices) {
		this.data = indices;
	}

	public void loadData(GLContext glContext) {
		bind(glContext);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glContext.bufferID = 0;
	}

	public void delete() {
		glDeleteBuffers(id);
	}

	protected void bind(GLContext glContext) {
		verifyInitialized();
		if (glContext.bufferID == id) {
			return;
		}
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
		glContext.bufferID = id;
	}

	public void genID() {
		this.id = glGenBuffers();
		initialize();
	}

	public int size() {
		return data.length;
	}

}
