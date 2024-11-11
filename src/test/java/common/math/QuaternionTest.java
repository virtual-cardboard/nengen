package common.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuaternionTest {

    @Test
    public void testNormalize() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        Quaternion normalized = q.normalize();
        float magnitude = (float) Math.sqrt(1*1 + 2*2 + 3*3 + 4*4);
        assertEquals(1 / magnitude, normalized.getS(), 0.0001);
        assertEquals(2 / magnitude, normalized.getV().x(), 0.0001);
        assertEquals(3 / magnitude, normalized.getV().y(), 0.0001);
        assertEquals(4 / magnitude, normalized.getV().z(), 0.0001);
    }

    @Test
    public void testGetConjugate() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        Quaternion conjugate = q.getConjugate();
        assertEquals(1, conjugate.getS(), 0.0001);
        assertEquals(-2, conjugate.getV().x(), 0.0001);
        assertEquals(-3, conjugate.getV().y(), 0.0001);
        assertEquals(-4, conjugate.getV().z(), 0.0001);
    }

    @Test
    public void testGetInverse() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        Quaternion inverse = q.getInverse();
        float magnitude = (float) Math.sqrt(1*1 + 2*2 + 3*3 + 4*4);
        float magnitudeSquared = magnitude * magnitude;
        assertEquals(1 / magnitudeSquared, inverse.getS(), 0.0001);
        assertEquals(-2 / magnitudeSquared, inverse.getV().x(), 0.0001);
        assertEquals(-3 / magnitudeSquared, inverse.getV().y(), 0.0001);
        assertEquals(-4 / magnitudeSquared, inverse.getV().z(), 0.0001);
    }

    @Test
    public void testScale() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        Quaternion scaled = q.scale(2);
        assertEquals(2, scaled.getS(), 0.0001);
        assertEquals(4, scaled.getV().x(), 0.0001);
        assertEquals(6, scaled.getV().y(), 0.0001);
        assertEquals(8, scaled.getV().z(), 0.0001);
    }

    @Test
    public void testDot() {
        Quaternion q1 = new Quaternion(1, 2, 3, 4);
        Quaternion q2 = new Quaternion(5, 6, 7, 8);
        float dot = q1.dot(q2);
        assertEquals(1*5 + 2*6 + 3*7 + 4*8, dot, 0.0001);
    }

    @Test
    public void testAngleBetween() {
        Quaternion q1 = new Quaternion(1, 0, 0, 0);
        Quaternion q2 = new Quaternion(0, 1, 0, 0);
        float angle = q1.angleBetween(q2);
        assertEquals(Math.PI / 2, angle, 0.0001);
    }

    @Test
    public void testMultiply() {
        Quaternion q1 = new Quaternion(1, 2, 3, 4);
        Quaternion q2 = new Quaternion(5, 6, 7, 8);
        Quaternion result = q1.multiply(q2);
        assertEquals(-60, result.getS(), 0.0001);
        assertEquals(12, result.getV().x(), 0.0001);
        assertEquals(30, result.getV().y(), 0.0001);
        assertEquals(24, result.getV().z(), 0.0001);
    }

    @Test
    public void testToRotationMatrix() {
        Quaternion q = new Quaternion(1, 0, 1, 0).normalize();
        Matrix4f matrix = q.toRotationMatrix();
        assertEquals(0, matrix.m00, 0.0001);
        assertEquals(0, matrix.m01, 0.0001);
        assertEquals(1, matrix.m02, 0.0001);
        assertEquals(0, matrix.m10, 0.0001);
        assertEquals(1, matrix.m11, 0.0001);
        assertEquals(0, matrix.m12, 0.0001);
        assertEquals(-1, matrix.m20, 0.0001);
        assertEquals(0, matrix.m21, 0.0001);
        assertEquals(0, matrix.m22, 0.0001);
    }

    @Test
    public void testFromMatrix() {
        Matrix4f matrix = new Matrix4f();
        matrix.m00 = 0;
        matrix.m01 = 0;
        matrix.m02 = 1;
        matrix.m10 = 0;
        matrix.m11 = 1;
        matrix.m12 = 0;
        matrix.m20 = -1;
        matrix.m21 = 0;
        matrix.m22 = 0;
        Quaternion q = Quaternion.fromMatrix(matrix);
        assertEquals(0.7071, q.getS(), 0.0001);
        assertEquals(0, q.getV().x(), 0.0001);
        assertEquals(0.7071, q.getV().y(), 0.0001);
        assertEquals(0, q.getV().z(), 0.0001);
    }

    @Test
    public void testInterpolate() {
        Quaternion q1 = new Quaternion(1, 0, 0, 0);
        Quaternion q2 = new Quaternion(0, 1, 0, 0);
        Quaternion result = Quaternion.interpolate(q1, q2, 0.5f);
        assertEquals(0.7071, result.getS(), 0.0001);
        assertEquals(0.7071, result.getV().x(), 0.0001);
        assertEquals(0, result.getV().y(), 0.0001);
        assertEquals(0, result.getV().z(), 0.0001);
    }

    @Test
    public void testMagnitude() {
        Quaternion q = new Quaternion(1, 2, 3, 4);
        float magnitude = q.magnitude();
        assertEquals((float) Math.sqrt(1*1 + 2*2 + 3*3 + 4*4), magnitude, 0.0001);
    }
}
