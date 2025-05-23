package sis.UI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import sis.studentinfo.*;
import static java.awt.GridBagConstraints.*;

public class CoursesPanel extends JPanel {
    static final String NAME = "coursesPanel";
    static final String COURSES_LABEL_TEXT = "Courses";
    static final String COURSES_LABEL_NAME = "coursesLabel";
    static final String COURSES_LIST_NAME = "coursesList";
    static final String COURSES_TABLE_NAME = "coursesList";
    static final String ADD_BUTTON_TEXT = "Add";
    static final String ADD_BUTTON_NAME = "addButton";
    static final String DEPARTMENT_FIELD_NAME = "deptField";
    static final String NUMBER_FIELD_NAME = "numberField";
    static final String DEPARTMENT_LABEL_NAME = "deptLabel";
    static final String NUMBER_LABEL_NAME = "numberLabel";
    static final String DEPARTMENT_LABEL_TEXT = "Department";
    static final String NUMBER_LABEL_TEXT = "Number";
    public static final String EFFECTIVE_DATE_LABEL_NAME = "effectiveDateFieldLabel";
    public static final String EFFECTIVE_DATE_LABEL_TEXT = FieldCatalog.EFFECTIVE_DATE_LABEL_TEXT;
    public static final String EFFECTIVE_DATE_FIELD_NAME   = FieldCatalog.EFFECTIVE_DATE_FIELD_NAME;

    private Status status;

    static final char ADD_BUTTON_MNEMONIC = 'A';
    private CoursesTableModel coursesTableModel = new CoursesTableModel();
    private JLabel coursesLabel;
    private JButton addButton;
    private JTable coursesTable;
    private DefaultListModel coursesModel = new DefaultListModel();

    public static void main(String[] args) {
        show(new CoursesPanel());
    }

    private static void show(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public CoursesPanel() {
        setName(NAME);
        coursesLabel = new JLabel(COURSES_LABEL_TEXT);
        coursesLabel.setName(COURSES_LABEL_NAME);
        coursesTable = createCoursesTable();
        createLayout();
    }

    private void createLayout() {

        JTable coursesTable = createCoursesTable();
        JScrollPane coursesScroll = new JScrollPane(coursesTable);
        coursesScroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());

        final int pad = 6;
        Border emptyBorder =
                BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder =
                BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titledBorder =
                BorderFactory.createTitledBorder(bevelBorder, COURSES_LABEL_TEXT);
        setBorder(BorderFactory.createCompoundBorder(emptyBorder, titledBorder));


        add(coursesScroll, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    JPanel createBottomPanel() {
        JLabel statusBar = new JLabel(" ");
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        status = new Status(statusBar);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(statusBar, BorderLayout.SOUTH);
        panel.add(createInputPanel(), BorderLayout.CENTER);
        return panel;
    }

    JPanel createFieldsPanel() {
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel(layout);
        int i = 0;
        FieldCatalog catalog = new FieldCatalog();
        for (String fieldName: getFieldNames()) {
            Field fieldSpec = catalog.get(fieldName);
            JTextField textField = TextFieldFactory.create(fieldSpec);
            status.addText(textField, fieldSpec.getInfo());
            addField(panel, layout, i++,
                    createLabel(fieldSpec.getLabelName(), fieldSpec.getLabel()),
                    textField);
        }
        return panel;
    }

    private void addField(
            JPanel panel, GridBagLayout layout, int row,
            JLabel label, JTextField field) {

        Insets insets = new Insets(3, 3, 3, 3); // margens

        layout.setConstraints(label,
                new GridBagConstraints(
                        0, row, 1, 1, 40, 1,
                        LINE_END, NONE, insets, 0, 0));

        layout.setConstraints(field,
                new GridBagConstraints(
                        1, row, 2, 1, 60, 1,
                        CENTER, HORIZONTAL, insets, 0, 0));

        panel.add(label);
        panel.add(field);
    }



    void setText(String textFieldName, String text) {
        getField(textFieldName).setText(text);
    }

    void addCourseAddListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    private JLabel createLabel(String name, String text) {
        JLabel label = new JLabel(text);
        label.setName(name);
        return label;
    }

    private JList createList(String name, ListModel model) {
        JList list = new JList<>(model);
        list.setName(name);
        return list;
    }

    private JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    private JTextField createField(String name, int columns) {
        JTextField field = new JTextField(columns);
        field.setName(name);
        return field;
    }
    JLabel getLabel(String name) {
        return (JLabel)Util.getComponent(this, name);
    }
    JList getList(String name) {
        return (JList)Util.getComponent(this, name);
    }
    JButton getButton(String name) {
        return (JButton)Util.getComponent(this, name);
    }
    JTextField getField(String name) {
        return (JTextField)Util.getComponent(this, name);
    }

    String getText(String textFieldName) {
        return getField(textFieldName).getText();
    }

    void setEnabled(String name, boolean state) {
        getButton(name).setEnabled(state);
    }

    void addFieldListener(String name, KeyListener listener) {
        getField(name).addKeyListener(listener);
    }

    private String[] getFieldNames() {
        return new String[]
                { FieldCatalog.DEPARTMENT_FIELD_NAME,
                        FieldCatalog.NUMBER_FIELD_NAME,
                        FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };
    }

    private JTable createCoursesTable() {
        JTable table = new JTable(coursesTableModel);
        table.setName(COURSES_TABLE_NAME);
        table.setShowGrid(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    public JTable getTable(String name) {
        if (COURSES_TABLE_NAME.equals(name)) return coursesTable;
        return null;
    }

    void addCourse(Course course) {
        coursesTableModel.add(course);
    }
    Course getCourse(int index) {
        return coursesTableModel.get(index);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        // campos em GridBag na esquerda
        panel.add(createFieldsPanel(), BorderLayout.CENTER);
        // botão Add abaixo ou ao lado
        JPanel buttonPanel = new JPanel();
        addButton = createButton(ADD_BUTTON_NAME, ADD_BUTTON_TEXT);
        addButton.setMnemonic(ADD_BUTTON_MNEMONIC);
        buttonPanel.add(addButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    int getCourseCount() {
        return coursesTableModel.getRowCount();
    }


}
