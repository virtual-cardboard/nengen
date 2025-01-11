package visuals.constraint.position;

import visuals.constraint.Constraint;
import visuals.constraint.posdim.AdditivePosDimConstraint;
import visuals.constraint.posdim.PosDimConstraint;

public interface PositionConstraint extends Constraint {

	public default PosDimConstraint add(Constraint c) {
		return new AdditivePosDimConstraint(this, c);
	}

}