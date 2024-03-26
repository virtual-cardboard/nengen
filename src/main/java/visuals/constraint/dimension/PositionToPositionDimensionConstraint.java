package visuals.constraint.dimension;

import visuals.constraint.position.PositionConstraint;

/**
 * A {@link DimensionConstraint} that is the difference between two {@link PositionConstraint}s.
 */
public class PositionToPositionDimensionConstraint implements DimensionConstraint {

	private final PositionConstraint start;
	private final PositionConstraint end;

	public PositionToPositionDimensionConstraint(PositionConstraint start, PositionConstraint end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public float get() {
		return end.get() - start.get();
	}

	public static DimensionConstraint between(PositionConstraint start, PositionConstraint end) {
		return new PositionToPositionDimensionConstraint(start, end);
	}

}
