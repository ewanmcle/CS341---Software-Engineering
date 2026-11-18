package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ewan.Main;

/**
 * JUnit tests for ewan.Main.
 *
 * Main keeps its UI in private static fields, so these tests read them
 * through reflection. The tests open the real window briefly and close it
 * afterwards. They do NOT click the input/output buttons, because those open
 * a file dialog that waits for a person to pick a file.
 */
public class MainTest {

    // HELPER: READ ONE OF MAIN'S PRIVATE STATIC FIELDS
    @SuppressWarnings("unchecked")
    private static <T> T getStatic(String name) throws Exception {
        Field f = Main.class.getDeclaredField(name);
        f.setAccessible(true);
        return (T) f.get(null);
    }

    @BeforeEach
    public void openWindow() {
        // Skip GUI tests on a machine with no screen (e.g. a CI server)
        assumeFalse(GraphicsEnvironment.isHeadless(), "no display available");
        Main.main(new String[0]);
    }

    @AfterEach
    public void closeWindow() throws Exception {
        if (GraphicsEnvironment.isHeadless()) return;
        JFrame frame = getStatic("jframeWindow");
        if (frame != null) frame.dispose();
    }

    // ---------- WINDOW ----------

    @Test
    public void mainCreatesVisibleWindow() throws Exception {
        JFrame frame = getStatic("jframeWindow");
        assertNotNull(frame);
        assertTrue(frame.isVisible());
    }

    @Test
    public void windowExitsOnClose() throws Exception {
        JFrame frame = getStatic("jframeWindow");
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
    }

    // ---------- PANEL ----------

    @Test
    public void panelIsInsideWindow() throws Exception {
        JFrame frame = getStatic("jframeWindow");
        JPanel panel = getStatic("panel");
        assertSame(frame.getContentPane(), panel.getParent());
    }

    @Test
    public void panelUsesThreeRowGrid() throws Exception {
        JPanel panel = getStatic("panel");
        assertTrue(panel.getLayout() instanceof GridLayout);
        assertEquals(3, ((GridLayout) panel.getLayout()).getRows());
    }

    @Test
    public void panelHasExpectedSizeAndColor() throws Exception {
        JPanel panel = getStatic("panel");
        assertEquals(new Dimension(500, 150), panel.getPreferredSize());
        assertEquals(Color.DARK_GRAY, panel.getBackground());
    }

    // ---------- BUTTONS ----------

    @Test
    public void buttonsHaveCorrectLabels() throws Exception {
        JButton input = getStatic("inputBtn");
        JButton output = getStatic("outputBtn");
        JButton compute = getStatic("computeBtn");
        assertEquals("Specify Input Text File", input.getText());
        assertEquals("Specify Output Text File", output.getText());
        assertEquals("Perform Work", compute.getText());
    }

    @Test
    public void buttonsAreInPanelInOrder() throws Exception {
        JPanel panel = getStatic("panel");
        assertEquals(3, panel.getComponentCount());
        assertSame(getStatic("inputBtn"), panel.getComponent(0));
        assertSame(getStatic("outputBtn"), panel.getComponent(1));
        assertSame(getStatic("computeBtn"), panel.getComponent(2));
    }

    @Test
    public void eachButtonHasOneListener() throws Exception {
        JButton input = getStatic("inputBtn");
        JButton output = getStatic("outputBtn");
        JButton compute = getStatic("computeBtn");
        assertEquals(1, input.getActionListeners().length);
        assertEquals(1, output.getActionListeners().length);
        assertEquals(1, compute.getActionListeners().length);
    }

    // ---------- COMPUTE ----------

    @Test
    public void computeSomethingPrintsMessage() {
        String printed = captureOutput(Main::computeSomething);
        assertEquals("now computing", printed.trim());
    }

    @Test
    public void computeButtonClickRunsComputeSomething() throws Exception {
        JButton compute = getStatic("computeBtn");
        String printed = captureOutput(compute::doClick);
        assertEquals("now computing", printed.trim());
    }

    @Test
    public void computeSomethingDoesNotChangeChosenFiles() throws Exception {
        File readBefore = getStatic("fileToRead");
        File saveBefore = getStatic("fileToSave");
        captureOutput(Main::computeSomething);
        assertSame(readBefore, getStatic("fileToRead"));
        assertSame(saveBefore, getStatic("fileToSave"));
    }

    // HELPER: RUN CODE AND RETURN WHAT IT PRINTED TO System.out
    private static String captureOutput(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}
