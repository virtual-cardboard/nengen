package lwjgl;

import static nengen.EngineConfiguration.DEBUG;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import common.time.TimestepTimer;
import context.GameContext;
import context.GameContextWrapper;
import nengen.EngineConfiguration;

public class GameWindowUpdater extends TimestepTimer implements Runnable {

	private final GameWindow window;
	private final GameContextWrapper wrapper;
	private final EngineConfiguration configuration;

	/**
	 * Creates a new {@link GameWindowUpdater} with the given configuration and context wrapper.
	 *
	 * @param configuration The configuration for the engine.
	 * @param wrapper       The context wrapper.
	 */
	public GameWindowUpdater(EngineConfiguration configuration, GameContextWrapper wrapper) {
		super(1000 / configuration.frameRate());
		this.configuration = configuration;
		this.window = new GameWindow(configuration);
		this.wrapper = wrapper;
	}

	@Override
	protected void startActions() {
		DEBUG("Creating window...");
		window.createDisplay();
		window.attachCallbacks();
		window.createSharedContextWindow();
	}

	@Override
	protected void update() {
		GameContext context = wrapper.context();
		glfwPollEvents();
		int[] width = new int[1];
		int[] height = new int[1];
		glfwGetWindowSize(window.windowId(), width, height);
		context.render();
	}

	@Override
	protected void endActions() {
		DEBUG("Destroying window...");
		window.destroy();
	}

	@Override
	protected boolean endCondition() {
		return glfwWindowShouldClose(window.windowId());
	}

}
