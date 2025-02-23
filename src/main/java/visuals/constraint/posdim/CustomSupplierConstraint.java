package visuals.constraint.posdim;

import java.util.function.Supplier;

import visuals.constraint.Constraint;

/**
 * A {@link Constraint} that represents a custom {@link Supplier} of {@link Float}.
 *
 * @see Constraint
 */
public class CustomSupplierConstraint implements Constraint {

	private final Supplier<Float> supplier;

	public CustomSupplierConstraint(Supplier<Float> supplier) {
		this.supplier = supplier;
	}

	@Override
	public float get() {
		return supplier.get();
	}

	public static Constraint custom(Supplier<Float> supplier) {
		return new CustomSupplierConstraint(supplier);
	}

}
