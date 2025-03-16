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

	@Deprecated
	public ConstraintCoordinate translate(float x, float y) {
		return translate(absolute(x), absolute(y));
	}

	@Deprecated
	public ConstraintCoordinate translate(Vector2f v) {
		return translate(v.x(), v.y());
	}

	@Deprecated
	public ConstraintCoordinate translate(ConstraintCoordinate c) {
		return translate(c.x, c.y);
	}

	public ConstraintCoordinate subtract(ConstraintCoordinate c) {
		return translate(c.invert());
	}

	public ConstraintCoordinate subtract(Vector2f v) {
		return translate(v.negate());
	}

	public ConstraintSize scale(float f) {
		return scale(f, f);
	}

	public ConstraintSize scale(float f1, float f2) {
		return new ConstraintSize(x.multiply(f1), x.multiply(f2));
	}

	public ConstraintSize add(ConstraintSize other) {
		return add(other.w(), other.h());
	}

	public ConstraintSize add(Vector2f v) {
		return add(absolute(v.x()), absolute(v.y()));
	}

	public ConstraintSize add(Constraint cw, Constraint ch) {
		return new ConstraintSize(x.add(cw), x.add(ch));
	}

	public ConstraintCoordinate neg() {
		return new ConstraintCoordinate(x.neg(), y.neg());
	}

}
