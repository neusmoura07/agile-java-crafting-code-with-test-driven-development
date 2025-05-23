package sis.UI;

import javax.swing.*;
import javax.swing.text.*;
import java.util.*;
import java.text.*;
import junit.framework.*;
import sis.studentinfo.DateUtil;
import sis.util.*;

public class TextFieldFactoryTest extends TestCase {

    private Field fieldSpec;
    private static final String FIELD_NAME = "fieldName";
    private static final int COLUMNS = 1;
    protected void setUp() {
        fieldSpec = new Field(FIELD_NAME);
        fieldSpec.setColumns(COLUMNS);
    }
    public void testCreateSimpleField() {
        final String textValue = "value";
        fieldSpec.setInitialValue(textValue);
        JTextField field = TextFieldFactory.create(fieldSpec);
        assertEquals(COLUMNS, field.getColumns());
        assertEquals(FIELD_NAME, field.getName());
        assertEquals(textValue, field.getText());
    }
    public void testLimit() {
        final int limit = 3;
        fieldSpec.setLimit(limit);JTextField field = TextFieldFactory.create(fieldSpec);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        ChainableFilter filter =
                (ChainableFilter)document.getDocumentFilter();
        assertEquals(limit, ((LimitFilter)filter).getLimit());
    }
    public void testUpcase() {
        fieldSpec.setUpcaseOnly();
        JTextField field = TextFieldFactory.create(fieldSpec);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        ChainableFilter filter =
                (ChainableFilter)document.getDocumentFilter();
        assertEquals(UpcaseFilter.class, filter.getClass());
    }
    public void testMultipleFilters() {
        fieldSpec.setLimit(3);
        fieldSpec.setUpcaseOnly();
        JTextField field = TextFieldFactory.create(fieldSpec);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        ChainableFilter filter =
                (ChainableFilter)document.getDocumentFilter();
        Set<Class> filters = new HashSet<Class>();
        filters.add(filter.getClass());
        filters.add(filter.getNext().getClass());
        assertTrue(filters.contains(LimitFilter.class));
        assertTrue(filters.contains(UpcaseFilter.class));
    }
    public void testCreateFormattedField() {
        final int year = 2006;
        final int month = 3;
        final int day = 17;
        fieldSpec.setInitialValue(DateUtil.createDate(year, month, day));

        final String pattern = "MM/dd/yy";
        fieldSpec.setFormat(new SimpleDateFormat(pattern));

        JFormattedTextField field =
                (JFormattedTextField)TextFieldFactory.create(fieldSpec);

        assertEquals(1, field.getColumns());
        assertEquals(FIELD_NAME, field.getName());
        DateFormatter formatter = (DateFormatter)field.getFormatter();
        SimpleDateFormat format = (SimpleDateFormat)formatter.getFormat();
        assertEquals(pattern, format.toPattern());
        assertEquals(Date.class, field.getValue().getClass());
        assertEquals("03/17/06", field.getText());
        assertDateEquals(year, month, day,
                (Date)field.getValue());
    }

    private static void assertDateEquals(int year, int month, int day, java.util.Date actual) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(actual);
        // note: em java.util.Calendar, month é 0-based (jan=0), mas o seu DateUtil pode ajustar
        assertEquals(year,  cal.get(java.util.Calendar.YEAR));
        assertEquals(month, cal.get(java.util.Calendar.MONTH) + 1);
        assertEquals(day,   cal.get(java.util.Calendar.DAY_OF_MONTH));
    }
}
