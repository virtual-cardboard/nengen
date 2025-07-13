package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;
import static visuals.constraint.posdim.NegativeConstraint.negative;

class NegativeConstraintTest {

    @Test
    void testGetWithNegativeInput() {
        Constraint c1 = absolute(-20f);
        NegativeConstraint negativeConstraint = new NegativeConstraint(c1);
        assertEquals(20f, negativeConstraint.get());
    }

    @Test
    void testNegativeStaticFactory() {
        Constraint c1 = absolute(30f);
        Constraint negativeConstraint = negative(c1);
        assertTrue(negativeConstraint instanceof NegativeConstraint);
        assertEquals(-30f, negativeConstraint.get());
    }

    @Test
    void testFlatten() {
        Constraint c1 = absolute(40f);
        NegativeConstraint nc1 = new NegativeConstraint(c1); // -40f
        NegativeConstraint nc2 = new NegativeConstraint(nc1); // --40f = 40f
        Constraint flattened = nc2.flatten();
        // Flattening a NegativeConstraint(NegativeConstraint(abs)) should return the original abs.
        assertTrue(flattened instanceof AbsoluteConstraint);
        assertEquals(40f, flattened.get());
    }

    @Test
    void testFlattenNonNested() {
        Constraint c1 = absolute(50f);
        NegativeConstraint negativeConstraint = new NegativeConstraint(c1);
        Constraint flattened = negativeConstraint.flatten();
        // Flattening a simple NegativeConstraint should return itself.
        assertSame(negativeConstraint, flattened);
        assertEquals(-50f, flattened.get());
    }

}
