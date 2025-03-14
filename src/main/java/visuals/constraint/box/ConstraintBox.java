package visuals.constraint.box;

import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

import common.math.Vector2f;
import visuals.constraint.Constraint;

public class ConstraintBox {

	private final Constraint x, y, w, h;

	public ConstraintBox(Constraint x, Constraint y, Constraint w, Constraint h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public ConstraintBox(ConstraintCoordinate coordinate, ConstraintSize size) {
		this(coordinate.x(), coordinate.y(), size.w(), size.h());
	}

	public ConstraintBox(Constraint x, Constraint y, ConstraintSize size) {
		this(x, y, size.w(), size.h());
	}

	public ConstraintBox(ConstraintCoordinate coordinate, Constraint w, Constraint h) {
		this(coordinate.x(), coordinate.y(), w, h);
	}

	public ConstraintBoxValue value() {
		return new ConstraintBoxValue(x.get(), y.get(), w.get(), h.get());
	}

	public Constraint x() {
		return x;
	}

	public Constraint y() {
		return y;
	}

	public Constraint w() {
		return w;
	}

	public Constraint h() {
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

	public ConstraintBox translate(Constraint x, Constraint y) {
		return new ConstraintBox(this.x.add(x), this.y.add(y), w, h);
	}

	public ConstraintBox translate(float x, float y) {
		return translate(absolute(x), absolute(y));
	}

	public ConstraintBox translate(Vector2f v) {
		return translate(v.x(), v.y());
	}

}
