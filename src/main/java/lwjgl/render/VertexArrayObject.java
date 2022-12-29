package lwjgl.render;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.util.ArrayList;
import java.util.List;

import lwjgl.GLContext;
import lwjgl.ResourcePack;

/**
 * An object that contains data about a group of vertices in OpenGL. Learn more about them here: <a href=
 * "https://stackoverflow.com/questions/11821336/what-are-vertex-array-objects">
 * https://stackoverflow.com/questions/11821336/what-are-vertex-array-objects</a>
 *
 * @author Jay
 * @see VertexBufferObject
 * @see ElementBufferObject
 */
public class VertexArrayObject extends GLContainerObject {

	private int id;
	private final List<VertexBufferObject> vbos = new ArrayList<>();
	private ElementBufferObject ebo;

	public void draw(GLContext glContext) {
		bind(glContext);
		ebo.bind(glContext);
		glDrawElements(GL_TRIANGLES, ebo.size(), GL_UNSIGNED_INT, 0);
	}

	public void delete() {
		glDeleteVertexArrays(id);
	}

	/**
	 * Tells OpenGL to enable the attached VBOs. Should only be called once after attaching all necessary VBOs.
	 *
	 * @see VertexBufferObject
	 */
	public void enableVertexAttribArrays(GLContext glContext) {
		bind(glContext);
		for (int i = 0; i < vbos.size(); i++) {
			vbos.get(i).enableVertexAttribArray(glContext, i);
		}
	}

	protected void bind(GLContext glContext) {
		verifyInitialized();
		if (glContext.vaoID == id) {
			return;
		}
		glBindVertexArray(id);
		glContext.vaoID = id;
	}

	public void attachVBO(VertexBufferObject vbo) {
		vbos.add(vbo);
	}

	public void genID() {
		this.id = glGenVertexArrays();
		initialize();
	}

	public void setEbo(ElementBufferObject ebo) {
		this.ebo = ebo;
	}

	@Override
	public void putInto(String name, ResourcePack resourcePack) {
		resourcePack.putVAO(name, this);
	}

}
