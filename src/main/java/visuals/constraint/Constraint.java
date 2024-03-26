package visuals.constraint;

import visuals.constraint.posdim.AdditivePosDimConstraint;
import visuals.constraint.posdim.MultiplierPosDimConstraint;
import visuals.constraint.posdim.PosDimConstraint;

/**
 * A GUIConstraint is used to calculate the x, y, width, or height of a GUI component, given the start and end of the
 * parent GUI component.
 */
public abstract interface Constraint {

	public abstract float get();

	public default PosDimConstraint multiply(float factor) {
		return new MultiplierPosDimConstraint(factor, this);
	}

}