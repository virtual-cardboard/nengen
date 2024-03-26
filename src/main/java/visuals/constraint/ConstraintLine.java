package visuals.constraint;

import visuals.constraint.dimension.DimensionConstraint;
import visuals.constraint.position.PositionConstraint;

public class ConstraintLine {

	private final PositionConstraint pos;
	private final DimensionConstraint dim;

	public ConstraintLine(PositionConstraint pos, DimensionConstraint dim) {
		this.pos = pos;
		this.dim = dim;
	}

	public float x() {
		return pos.get();
	}

	public float w() {
		return dim.get();
	}

	public PositionConstraint xConstraint() {
		return pos;
	}

	public DimensionConstraint wConstraint() {
		return dim;
	}

}
