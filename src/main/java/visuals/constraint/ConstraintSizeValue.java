package visuals.constraint;

import common.math.Vector2f;

/**
 * {@link ConstraintSizeValue} objects are temporary and are used to store the values of a {@link ConstraintBox} at a
 * given time
 */
public class ConstraintSizeValue {

	private final float w;
	private final float h;

	/**
	 * Creates a new {@link ConstraintSizeValue} with the given values
	 *
	 * @param w the width value
	 * @param h the height value
	 */
	public ConstraintSizeValue(float w, float h) {
		this.w = w;
		this.h = h;
	}

	/**
	 * @return the width value
	 */
	public float w() {
		return w;
	}

	/**
	 * @return the height value
	 */
	public float h() {
		return h;
	}

	public Vector2f toVector() {
		return new Vector2f(w, h);
	}

}
