package visuals.constraint.posdim;

import org.junit.jupiter.api.Test;
import visuals.constraint.Constraint;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static visuals.constraint.posdim.CustomSupplierConstraint.custom;

class CustomSupplierConstraintTest {

    @Test
    void testGetWithChangingSupplier() {
        AtomicInteger value = new AtomicInteger(10);
        Supplier<Float> supplier = () -> (float) value.getAndIncrement();
        CustomSupplierConstraint constraint = new CustomSupplierConstraint("ChangingSupplier", supplier);
        assertEquals(10f, constraint.get());
        assertEquals(11f, constraint.get());
        assertEquals(12f, constraint.get());
    }

    @Test
    void testCustomStaticFactory() {
        Supplier<Float> supplier = () -> 60f;
        Constraint constraint = custom("FactoryTest", supplier);
        assertTrue(constraint instanceof CustomSupplierConstraint);
        assertEquals(60f, constraint.get());
        assertEquals("FactoryTest", ((CustomSupplierConstraint) constraint).name());
    }

}
