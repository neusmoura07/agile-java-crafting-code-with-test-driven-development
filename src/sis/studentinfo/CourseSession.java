package sis.studentinfo;

import java.util.*;

/**
 * Provides a represetation of a single-semester session of a specific university course.
 * @author nvmoura
 */
public class CourseSession extends Session {
    private static int count;

    public static CourseSession create(Course course, Date startDate) {
        incrementCount();
        return new CourseSession(course, startDate);

    }
    /**
     *Constrói uma CourseSession iniciando em uma data especifica
     * @param startDate
     */
    protected CourseSession(Course course, Date startDate) {
        super(course ,startDate);


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
