package context;

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

}
