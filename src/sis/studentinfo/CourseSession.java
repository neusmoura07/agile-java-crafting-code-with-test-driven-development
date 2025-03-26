package sis.studentinfo;

import java.util.*;

/**
 * Provides a represetation of a single-semester session of a specific university course.
 * @author nvmoura
 */
public class CourseSession extends Session {
    private static int count;

    public static CourseSession create(String department, String number, Date startDate) {
        return new CourseSession(department, number, startDate);

    }
    /**
     *Constrói uma CourseSession iniciando em uma data especifica
     * @param startDate
     */
    protected CourseSession(String department, String number, Date startDate) {
        super(department, number,startDate);
        incrementCount();

    }

    static private void incrementCount() {
        ++count;
    }

    static void resetCount() {
        count = 0;
    }

    static int getCount() {
        return count;
    }

    protected int getSessionLength() {
        return 16;
    }
}
