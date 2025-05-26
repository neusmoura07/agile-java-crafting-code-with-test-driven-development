package sis.studentinfo;

import junit.framework.*;

import java.util.Date;

public class CourseTest extends TestCase {
    public void testCreate() {
        Course course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }

    public void testClone() {
        final String department = "CHEM";
        final String number = "400";
        final Date now = new Date();
        Course course = new Course(department, number);
        course.setEffectiveDate(now);
        Course copy = course.clone();

        assertFalse(copy == course);
        assertEquals(department, copy.getDepartment());
        assertEquals(number, copy.getNumber());
        assertEquals(now, copy.getEffectiveDate());
    }

}
