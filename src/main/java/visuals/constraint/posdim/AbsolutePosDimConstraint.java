package visuals.constraint.posdim;

/*
 * A {@link PosDimConstraint} that represents an absolute {@link Float} value.
 */
public class AbsolutePosDimConstraint implements PosDimConstraint {

	private final float value;

	public AbsolutePosDimConstraint(float value) {
		this.value = value;
	}

	@Override
	public float get() {
		return value;
	}

	public static PosDimConstraint absolute(float value) {
		return new AbsolutePosDimConstraint(value);
	}

}