package context.input.event;

import context.input.Mouse;

public final class MousePressedInputEvent extends GameInputEvent {

	private Mouse mouse;
	private final int button;

	public MousePressedInputEvent(Mouse mouse, int mouseButton) {
		this.mouse = mouse;
		this.button = mouseButton;
	}

	public Mouse mouse() {
		return mouse;
	}

	public int button() {
		return button;
	}

}
