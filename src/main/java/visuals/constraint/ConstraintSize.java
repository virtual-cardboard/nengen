package visuals.constraint;

import visuals.constraint.dimension.DimensionConstraint;
import visuals.constraint.position.PositionConstraint;

public class ConstraintSize {

	private final DimensionConstraint w;
	private final DimensionConstraint h;

	public ConstraintSize(DimensionConstraint w, DimensionConstraint h) {
		this.w = w;
		this.h = h;
	}

	public ConstraintSizeValue value() {
		return new ConstraintSizeValue(w.get(), h.get());
	}

	public DimensionConstraint w() {
		return w;
	}

	public DimensionConstraint h() {
		return h;
	}

}
