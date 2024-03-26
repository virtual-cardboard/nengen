package visuals.constraint.posdim;

import visuals.constraint.Constraint;
import visuals.constraint.dimension.DimensionConstraint;

/**
 */
public class MultiplierPosDimConstraint implements PosDimConstraint {

	private final float factor;
	private final Constraint constraint;

	public MultiplierPosDimConstraint(float factor, Constraint constraint) {
		this.factor = factor;
		this.constraint = constraint;
	}

	@Override
	public float get() {
		return constraint.get() * factor;
	}

	public static PosDimConstraint factor(Constraint constraint, float factor) {
		return new MultiplierPosDimConstraint(factor, constraint);
	}

}