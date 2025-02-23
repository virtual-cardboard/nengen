package visuals.constraint.posdim;

import visuals.constraint.Constraint;

/**
 *
 */
public class MultiplierConstraint implements Constraint {

	private final float factor;
	private final Constraint constraint;

	public MultiplierConstraint(float factor, Constraint constraint) {
		this.factor = factor;
		this.constraint = constraint;
	}

	@Override
	public float get() {
		return constraint.get() * factor;
	}

	public static Constraint factor(Constraint constraint, float factor) {
		return new MultiplierConstraint(factor, constraint);
	}

	@Override
	public MultiplierConstraint flatten() {
		if (constraint instanceof MultiplierConstraint) {
			MultiplierConstraint constraint = (MultiplierConstraint) this.constraint;
			return new MultiplierConstraint(this.factor * constraint.factor, constraint.constraint);
		}
		return this;
	}

}