package visuals.constraint.posdim;

import java.util.function.Supplier;

import visuals.constraint.Constraint;

/**
 * A {@link Constraint} that represents a custom {@link Supplier} of {@link Float}.
 *
 * @see Constraint
 */
public class CustomSupplierConstraint implements Constraint {

	private final String name;
	private final Supplier<Float> supplier;

	public CustomSupplierConstraint(String name, Supplier<Float> supplier) {
		this.name = name;
		this.supplier = supplier;
	}

	@Override
	public float get() {
		return supplier.get();
	}

	public static Constraint custom(String name, Supplier<Float> supplier) {
		return new CustomSupplierConstraint(name, supplier);
	}

	@Override
	public String toString() {
		return name;
	}

}
