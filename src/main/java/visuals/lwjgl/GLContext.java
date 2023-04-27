package visuals.lwjgl;

import common.math.Vector2i;

/**
 * This class is used to store OpenGL context data.
 * <br>
 * This class is used internally by the engine and should not be modified by the user.
 * <br>
 * <br>
 * OpenGL uses static variables to store context data. Static state is difficult to reason about and can lead to
 * confusing bugs. The engine uses this class to store OpenGL context data in a non-static way so that the user does not
 * have to worry about setting OpenGL context data correctly.
 *
 * @author Lunkle
 */
public class GLContext {

	public int vertexArrayID = 0;
	public int bufferID = 0;
	public int framebufferID = 0;
	public int renderBufferID = 0;
	public int shaderProgramID = 0;
	public int[] textureIDs = new int[48];

	private Vector2i windowDim;

	public GLContext() {
	}

	public float width() {
		return windowDim.x();
	}

	public float height() {
		return windowDim.y();
	}

	public Vector2i windowDim() {
		return windowDim;
	}

	public void setWindowDim(Vector2i windowDim) {
		this.windowDim = windowDim;
	}

}
