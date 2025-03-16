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
		return scale(f, f);
	}

	public ConstraintSize scale(float f1, float f2) {
		return new ConstraintSize(w.multiply(f1), h.multiply(f2));
	}

	public ConstraintSize add(ConstraintSize other) {
		return add(other.w(), other.h());
	}

	public ConstraintSize add(Constraint cw, Constraint ch) {
		return new ConstraintSize(w.add(cw), h.add(ch));
	}

	public ConstraintSize neg() {
		return new ConstraintSize(w.neg(), h.neg());
	}

}
