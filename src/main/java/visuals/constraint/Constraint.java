package visuals.constraint;

import visuals.constraint.posdim.AdditiveConstraint;
import visuals.constraint.posdim.MultiplierConstraint;
import visuals.constraint.posdim.NegativeConstraint;

/**
 * A GUIConstraint is used to calculate the x, y, width, or height of a GUI component, given the start and end of the
 * parent GUI component.
 */
public interface Constraint {

	float get();

	default Constraint add(Constraint c) {
		return new AdditiveConstraint(this, c).flatten();
	}


	default Constraint multiply(float factor) {
		return new MultiplierConstraint(factor, this).flatten();
	}

	default Constraint neg() {
		return new NegativeConstraint(this).flatten();
	}

	/**
	 * Flatten constraints to reduce complexity and size of the constraint. Override this function to flatten your own
	 * constraints.
	 * <p>
	 * e.g. an {@link AdditiveConstraint} can be flattened to reduce any sub-{@link AdditiveConstraint}s into itself.
	 *
	 * @return either a new flattened constraint or just itself if it can't be flattened
	 */
	default Constraint flatten() {
		return this;
	}
}