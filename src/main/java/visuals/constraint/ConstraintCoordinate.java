package visuals.constraint;

import common.math.Vector2f;
import visuals.constraint.position.PositionConstraint;

import static visuals.constraint.posdim.AbsolutePosDimConstraint.absolute;

public class ConstraintCoordinate {

	private final PositionConstraint x;
	private final PositionConstraint y;

	public ConstraintCoordinate(PositionConstraint x, PositionConstraint y) {
		this.x = x;
		this.y = y;
	}

	public ConstraintCoordinateValue value() {
		return new ConstraintCoordinateValue(x.get(), y.get());
	}

	public PositionConstraint x() {
		return x;
	}

	public PositionConstraint y() {
		return y;
	}

	public ConstraintCoordinate invert() {
		return new ConstraintCoordinate(x.multiply(-1), y.multiply(-1));
	}

	public ConstraintCoordinate translate(PositionConstraint x, PositionConstraint y) {
		return new ConstraintCoordinate(this.x.add(x), this.y.add(y));
	}

	public ConstraintCoordinate translate(float x, float y) {
		return translate(absolute(x), absolute(y));
	}

	public ConstraintCoordinate translate(Vector2f v) {
		return translate(v.x(), v.y());
	}

    public ConstraintCoordinate translate(ConstraintCoordinate c) {
		return translate(c.x, c.y);
    }

	public ConstraintCoordinate subtract(ConstraintCoordinate c) {
		return translate(c.invert());
	}

	public ConstraintCoordinate subtract(Vector2f v) {
		return translate(v.negate());
	}

	public ConstraintCoordinate multiply(float f) {
		return new ConstraintCoordinate(x.multiply(f), y.multiply(f));
	}

}
