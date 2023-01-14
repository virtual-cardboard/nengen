package visuals.lwjgl.render;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.GL_COLOR_ATTACHMENT0;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.GL_RENDERBUFFER;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengl.GL30.glDeleteFramebuffers;
import static org.lwjgl.opengl.GL30.glFramebufferRenderbuffer;
import static org.lwjgl.opengl.GL30.glFramebufferTexture2D;
import static org.lwjgl.opengl.GL30.glGenFramebuffers;

import visuals.lwjgl.GLContext;
import visuals.lwjgl.ResourcePack;

public class FrameBufferObject extends GLContainerObject {

	private Texture texture;
	private RenderBufferObject rbo;

	@Override
	public void genID() {
		this.id = glGenFramebuffers();
		initialize();
	}

	public void attach(Texture texture) {
		verifyInitialized();
		glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, texture.id(), 0);
		this.texture = texture;
	}

	public void attach(Texture texture, int attachmentType) {
		verifyInitialized();
		glFramebufferTexture2D(GL_FRAMEBUFFER, attachmentType, GL_TEXTURE_2D, texture.id(), 0);
		this.texture = texture;
	}

	public void attach(RenderBufferObject rbo) {
		verifyInitialized();
		glFramebufferRenderbuffer(GL_FRAMEBUFFER, rbo.formatType(), GL_RENDERBUFFER, rbo.id());
		this.rbo = rbo;
	}

	public void bind(GLContext glContext) {
		verifyInitialized();
		if (glContext.framebufferID == id) {
			return;
		}
		glBindFramebuffer(GL_FRAMEBUFFER, id);
		glContext.framebufferID = id;
	}

	public static void unbind(GLContext glContext) {
		if (glContext.framebufferID == 0) {
			return;
		}
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glContext.framebufferID = 0;
	}

	public void delete() {
		verifyInitialized();
		glDeleteFramebuffers(id);
	}

	public Texture texture() {
		return texture;
	}

	public RenderBufferObject renderBufferObject() {
		return rbo;
	}

	@Override
	public void putInto(String name, ResourcePack resourcePack) {
		resourcePack.putFBO(name, this);
	}

}
