package nengen;

import context.GameContext;
import context.GameContextWrapper;
import lwjgl.GameTickUpdater;
import lwjgl.GameWindowUpdater;

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

	/**
	 * A paradigm for the entire engine is to never allow the user to instantiate any objects using the <code>new</code>
	 * keyword. This is why the constructor is private.
	 * <br>
	 * <br>
	 * The user should use the static method {@link #newNengen()} to instantiate a new Nengen engine.
	 */
	private Nengen() {
		configuration = new NengenConfiguration();
	}

	public static Nengen newNengen() {
		return new Nengen();
	}

	/**
	 * Offers the configuration interface for the Nengen engine.
	 */
	public NengenConfiguration configure() {
		return configuration;
	}

	public void startNengen(GameContext context) {
		assert context != null : "Context cannot be null";

		EngineConfiguration config = configuration.build();
		GameContextWrapper wrapper = new GameContextWrapper(context);
		context.init(); // Explicitly initialize the first context.

		Thread renderThread = new Thread(new GameWindowUpdater(config, wrapper));
		Thread tickThread = new Thread(new GameTickUpdater(config, wrapper));
		renderThread.start();
		tickThread.start();
	}

}
