package visuals.constraint.position;

import visuals.constraint.Constraint;
import visuals.constraint.posdim.AdditivePosDimConstraint;
import visuals.constraint.posdim.CustomSupplierPosDimConstraint;
import visuals.constraint.posdim.MultiplierPosDimConstraint;
import visuals.constraint.posdim.PosDimConstraint;

public interface PositionConstraint extends Constraint {

	public default PosDimConstraint add(PositionConstraint c) {
		return new AdditivePosDimConstraint(this, c);
	}

}