package visuals.constraint;

/**
 * {@link ConstraintCoordinateValue} objects are temporary and are used to store the values of a
 * {@link ConstraintCoordinate} at a given time
 */
public class ConstraintCoordinateValue {

	private final float x;
	private final float y;

	/**
	 * Creates a new {@link ConstraintCoordinateValue} with the given values
	 *
	 * @param x the x value
	 * @param y the y value
	 */
	public ConstraintCoordinateValue(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x value
	 */
	public float x() {
		return x;
	}

	/**
	 * @return the y value
	 */
	public float y() {
		return y;
	}

}
