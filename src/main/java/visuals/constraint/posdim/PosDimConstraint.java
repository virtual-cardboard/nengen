package visuals.constraint.posdim;

import visuals.constraint.dimension.DimensionConstraint;
import visuals.constraint.position.PositionConstraint;

/**
 * A constraint that works as either a {@link PositionConstraint} or a {@link DimensionConstraint}.
 *
 * @see PositionConstraint
 * @see DimensionConstraint
 */
public interface PosDimConstraint extends PositionConstraint, DimensionConstraint {

}