package visuals.builtin;

import visuals.lwjgl.render.ElementBufferObject;
import visuals.lwjgl.render.VertexArrayObject;
import visuals.lwjgl.render.VertexBufferObject;

public class RectangleVertexArrayObject {

	private static VertexArrayObject vao;

	private RectangleVertexArrayObject() {
	}

	private static final float[] POSITIONS = {
			1.0f, 1.0f, 0.0f,
			1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f
	};

	private static final float[] TEXTURE_COORDINATES = {
			1.0f, 1.0f,
			1.0f, 0.0f,
			0.0f, 0.0f,
			0.0f, 1.0f
	};

	private static final int[] INDICES = {
			0, 1, 2,
			0, 2, 3
	};

	public static VertexArrayObject instance() {
		if (vao == null) {
			ElementBufferObject ebo = new ElementBufferObject().indices(INDICES).load();
			VertexBufferObject positionsVBO = new VertexBufferObject().data(POSITIONS).dimensions(3).load();
			VertexBufferObject textureCoordinatesVBO = new VertexBufferObject().data(TEXTURE_COORDINATES).dimensions(2).load();
			vao = new VertexArrayObject().vbos(positionsVBO, textureCoordinatesVBO).ebo(ebo).load();
		}
		return vao;
	}

}
