package lwjgl.callback;

import context.GameContextWrapper;
import context.input.event.MouseMovedInputEvent;
import org.lwjgl.glfw.GLFWCursorPosCallback;

/**
 * Decorates a GLFW mouse moved event into a {@link MouseMovedInputEvent} and forwards it to the game context.
 *
 * @author Lunkle
 */
public class MouseMovementCallback extends GLFWCursorPosCallback {

	private final GameContextWrapper wrapper;

	public MouseMovementCallback(GameContextWrapper wrapper) {
		this.wrapper = wrapper;
	}

	/**
	 * This is the function that is called internally by GLFW called when the mouse is moved.
	 */
	@Override
	public void invoke(long window, double xPos, double yPos) {
		wrapper.context().input(new MouseMovedInputEvent((int) xPos, (int) yPos));
	}

}
