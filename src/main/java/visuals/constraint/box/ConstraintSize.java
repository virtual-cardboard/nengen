package visuals.constraint.box;

import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

import common.math.Vector2f;
import visuals.constraint.Constraint;

public class ConstraintSize {

	private final Constraint w;
	private final Constraint h;

	public ConstraintSize(Constraint w, Constraint h) {
		this.w = w;
		this.h = h;
	}

	public ConstraintSize(Vector2f dimensions) {
		this(absolute(dimensions.x()), absolute(dimensions.y()));
	}

	public ConstraintSizeValue value() {
		return new ConstraintSizeValue(w.get(), h.get());
	}

	public Constraint w() {
		return w;
	}

	public Constraint h() {
		return h;
	}

	public ConstraintSize scale(float f) {
		return new ConstraintSize(w.multiply(f), h.multiply(f));
	}

	public ConstraintSize add(ConstraintSize other) {
		return new ConstraintSize(w.add(other.w), h.add(other.h));
	}

	public ConstraintSize subtract(ConstraintSize other) {
		return new ConstraintSize(w.sub(other.w), h.subtract(other.h));
	}

}
