package sis.UI;

import java.util.*;
import java.text.*;

public class FieldCatalog {
    static final String DEPARTMENT_FIELD_INFO = "Enter a 4-character department designation.";
    static final String NUMBER_FIELD_INFO = "The department number should be 3 digits.";
    static final String EFFECTIVE_DATE_FIELD_INFO = "Effective date should be in mm/dd/yy format.";
    public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
    static final String DEPARTMENT_FIELD_NAME = "deptField";
    static final String DEPARTMENT_LABEL_TEXT = "Department";
    static final int DEPARTMENT_FIELD_LIMIT = 4; static final String NUMBER_FIELD_NAME = "numberField";
    static final String NUMBER_LABEL_TEXT = "Number";
    static final int NUMBER_FIELD_LIMIT = 3;
    static final String EFFECTIVE_DATE_FIELD_NAME = "effectiveDateField";
    static final String EFFECTIVE_DATE_LABEL_TEXT = "Effective Date";
    static final int DEFAULT_COLUMNS = 20;
    public static final String DEPARTMENT_FIELD_SHORTNAME     = "Dept";
    public static final String NUMBER_FIELD_SHORTNAME         = "Number";
    public static final String EFFECTIVE_DATE_FIELD_SHORTNAME = "Eff. Date";


    private Map<String, Field> fields;
    public FieldCatalog() {
        loadFields();
    }
    public int size() {
        return fields.size();
    }
    private void loadFields() {
        fields = new HashMap<String,Field>();
        // dept
        Field fieldSpec = new Field(DEPARTMENT_FIELD_NAME);
        fieldSpec.setInfo(DEPARTMENT_FIELD_INFO);
        fieldSpec.setLabel(DEPARTMENT_LABEL_TEXT);
        fieldSpec.setShortName(DEPARTMENT_FIELD_SHORTNAME);
        fieldSpec.setLimit(DEPARTMENT_FIELD_LIMIT);
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        fieldSpec.setUpcaseOnly();
        put(fieldSpec);

        // number
        fieldSpec = new Field(NUMBER_FIELD_NAME);
        fieldSpec.setInfo(NUMBER_FIELD_INFO);
        fieldSpec.setLabel(NUMBER_LABEL_TEXT);
        fieldSpec.setShortName(NUMBER_FIELD_SHORTNAME);
        fieldSpec.setLimit(NUMBER_FIELD_LIMIT);
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        put(fieldSpec);

        // effective date
        fieldSpec = new Field(EFFECTIVE_DATE_FIELD_NAME);
        fieldSpec.setInfo(EFFECTIVE_DATE_FIELD_INFO);
        fieldSpec.setLabel(EFFECTIVE_DATE_LABEL_TEXT);
        fieldSpec.setShortName(EFFECTIVE_DATE_FIELD_SHORTNAME);
        fieldSpec.setFormat(DEFAULT_DATE_FORMAT);
        fieldSpec.setInitialValue(new Date());
        fieldSpec.setColumns(DEFAULT_COLUMNS);
        put(fieldSpec);
    }

    private void put(Field fieldSpec) {
        fields.put(fieldSpec.getName(), fieldSpec);
    }
    public Field get(String fieldName) {
        return fields.get(fieldName);
    }
}