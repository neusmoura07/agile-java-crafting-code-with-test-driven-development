package sis.report;

import sis.studentinfo.*;
import java.util.*;
import junit.framework.TestCase;
import static sis.report.RosterReporter.NEWLINE;

public class CourseReportTest extends TestCase {

    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "410", date));
        report.add(CourseSession.create("CZEC", "220", date));
        report.add(CourseSession.create("ITAL", "330", date));
        assertEquals(
                "CZEC 200" + NEWLINE +
                        "CZEC 220" + NEWLINE +
                        "ENGL 101" + NEWLINE +
                        "ITAL 330" + NEWLINE +
                        "ITAL 410" + NEWLINE,
                report.text());
    }

    public void testSortStringInPlace() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Heller");
        list.add("Kafka");
        list.add("Camus");
        list.add("Boyle");

        ArrayList<String> sortedlist = new ArrayList<String>(list);
        java.util.Collections.sort(sortedlist);

        assertEquals("Boyle", sortedlist.get(0));
        assertEquals("Camus", sortedlist.get(1));
        assertEquals("Heller", sortedlist.get(2));
        assertEquals("Kafka", sortedlist.get(3));

        assertEquals("Heller", list.get(0));
        assertEquals("Kafka", list.get(1));
        assertEquals("Camus", list.get(2));
        assertEquals("Boyle", list.get(3));
    }

    public void testStringCompareTo() {
        assertTrue("A".compareTo("B") < 0);
        assertEquals(0,"A".compareTo("A"));
        assertTrue("B".compareTo("A") > 0);
    }

}
