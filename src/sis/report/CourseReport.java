package sis.report;

import java.util.*;
import sis.studentinfo.*;
import java.util.ArrayList;
import static sis.report.ReportConstant.NEWLINE;


public class CourseReport {
    private List<CourseSession> sessions = new LinkedList<CourseSession>();

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for(CourseSession session: sessions)
            builder.append(
                    session.getDepartment() + " " + session.getNumber() + NEWLINE);
        return builder.toString();
    }
}
