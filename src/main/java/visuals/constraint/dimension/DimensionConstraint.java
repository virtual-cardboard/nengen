package visuals.constraint.dimension;

import visuals.constraint.Constraint;
import visuals.constraint.posdim.AdditivePosDimConstraint;
import visuals.constraint.posdim.MultiplierPosDimConstraint;
import visuals.constraint.posdim.PosDimConstraint;

/**
 * A GuiDimensionConstraint is used to calculate the width or height of a GUI component.
 */
public interface DimensionConstraint extends Constraint {

	public default PosDimConstraint add(DimensionConstraint c) {
		return new AdditivePosDimConstraint(this, c);
	}

}
