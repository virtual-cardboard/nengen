package visuals.constraint.posdim;

import java.util.function.Supplier;

/**
 * A {@link PosDimConstraint} that represents a custom {@link Supplier} of
 * {@link Float}.
 *
 * @see PosDimConstraint
 */
public class CustomSupplierPosDimConstraint implements PosDimConstraint {

	private final Supplier<Float> supplier;

	public CustomSupplierPosDimConstraint(Supplier<Float> supplier) {
		this.supplier = supplier;
	}

	@Override
	public float get() {
		return supplier.get();
	}

	public static PosDimConstraint custom(Supplier<Float> supplier) {
		return new CustomSupplierPosDimConstraint(supplier);
	}

}
