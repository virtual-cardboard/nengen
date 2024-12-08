package common.colour;

import common.math.Vector4f;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColourTest {

    @Test
    public void testRgb() {
        int colour = Colour.rgb(255, 0, 0);
        assertEquals(255, Colour.r(colour));
        assertEquals(0, Colour.g(colour));
        assertEquals(0, Colour.b(colour));
        assertEquals(255, Colour.a(colour));
    }

    @Test
    public void testRgba() {
        int colour = Colour.rgba(255, 0, 0, 128);
        assertEquals(255, Colour.r(colour));
        assertEquals(0, Colour.g(colour));
        assertEquals(0, Colour.b(colour));
        assertEquals(128, Colour.a(colour));
    }

    @Test
    public void testHsl() {
        int colour = Colour.hsl(0, 1, 0.5f);
        assertEquals(255, Colour.r(colour));
        assertEquals(0, Colour.g(colour));
        assertEquals(0, Colour.b(colour));
        assertEquals(255, Colour.a(colour));
    }

    @Test
    public void testNormalizedR() {
        int colour = Colour.rgb(255, 0, 0);
        assertEquals(1.0f, Colour.normalizedR(colour));
        assertEquals(0.0f, Colour.normalizedG(colour));
        assertEquals(0.0f, Colour.normalizedB(colour));
        assertEquals(1.0f, Colour.normalizedA(colour));
    }

    @Test
    public void testNormalizedG() {
        int colour = Colour.rgb(0, 255, 0);
        assertEquals(0.0f, Colour.normalizedR(colour));
        assertEquals(1.0f, Colour.normalizedG(colour));
        assertEquals(0.0f, Colour.normalizedB(colour));
        assertEquals(1.0f, Colour.normalizedA(colour));
    }

    @Test
    public void testNormalizedB() {
        int colour = Colour.rgb(0, 0, 255);
        assertEquals(0.0f, Colour.normalizedR(colour));
        assertEquals(0.0f, Colour.normalizedG(colour));
        assertEquals(1.0f, Colour.normalizedB(colour));
        assertEquals(1.0f, Colour.normalizedA(colour));
    }

    @Test
    public void testNormalizedA() {
        int colour = Colour.rgba(0, 0, 0, 255);
        assertEquals(0.0f, Colour.normalizedR(colour));
        assertEquals(0.0f, Colour.normalizedG(colour));
        assertEquals(0.0f, Colour.normalizedB(colour));
        assertEquals(1.0f, Colour.normalizedA(colour));
    }

    @Test
    public void testToVector() {
        int colour = Colour.rgba(255, 0, 0, 128);
        Vector4f vector = Colour.toVector(colour);
        assertEquals(255, vector.x(), 0.01);
        assertEquals(0, vector.y(), 0.01);
        assertEquals(0, vector.z(), 0.01);
        assertEquals(128, vector.w(), 0.01);
    }

    @Test
    public void testToRangedVector() {
        int colour = Colour.rgba(255, 0, 0, 128);
        Vector4f vector = Colour.toRangedVector(colour);
        assertEquals(1.0f, vector.x(), 0.01);
        assertEquals(0.0f, vector.y(), 0.01);
        assertEquals(0.0f, vector.z(), 0.01);
        assertEquals(0.5f, vector.w(), 0.01);
    }
}
