package sis.UI;

import junit.framework.*;
import java.io.*;
import java.util.*;
import sis.studentinfo.*;


public class StudentUITest extends TestCase {
    static private final String name = "Leo Xerces Schmmo";

    public void testCreateStudent() throws IOException {
        StringBuffer expectedOutput = new StringBuffer();
        StringBuffer input = new StringBuffer();
        setup(expectedOutput, input);
        byte[] buffer = input.toString().getBytes();

        InputStream inputStream = new ByteArrayInputStream(buffer);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        OutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        StudentUI ui = new StudentUI(reader, writer);
        ui.run();

        assertEquals(expectedOutput.toString(), outputStream.toString());
        assertStudents(ui.getAddedStudents());
    }

    private String line(String input) {
        return String.format("%s%n", input);
    }

    private void setup(StringBuffer expectedOutput, StringBuffer input) {
        expectedOutput.append(StudentUI.MENU);
        input.append(line(StudentUI.ADD_OPTION));
        expectedOutput.append(StudentUI.NAME_PROMPT);
        input.append(line(name));
        expectedOutput.append(line(StudentUI.ADDED_MESSAGE));
        expectedOutput.append(StudentUI.MENU);
        input.append(line(StudentUI.QUIT_OPTION));
    }

    private void assertStudents(List<Student> students) {
        assertEquals(1, students.size());
        Student student = students.get(0);
        assertEquals(name, student.getName());
    }
}
