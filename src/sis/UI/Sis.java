package sis.UI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import sis.studentinfo.*;
import sis.util.ImageUtil;

public class Sis {
    static final int WIDTH = 350;
    static final int HEIGHT = 500;
    static final String COURSES_TITLE = "Course Listing";

    private CoursesPanel panel;
    private JFrame frame = new JFrame(COURSES_TITLE);
    public static void main(String[] args) {
        new Sis().show();
    }

    Sis() {
        initialize();
    }

    private void initialize() {
        createCoursePanel();
        createKeyListeners();
        createInputFilters();

        Image image = ImageUtil.create("/images/course.gif");
        frame.setIconImage(image);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    JFrame getFrame() {
        return frame;
    }

    void createCoursePanel() {
        panel = new CoursesPanel();
        panel.addCourseAddListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
    }

    private void addCourse() {
        Thread thread = new Thread() {
            public void run() {
                panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, false);
                try {
                    final Course course = basicAddCourse();
                    SwingUtilities.invokeAndWait(new Runnable() {
                        public void run() {
                            panel.addCourse(course);
                            panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, true);
                        }
                    });
                }
                catch (Exception e) {}
            }
        };
        thread.start();
    }

    private Course basicAddCourse() {
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        Course course =
                new Course(
                        panel.getText(FieldCatalog.DEPARTMENT_FIELD_NAME),
                        panel.getText(FieldCatalog.NUMBER_FIELD_NAME));
        JFormattedTextField effectiveDateField =
                (JFormattedTextField)panel.getField(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME);
        Date date = (Date)effectiveDateField.getValue();
        course.setEffectiveDate(date);
        return course;
    }

    void close() {
        frame.dispose();
    }

    void createKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                setAddButtonState();
            }
        };
        panel.addFieldListener(CoursesPanel.DEPARTMENT_FIELD_NAME, listener);
        panel.addFieldListener(CoursesPanel.NUMBER_FIELD_NAME, listener);
        setAddButtonState();
    }

    void setAddButtonState() {
        panel.setEnabled(CoursesPanel.ADD_BUTTON_NAME, !isEmpty(CoursesPanel.DEPARTMENT_FIELD_NAME) &&
                !isEmpty(CoursesPanel.NUMBER_FIELD_NAME));
    }

    private boolean isEmpty(String field) {
        String value = panel.getText(field);
        return value.trim().equals("");
    }

    private void createInputFilters() {
        JTextField field =
                panel.getField(CoursesPanel.DEPARTMENT_FIELD_NAME);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        document.setDocumentFilter(new UpcaseFilter());
    }
}
