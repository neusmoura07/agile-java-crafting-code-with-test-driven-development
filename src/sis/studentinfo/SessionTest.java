package sis.studentinfo;

import junit.framework.*;

import java.net.MalformedURLException;
import java.util.*;


abstract public class SessionTest extends TestCase {
    private Session session;
    private Date startDate;
    public static final int CREDITS = 3;

    public void setUp() {
        startDate = DateUtil.createDate(2003, 1,6);
        session = createSession("ENGL", "101", startDate);
        session.setNumberOfCredits(CREDITS);
    }

    abstract protected Session createSession(String departament, String number, Date startDate);

    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
    }

    public void testComparable() {
        final Date date = new Date();
        CourseSession sessionA = CourseSession.create("CMSC", "101", date);
        CourseSession sessionB = CourseSession.create("ENGL", "101", date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        CourseSession sessionC = CourseSession.create("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));

        CourseSession sessionD = CourseSession.create("CMSC", "210", date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }

    public void testEnrollStudents() {
        final Student student1 = new Student("Cain Divoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        assertEquals(1, session.getNumberOfStudents());


        final Student student2 = new Student("Coralee Devaughn");
        session.enroll(student2);
        assertEquals(CREDITS, student2.getCredits());
        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }

    public void testAverageGpaForPartTimeStudents() {
        session.enroll(createFullTimeStudent());
        Student partTimer1 = new Student("1");
        partTimer1.addGrade(Student.Grade.A);
        session.enroll(partTimer1);
        session.enroll(createFullTimeStudent());
        Student partTimer2 = new Student("2");
        partTimer2.addGrade(Student.Grade.B);
        session.enroll(partTimer2);
        assertEquals(3.5, session.averageGpaForPartTimeStudents(), 0.05);
    }

    private Student createFullTimeStudent() {
        Student student = new Student("a");
        student.addCredits(Student.CREDITS_REQUIRED_FOR_FULL_TIME);
        return student;
    }

    public void testIterate() {
        enrollStudents(session);
        List<Student> results = new ArrayList<Student>();
        for(Student student: session)
            results.add(student);
        assertEquals(session.getAllStudents(), results);
    }

    public void testCasting() {
        List students = new ArrayList();
        students.add(new Student("a"));
        students.add(new Student("b"));
        List names = new ArrayList();
        Iterator it = students.iterator();
        while (it.hasNext()) {
            Student student = (Student)it.next();
            names.add(student.getLastName());
        }
        assertEquals("a", names.get(0));
        assertEquals("b", names.get(1));
    }

    private void enrollStudents(Session session) {
        session.enroll(new Student("1"));
        session.enroll(new Student("2"));
        session.enroll(new Student("3"));
    }

    public void testSessionUrl() throws MalformedURLException {
        final String url = "http://course.langrsoft.com/cmsc300";
        session.setUrl(url);
        assertEquals(url, session.getUrl().toString());
    }

    public void testInvalidSessionUrl() {
        final String url = "httsp://course.langrsoft.com/cmsc300"; // Incorreto
        try {
            session.setUrl(url);
            fail("expected exception due to invalid protocol in URL");
        } catch (MalformedURLException success) {

        }
    }





}
