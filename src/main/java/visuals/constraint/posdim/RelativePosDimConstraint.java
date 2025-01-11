package visuals.constraint.posdim;

/**
 * Represents a {@link PosDimConstraint} that requires a parent PosDimConstraint.
 *
 * @author Lunkle
 */
public abstract class RelativePosDimConstraint implements PosDimConstraint {

	protected PosDimConstraint parent;

	public RelativePosDimConstraint(PosDimConstraint parent) {
		this.parent = parent;
	}

}
