package visuals.constraint;

import static visuals.constraint.posdim.AbsolutePosDimConstraint.absolute;

import common.math.Vector2f;
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

	public ConstraintCoordinate translate(PositionConstraint x, PositionConstraint y) {
		return new ConstraintCoordinate(this.x.add(x), this.y.add(y));
	}

	public ConstraintCoordinate translate(float x, float y) {
		return translate(absolute(x), absolute(y));
	}

	public ConstraintCoordinate translate(Vector2f v) {
		return translate(v.x(), v.y());
	}

}
