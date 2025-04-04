package context.input;

import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

import visuals.constraint.box.ConstraintPair;

public class Mouse {

	private int x;
	private int y;

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public ConstraintPair coordinate() {
		return new ConstraintPair(absolute(x), absolute(y));
	}

	public void x(int x) {
		this.x = x;
	}

	public void y(int y) {
		this.y = y;
	}

}
