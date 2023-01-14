package nengen;

import context.GameContext;
import context.GameContextWrapper;
import context.GameTickUpdater;
import context.GameWindowUpdater;
import visuals.lwjgl.GLContext;

/**
 * The main class for the Nengen game engine.
 *
 * @author Lunkle
 */
public class Nengen {

	/**
	 * External-facing configuration for Nengen. Note that this configuration is converted to an internal-facing
	 * configuration when the engine is started.
	 */
	private final NengenConfiguration configuration;

	public Nengen() {
		configuration = new NengenConfiguration();
	}

	/**
	 * Offers the configuration interface for the Nengen engine.
	 */
	public NengenConfiguration configure() {
		return configuration;
	}

	public void startNengen(GameContext context) {
		assert context != null : "Context cannot be null";
		GLContext glContext = new GLContext();

		EngineConfiguration config = configuration.build();
		GameContextWrapper wrapper = new GameContextWrapper(context, glContext);

		Thread renderThread = new Thread(new GameWindowUpdater(config, wrapper));
		Thread tickThread = new Thread(new GameTickUpdater(config, wrapper));
		renderThread.start();
		tickThread.start();
	}

}
