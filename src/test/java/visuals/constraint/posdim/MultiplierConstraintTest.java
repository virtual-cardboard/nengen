package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

class MultiplierConstraintTest {

    @Test
    void testGetWithMultipleConstraints() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        Constraint c3 = absolute(2f);
        MultiplierConstraint multiplierConstraint = new MultiplierConstraint(c1, c2, c3);
        assertEquals(100f, multiplierConstraint.get());
    }

    @Test
    void testGetWithZero() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(0f);
        Constraint c3 = absolute(5f);
        MultiplierConstraint multiplierConstraint = new MultiplierConstraint(c1, c2, c3);
        assertEquals(0f, multiplierConstraint.get());
    }

    @Test
    void testNeg() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        MultiplierConstraint multiplierConstraint = new MultiplierConstraint(c1, c2); // 50
        Constraint negated = multiplierConstraint.neg(); // Should be -50
        assertTrue(negated instanceof MultiplierConstraint); // neg() wraps in a new MultiplierConstraint
        assertEquals(-50f, negated.get());
    }

    @Test
    void testFlatten() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        Constraint c3 = absolute(2f);
        MultiplierConstraint innerMultiplier = new MultiplierConstraint(c2, c3); // 5 * 2 = 10
        MultiplierConstraint outerMultiplier = new MultiplierConstraint(c1, innerMultiplier); // 10 * 10 = 100

        Constraint flattened = outerMultiplier.flatten();
        assertTrue(flattened instanceof MultiplierConstraint);
        assertEquals(100f, flattened.get());
        // The current implementation of MultiplierConstraint flattens during construction.
        // So, outerMultiplier.toString() would already show the flattened form.
        // A more direct test of flatten would involve creating a MultiplierConstraint
        // with a nested MultiplierConstraint without relying on the constructor to flatten it,
        // which is not straightforward with the current API.
        // However, we can verify the toString reflects the flattened structure.
        assertEquals("MultiplierConstraint[constraints=[AbsoluteConstraint[value=10.0], AbsoluteConstraint[value=5.0], AbsoluteConstraint[value=2.0]]]", flattened.toString());
    }

    @Test
    void testFlattenWithNoNestedMultiplierConstraints() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        MultiplierConstraint multiplierConstraint = new MultiplierConstraint(c1, c2);
        Constraint flattened = multiplierConstraint.flatten();
        // If no flattening is needed (already flat), it might return itself or an equivalent.
        // The current implementation always creates a new list in flatten if not an AbsoluteConstraint(1)
        // or if constraints size is not 1.
        // So, it won't be the same instance unless it's a very specific case.
        assertTrue(flattened instanceof MultiplierConstraint);
        assertEquals(50f, flattened.get());
    }

    @Test
    void testFlattenToAbsoluteConstraintOne() {
        MultiplierConstraint mc = new MultiplierConstraint(absolute(1f));
        Constraint flattened = mc.flatten();
        assertTrue(flattened instanceof AbsoluteConstraint);
        assertEquals(1f, flattened.get());
    }

}
