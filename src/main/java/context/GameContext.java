package context;

import context.input.event.FrameResizedInputEvent;
import context.input.event.KeyPressedInputEvent;
import context.input.event.KeyReleasedInputEvent;
import context.input.event.KeyRepeatedInputEvent;
import context.input.event.MouseMovedInputEvent;
import context.input.event.MousePressedInputEvent;
import context.input.event.MouseReleasedInputEvent;
import context.input.event.MouseScrolledInputEvent;
import context.input.event.PacketReceivedInputEvent;

public class GameContext {

	private GameContextWrapper wrapper;

	public void init() {
	}

	public void render() {
	}

	public void update() {
	}

	public void terminate() {
	}

	public void transition(GameContext nextContext) {
		terminate();
		nextContext.init();
		wrapper.setContext(nextContext);
	}

	/**
	 * Sets the context wrapper for this context.
	 * <br>
	 * <br>
	 * This method is package-private because it should only be called by the wrapper.
	 *
	 * @param wrapper The context wrapper.
	 */
	void setWrapper(GameContextWrapper wrapper) {
		this.wrapper = wrapper;
	}

	public void input(KeyPressedInputEvent event) {
	}

	public void input(KeyReleasedInputEvent event) {
	}

	public void input(KeyRepeatedInputEvent event) {
	}

	public void input(MouseMovedInputEvent event) {
	}

	public void input(MousePressedInputEvent event) {
	}

	public void input(MouseReleasedInputEvent event) {
	}

	public void input(MouseScrolledInputEvent event) {
	}

	public void input(PacketReceivedInputEvent event) {
	}

	public void input(FrameResizedInputEvent event) {
	}

}
