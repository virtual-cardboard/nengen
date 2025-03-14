package visuals.constraint.posdim;

import visuals.constraint.Constraint;

/*
 * A {@link PosDimConstraint} that represents an absolute {@link Float} value.
 */
public class AbsoluteConstraint implements Constraint {

	private final float value;

	public AbsoluteConstraint(float value) {
		this.value = value;
	}

	@Override
	public float get() {
		return value;
	}

	public static Constraint absolute(float value) {
		return new AbsoluteConstraint(value);
	}

	public static Constraint zero() {
		return absolute(0);
	}

}