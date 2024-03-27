package visuals.constraint.posdim;

import visuals.constraint.Constraint;
import visuals.constraint.ConstraintCoordinate;
import visuals.constraint.ConstraintSize;

public class MaxPosDimConstraint implements PosDimConstraint {

	private final Constraint c1;
	private final Constraint c2;

	public MaxPosDimConstraint(Constraint c1, Constraint c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public float get() {
		return Math.max(c1.get(), c2.get());
	}

	public static PosDimConstraint max(Constraint c1, Constraint c2) {
		return new MaxPosDimConstraint(c1, c2);
	}

}
