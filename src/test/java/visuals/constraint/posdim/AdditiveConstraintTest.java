package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditiveConstraintTest {

	@Test
	void testGetWithSingleAbsoluteConstraint() {
		AdditiveConstraint constraint = new AdditiveConstraint(new AbsoluteConstraint(5));
		assertEquals(5, constraint.get());
	}

	@Test
	void testGetWithMultipleAbsoluteConstraints() {
		AdditiveConstraint constraint = new AdditiveConstraint(new AbsoluteConstraint(5), new AbsoluteConstraint(10));
		assertEquals(15, constraint.get());
	}

	@Test
	void testGetWithCustomSupplierConstraint() {
		AdditiveConstraint constraint = new AdditiveConstraint(new CustomSupplierConstraint("test", () -> 5f));
		assertEquals(5, constraint.get());
	}

	@Test
	void testGetWithMixedConstraints() {
		AdditiveConstraint constraint = new AdditiveConstraint(new AbsoluteConstraint(5), new CustomSupplierConstraint("test", () -> 10f));
		assertEquals(15, constraint.get());
	}

	@Test
	void testNeg() {
		AdditiveConstraint constraint = new AdditiveConstraint(new AbsoluteConstraint(5), new CustomSupplierConstraint("test", () -> 10f));
		assertEquals(-15, constraint.neg().get());
	}

	@Test
	void testFlatten() {
		AdditiveConstraint constraint = new AdditiveConstraint(new AbsoluteConstraint(5));
		assertEquals(5, constraint.flatten().get());
	}

}
