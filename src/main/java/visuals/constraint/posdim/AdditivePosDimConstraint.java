package visuals.constraint.posdim;

import visuals.constraint.Constraint;

/**
 * A {@link PosDimConstraint} that represents the sum of two other
 * {@link Constraint}s.
 *
 * @see Constraint
 * @see PosDimConstraint
 */
public class AdditivePosDimConstraint implements PosDimConstraint {

	private final Constraint c1;
	private final Constraint c2;

	public AdditivePosDimConstraint(Constraint c1, Constraint c2) {
		this.c1 = c1;
		this.c2 = c2;
	}

	public float get() {
		return c1.get() + c2.get();
	}

}
