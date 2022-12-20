package nengen;

public class NengenConfiguration {

	protected int width;
	protected int height;
	protected int frameRate;
	protected int tickRate;
	protected String windowName;

	public boolean debug = false;

	protected NengenConfiguration() {
	}

	public NengenConfiguration setWindowDim(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public NengenConfiguration setWindowName(String windowName) {
		this.windowName = windowName;
		return this;
	}

	public NengenConfiguration setFrameRate(int frameRate) {
		this.frameRate = frameRate;
		return this;
	}

	public NengenConfiguration setTickRate(int tickRate) {
		this.tickRate = tickRate;
		return this;
	}

	public NengenConfiguration debug() {
		debug = true;
		return this;
	}

}
