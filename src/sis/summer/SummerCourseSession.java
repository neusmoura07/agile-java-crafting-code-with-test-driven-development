package sis.summer;

import junit.framework.*;
import java.util.*;
import sis.studentinfo.*;

public class SummerCourseSession extends Session {

    public static SummerCourseSession create(String department, String number, Date startDate) {
        return new SummerCourseSession(department, number, startDate);
    }

    private SummerCourseSession(String departament, String number, Date startDate) {
        super(departament, number, startDate);
    }

    @Override
    protected int getSessionLength() {
        return 8;
    }
}
