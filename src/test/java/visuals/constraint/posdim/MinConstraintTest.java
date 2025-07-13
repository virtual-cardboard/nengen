package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;
import static visuals.constraint.posdim.MinConstraint.min;

class MinConstraintTest {

    @Test
    void testGetWithNegativeValues() {
        Constraint c1 = absolute(-10f);
        Constraint c2 = absolute(-100f);
        MinConstraint minConstraint = new MinConstraint(c1, c2);
        assertEquals(-100f, minConstraint.get());
    }

    @Test
    void testMinStaticFactory() {
        Constraint c1 = absolute(200f);
        Constraint c2 = absolute(20f);
        Constraint minConstraint = min(c1, c2);
        assertTrue(minConstraint instanceof MinConstraint);
        assertEquals(20f, minConstraint.get());
    }

}
