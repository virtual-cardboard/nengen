package context.input;

import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

import visuals.constraint.box.ConstraintCoordinate;

public class Mouse {

	private int x;
	private int y;

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public ConstraintCoordinate coordinate() {
		return new ConstraintCoordinate(absolute(x), absolute(y));
	}

	public void x(int x) {
		this.x = x;
	}

	public void y(int y) {
		this.y = y;
	}

}
