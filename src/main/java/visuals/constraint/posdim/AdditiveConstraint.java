package visuals.constraint.posdim;

import java.util.ArrayList;
import java.util.List;

import visuals.constraint.Constraint;

/**
 * A {@link Constraint} that represents the sum of two other {@link Constraint}s.
 *
 * @see Constraint
 */
public class AdditiveConstraint implements Constraint {

	private final List<Constraint> constraintList = new ArrayList<Constraint>();

	public AdditiveConstraint(Constraint... constraints) {
		for (Constraint c : constraints) {
			constraintList.add(c);
		}
	}

	public AdditiveConstraint(List<Constraint> constraints) {
		this.constraintList.addAll(constraints);
	}

	public float get() {
		float sum = 0;
		for (Constraint c : constraintList) {
			sum += c.get();
		}
		return sum;
	}

	@Override
	public Constraint flatten() {
		if (constraintList.size() == 1) {
			return (AdditiveConstraint) constraintList.get(0);
		}

		boolean isFlattened = false;
		List<Constraint> flattened = new ArrayList<Constraint>();
		for (Constraint c : constraintList) {
			if (c instanceof AdditiveConstraint) {
				isFlattened = true;
				flattened.addAll(((AdditiveConstraint) c).constraintList);
			} else {
				flattened.add(c);
			}
		}
		if (isFlattened) {
			return new AdditiveConstraint(flattened);
		}
		return this;
	}

}
