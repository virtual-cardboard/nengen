package visuals.constraint;

import visuals.constraint.dimension.DimensionConstraint;
import visuals.constraint.position.PositionConstraint;

public class ConstraintBox {

	private final PositionConstraint x;
	private final PositionConstraint y;
	private final DimensionConstraint w;
	private final DimensionConstraint h;

	public ConstraintBox(PositionConstraint x, PositionConstraint y, DimensionConstraint w, DimensionConstraint h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public ConstraintBoxValue value() {
		return new ConstraintBoxValue(x.get(), y.get(), w.get(), h.get());
	}

	public PositionConstraint x() {
		return x;
	}

	public PositionConstraint y() {
		return y;
	}

	public DimensionConstraint w() {
		return w;
	}

	public DimensionConstraint h() {
		return h;
	}

	public ConstraintLine horizontal() {
		return new ConstraintLine(x, w);
	}

	public ConstraintLine vertical() {
		return new ConstraintLine(y, h);
	}

}
