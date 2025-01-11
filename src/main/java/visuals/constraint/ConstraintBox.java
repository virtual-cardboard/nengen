package visuals.constraint;

import static visuals.constraint.posdim.AbsolutePosDimConstraint.absolute;

import common.math.Vector2f;
import visuals.constraint.dimension.DimensionConstraint;
import visuals.constraint.position.PositionConstraint;

public class ConstraintBox {

	private final PositionConstraint x;
	private final PositionConstraint y;
	private final DimensionConstraint w;
	private final DimensionConstraint h;

	public ConstraintBox(PositionConstraint x, PositionConstraint y, DimensionConstraint w, DimensionConstraint h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public ConstraintBox(ConstraintCoordinate coordinate, ConstraintSize size) {
		this(coordinate.x(), coordinate.y(), size.w(), size.h());
	}

	public ConstraintBox(PositionConstraint x, PositionConstraint y, ConstraintSize size) {
		this(x, y, size.w(), size.h());
	}

	public ConstraintBox(ConstraintCoordinate coordinate, DimensionConstraint w, DimensionConstraint h) {
		this(coordinate.x(), coordinate.y(), w, h);
	}

	public ConstraintBoxValue value() {
		return new ConstraintBoxValue(x.get(), y.get(), w.get(), h.get());
	}

	public PositionConstraint x() {
		return x;
	}

	public PositionConstraint y() {
		return y;
	}

	public DimensionConstraint w() {
		return w;
	}

	public DimensionConstraint h() {
		return h;
	}

	public boolean contains(ConstraintCoordinate coordinate) {
		return contains(coordinate.value().toVector());
	}

	public boolean contains(Vector2f point) {
		return x.get() <= point.x() && point.x() <= x.get() + w.get() &&
				y.get() <= point.y() && point.y() <= y.get() + h.get();
	}

	public ConstraintLine horizontal() {
		return new ConstraintLine(x, w);
	}

	public ConstraintLine vertical() {
		return new ConstraintLine(y, h);
	}

	public ConstraintCoordinate coordinate() {
		return new ConstraintCoordinate(x, y);
	}

	public ConstraintSize dimensions() {
		return new ConstraintSize(w, h);
	}

	public ConstraintSize size() {
		return new ConstraintSize(w, h);
	}

	public ConstraintBox translate(PositionConstraint x, PositionConstraint y) {
		return new ConstraintBox(this.x.add(x), this.y.add(y), w, h);
	}

	public ConstraintBox translate(float x, float y) {
		return translate(absolute(x), absolute(y));
	}

	public ConstraintBox translate(Vector2f v) {
		return translate(v.x(), v.y());
	}

	/**
	 * Treats the
	 *
	 * @param box the sub-box that is embedded into this
	 * @return new constraint box representing the embedded constraint box
	 */
	public ConstraintBox embed(ConstraintBox box) {
		return null;
//		return new ConstraintBox();
	}

}
