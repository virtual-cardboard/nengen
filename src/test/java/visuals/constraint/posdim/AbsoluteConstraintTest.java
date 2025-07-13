package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

class AbsoluteConstraintTest {

    @Test
    void testGet() {
        AbsoluteConstraint constraint = new AbsoluteConstraint(20f);
        assertEquals(20f, constraint.get());
    }

    @Test
    void testAbsolute() {
        Constraint constraint = absolute(30f);
        assertTrue(constraint instanceof AbsoluteConstraint);
        assertEquals(30f, constraint.get());
    }

}
