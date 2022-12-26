package lwjgl;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;

import context.GameContext;
import context.GameContextWrapper;
import nengen.EngineConfiguration;
import nengen.common.time.TimestepTimer;

public class GameWindowUpdater extends TimestepTimer implements Runnable {

	private final GameWindow window;
	private GameContextWrapper wrapper;
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
		window = new GameWindow(configuration);
	}

	@Override
	protected void startActions() {
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
		window.destroy();
	}

	@Override
	protected boolean endCondition() {
		return false;
	}

}
