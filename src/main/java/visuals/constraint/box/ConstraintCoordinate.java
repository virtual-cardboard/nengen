package visuals.constraint.box;

import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

import common.math.Vector2f;
import visuals.constraint.Constraint;

public class ConstraintCoordinate {

	private final Constraint x;
	private final Constraint y;

	public ConstraintCoordinate(Constraint x, Constraint y) {
		this.x = x;
		this.y = y;
	}

	public ConstraintCoordinateValue value() {
		return new ConstraintCoordinateValue(x.get(), y.get());
	}

	public Constraint x() {
		return x;
	}

	public Constraint y() {
		return y;
	}

	public ConstraintCoordinate invert() {
		return new ConstraintCoordinate(x.multiply(-1), y.multiply(-1));
	}

	public ConstraintCoordinate translate(ConstraintSize size) {
		return new ConstraintCoordinate(this.x.add(size.w()), this.y.add(size.h()));
	}

	public ConstraintCoordinate translate(Constraint x, Constraint y) {
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

	public ConstraintCoordinate neg() {
		return new ConstraintCoordinate(x.neg(), y.neg());
	}

}
