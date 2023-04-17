package nengen;

import org.junit.Test;
import static org.junit.Assert.*;

public class NengenConfigurationTest {

    @Test
    public void testGetters() {
        NengenConfiguration config = new NengenConfiguration();
        config.setWindowDim(800, 600);
        config.setWindowName("Test Window");
        config.setFrameRate(30);
        config.setTickRate(10);
        config.setResizable(false);
        config.setFullscreen(false);

        assertEquals(800, config.getWidth());
        assertEquals(600, config.getHeight());
        assertEquals("Test Window", config.getWindowName());
        assertEquals(30, config.getFrameRate());
        assertEquals(10, config.getTickRate());
        assertFalse(config.isResizable());
        assertFalse(config.isFullscreen());
    }

    @Test
    public void testToString() {
        NengenConfiguration config = new NengenConfiguration();
        config.setWindowDim(800, 600);
        config.setWindowName("Test Window");
        config.setFrameRate(30);
        config.setTickRate(10);
        config.setResizable(false);
        config.setFullscreen(false);

        String expected = "NengenConfiguration [width=800, height=600, frameRate=30, tickRate=10, resizable=false, fullscreen=false, windowName=Test Window]";
        assertEquals(expected, config.toString());
    }
}