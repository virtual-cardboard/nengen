package visuals.constraint;

import visuals.constraint.dimension.DimensionConstraint;
import visuals.constraint.position.PositionConstraint;

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

}
