package nengen;

import context.GameContext;

public class Nengen {

	private final NengenConfiguration configuration;

	private Nengen() {
		configuration = new NengenConfiguration();
	}

	public static Nengen newNengen() {
		return new Nengen();
	}

	public NengenConfiguration configure() {
		return configuration;
	}

	public void startNengen(GameContext context) {
	}

}
