package visuals.constraint.posdim;

import visuals.constraint.Constraint;

public class MaxConstraint implements Constraint {

	private final Constraint c1;
	private final Constraint c2;

	public MaxConstraint(Constraint c1, Constraint c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public float get() {
		return Math.max(c1.get(), c2.get());
	}

	public static Constraint max(Constraint c1, Constraint c2) {
		return new MaxConstraint(c1, c2);
	}

}
