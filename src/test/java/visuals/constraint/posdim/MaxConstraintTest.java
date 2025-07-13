package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;
import static visuals.constraint.posdim.MaxConstraint.max;

class MaxConstraintTest {

    @Test
    void testGetWithNegativeValues() {
        Constraint c1 = absolute(-10f);
        Constraint c2 = absolute(-100f);
        MaxConstraint maxConstraint = new MaxConstraint(c1, c2);
        assertEquals(-10f, maxConstraint.get());
    }

    @Test
    void testMaxStaticFactory() {
        Constraint c1 = absolute(20f);
        Constraint c2 = absolute(200f);
        Constraint maxConstraint = max(c1, c2);
        assertTrue(maxConstraint instanceof MaxConstraint);
        assertEquals(200f, maxConstraint.get());
    }

}
