package sis.UI;

import junit.framework.*;
import javax.swing.*;
import java.awt.*;
import sis.studentinfo.*;

public class SisTest extends TestCase {

    private Sis sis;
    private JFrame frame;

    protected void setUp() {
        sis = new Sis();
        frame = sis.getFrame();
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(sis.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(sis.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertTrue(containsCoursesPanel(frame));
        assertNotNull(Util.getComponent(frame, CoursesPanel.NAME));
    }

    public void testAddCourse() {
        CoursesPanel panel = (CoursesPanel) Util.getComponent(frame, CoursesPanel.NAME);
        panel.setText(CoursesPanel.DEPARTMENT_FIELD_NAME, "300");

        JButton button = panel.getButton(CoursesPanel.ADD_BUTTON_NAME);
        button.doClick();

        Course course = panel.getCourse(0);
        assertEquals("MATH", course.getDepartment());
        assertEquals("300", course.getNumber());
    }

    private boolean containsCoursesPanel(JFrame frame) {
        Container pane = frame.getContentPane();
        for (Component component: pane.getComponents())
            if (component instanceof CoursesPanel)
                return true;
        return false;
    }

    public void testShow() {
        sis.show();
        assertTrue(frame.isVisible());
    }


    protected void tearDown() {
        sis.close();
    }
}
