package visuals.constraint.posdim;

import visuals.constraint.Constraint;

public class MinConstraint implements Constraint {

	private final Constraint c1;
	private final Constraint c2;

	public MinConstraint(Constraint c1, Constraint c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public float get() {
		return Math.min(c1.get(), c2.get());
	}

	public static Constraint min(Constraint c1, Constraint c2) {
		return new MinConstraint(c1, c2);
	}

}
