package visuals.constraint;

import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

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

	default Constraint add(float f) {
		return add(absolute(f));
	}

	default Constraint multiply(Constraint c) {
		return new MultiplierConstraint(c, this).flatten();
	}

	default Constraint multiply(float f) {
		return multiply(absolute(f));
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

	/**
	 * Get the size of the constraint, i.e. the number of constraints it depends on.
	 *
	 * @return the size of the constraint
	 */
	default int size() {
		return 1;
	}

}