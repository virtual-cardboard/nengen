package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.AbsoluteConstraint.absolute;

class AdditiveConstraintTest {

    @Test
    void testGetWithMultipleConstraints() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        Constraint c3 = absolute(3f);
        AdditiveConstraint additiveConstraint = new AdditiveConstraint(c1, c2, c3);
        assertEquals(18f, additiveConstraint.get());
    }

    @Test
    void testFlatten() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        Constraint c3 = absolute(3f);
        AdditiveConstraint innerAdditive = new AdditiveConstraint(c2, c3); // 5 + 3 = 8
        AdditiveConstraint outerAdditive = new AdditiveConstraint(c1, innerAdditive); // 10 + 8 = 18
        Constraint flattened = outerAdditive.flatten();
        assertTrue(flattened instanceof AdditiveConstraint);
        assertEquals(18f, flattened.get());
        // Check that the flattened constraint doesn't contain nested AdditiveConstraints
        // This requires inspecting the internal structure or ensuring the string representation is simpler.
        // For now, we'll rely on the get() method and the type.
        // A more robust test might involve checking the number of sub-constraints if such a method existed.
    }

    @Test
    void testFlattenWithNoNestedAdditiveConstraints() {
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(5f);
        AdditiveConstraint additiveConstraint = new AdditiveConstraint(c1, c2);
        Constraint flattened = additiveConstraint.flatten();
        assertSame(additiveConstraint, flattened); // Should return itself if no flattening is needed
        assertEquals(15f, flattened.get());
    }

    @Test
    void testFlattenWithAbsoluteOnly() {
        // Flattening an AdditiveConstraint containing only AbsoluteConstraints
        // should result in a simplified AdditiveConstraint or potentially an AbsoluteConstraint
        // if all but one are zero (though the current implementation might not optimize to AbsoluteConstraint).
        Constraint c1 = absolute(10f);
        Constraint c2 = absolute(0f);
        AdditiveConstraint additive = new AdditiveConstraint(c1, c2);
        Constraint flattened = additive.flatten();
        assertTrue(flattened instanceof AdditiveConstraint); // or AbsoluteConstraint depending on impl.
        assertEquals(10f, flattened.get());
    }

}
