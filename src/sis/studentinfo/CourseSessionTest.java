package sis.studentinfo;
import junit.framework.TestCase;
import java.util.*;
import static sis.studentinfo.DateUtil.createDate;

public class CourseSessionTest extends SessionTest {
    public void testCourseDates() {
        Date startDate = DateUtil.createDate(2003, 1, 6);
        Session session = createSession(createCourse(), startDate);
        Date sixteenWeeksOut = createDate(2003, 4, 25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    public void testCount() {
        CourseSession.resetCount();
        createSession(createCourse(), new Date());
        assertEquals(1, CourseSession.getCount());
        createSession(createCourse(), new Date());
        assertEquals(2, CourseSession.getCount());
    }

    private Course createCourse(){
        return new Course("ENGL", "101");
    }

    protected Session createSession(Course course, Date date) {
        return CourseSession.create(course, date);
    }

    public void testEquality() {
        Course courseA = new Course("NURS", "201");
        Course courseAprime = new Course("NURS", "201");
        assertEquals(courseA, courseAprime);

        Course courseB = new Course("ARTH", "330");
        assertFalse(courseA.equals(courseB));

        // reflexividade
        assertEquals(courseA, courseA);

        // transitividade
        Course courseAPrime2 = new Course("NURS", "201");
        assertEquals(courseAprime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);

        // simetria
        assertEquals(courseAprime, courseA);

        // consistência
        assertEquals(courseA, courseAprime);

        // comparação com null
        assertFalse(courseA.equals(null));

        // apples & oranges
        assertFalse(courseA.equals("CMSC-120"));

        List<Course> list = new ArrayList<Course>();
        list.add(courseA);
        assertTrue(list.contains(courseAprime));

        Map<Course, String> map = new HashMap<Course, String>();
        map.put(courseA, "");
        assertTrue(map.containsKey(courseAprime));
    }

    public void testHashCode() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");

        assertEquals(courseA.hashCode(), courseAPrime.hashCode());

        //consistencia
        assertEquals(courseA.hashCode(), courseA.hashCode());
    }

    public void testHashCodePerformance() {
        final int count = 10000;
        long start = System.currentTimeMillis();
        Map<Course, String> map = new HashMap<Course, String>();
        for (int i = 0; i < count; i++) {
            Course course = new Course("C" + i, "" + i);
            map.put(course,"");
        }
        long stop = System.currentTimeMillis();
        long elapsed = stop - start;
        final long arbitraryThreshold = 200;
        assertTrue("elaapsed time = " + elapsed, elapsed < arbitraryThreshold);
    }
}
