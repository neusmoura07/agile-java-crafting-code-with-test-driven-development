package sis.UI;
import junit.framework.*;
import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

import sis.studentinfo.*;
import sis.testing.Date;

import static sis.UI.CoursesPanel.*;

public class CoursesPanelTest extends TestCase {
    private CoursesPanel panel;
    private boolean wasClicked;

    protected void setUp() {
        panel = new CoursesPanel();
    }
    public void testCreate() {
        assertEmptyTable(COURSES_LIST_NAME);
        assertButtonText(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);

        String[] fields =
                { FieldCatalog.DEPARTMENT_FIELD_NAME,
                        FieldCatalog.NUMBER_FIELD_NAME,
                        FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };
        assertFields(fields);

        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertEquals(ADD_BUTTON_MNEMONIC, button.getMnemonic());
    }

    public void testAddButtonClick() {
        JButton button = panel.getButton(ADD_BUTTON_NAME);
        wasClicked = false;
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wasClicked = true;
            }
        });
        button.doClick();
        assertTrue(wasClicked);
    }

    public void testAddCourse() {
        Course course = new Course("ENGL", "101");
        panel.addCourse(course);
        JTable table = panel.getTable(COURSES_TABLE_NAME);
        CoursesTableModel model = (CoursesTableModel)table.getModel();
        assertSame(course, model.get(0));
    }

    public void testEnalbeDisable() {
        panel.setEnabled(ADD_BUTTON_NAME, true);
        JButton button = panel.getButton(ADD_BUTTON_NAME);
        assertTrue(button.isEnabled());

        panel.setEnabled(ADD_BUTTON_NAME, false);
        assertFalse(button.isEnabled());
    }

    public void testAddListener() throws Exception {
        KeyListener listener = new KeyAdapter() {};
        panel.addFieldListener(DEPARTMENT_FIELD_NAME, listener);
        JTextField field = panel.getField(DEPARTMENT_FIELD_NAME);
        KeyListener[] listeners = field.getKeyListeners();
        assertEquals(1, listeners.length);
        assertSame(listener, listeners[0]);
    }

    private void assertEmptyTable(String name) {
        JTable table = panel.getTable(name);
        assertEquals(0, table.getModel().getRowCount());
    }

    private void verifyEffectiveDate() {
        assertLabelText(EFFECTIVE_DATE_LABEL_NAME,
                EFFECTIVE_DATE_LABEL_TEXT);
        JFormattedTextField dateField =
                (JFormattedTextField)panel.getField(EFFECTIVE_DATE_FIELD_NAME);
        DateFormatter formatter = (DateFormatter)dateField.getFormatter();
        SimpleDateFormat format = (SimpleDateFormat)formatter.getFormat();
        assertEquals("MM/dd/yy", format.toPattern());
        assertEquals(Date.class, dateField.getValue().getClass());
    }

    private void assertLabelText(String name, String text) {
        JLabel label = panel.getLabel(name);
        assertEquals(text, label.getText());
    }
    private void assertEmptyField(String name) {
        JTextField field = panel.getField(name);
        assertEquals("", field.getText());
    }
    private void assertEmptyList(String name) {
        JList list = panel.getList(name);
        assertEquals(0, list.getModel().getSize());
    }
    private void assertButtonText(String name, String text) {
        JButton button = panel.getButton(name);
        assertEquals(text, button.getText());
    }

    private void assertFields(String[] fieldNames) {FieldCatalog catalog = new FieldCatalog();
        for (String fieldName: fieldNames) {
            assertNotNull(panel.getField(fieldName));
            Field fieldSpec = catalog.get(fieldName);
            assertLabelText(fieldSpec.getLabelName(), fieldSpec.getLabel());
        }
    }
}
