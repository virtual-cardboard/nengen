package visuals.lwjgl.render.framebuffer;

import visuals.lwjgl.render.FrameBufferObject;

public class DefaultFrameBuffer {

	public static FrameBufferObject instance() {
		return new FrameBufferObject();
	}

}
