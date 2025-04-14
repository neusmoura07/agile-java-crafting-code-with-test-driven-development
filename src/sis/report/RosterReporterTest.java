package sis.report;
import junit.framework.*;
import sis.studentinfo.*;
import java.util.*;
import static sis.report.RosterReporter.NEWLINE;

public class RosterReporterTest extends TestCase {

    public void testRostReport(){
        CourseSession session = CourseSession.create(new Course("ENGL", "101"),DateUtil.createDate(2003, 1, 6));
        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        String rosterReport = new RosterReporter(session).getReport();
        assertEquals(
                RosterReporter.ROSTER_REPORT_HEADER + "A" + RosterReporter.NEWLINE + "B" + RosterReporter.NEWLINE +
                        RosterReporter.ROSTER_REPORT_FOOTER + "2" + RosterReporter.NEWLINE, rosterReport);

    }


}
