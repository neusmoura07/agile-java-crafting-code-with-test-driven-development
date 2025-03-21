package sis.studentinfo;

import junit.framework.TestCase;

public class StudentTest extends TestCase {
    private static final double GRADE_TOLERANCE = 0.05;

    public StudentTest() {
    }

    public void testCreate() {
        String firstStudentName = "Jane Doe";
        Student firststudent = new Student("Jane Doe");
        assertEquals("Jane Doe", firststudent.getName());
        String secondStudentname = "Joe Blow";
        Student secondStudent = new Student("Joe Blow");
        assertEquals("Joe Blow", secondStudent.getName());
    }

    public void testStudentStatus() {
        Student student = new Student("a");
        assertEquals(0,student.getCredits());
        assertFalse(student.isFullTime());
        student.addCredits(3);
        assertEquals(3,student.getCredits());
        assertFalse(student.isFullTime());
        student.addCredits(4);
        assertEquals(7,student.getCredits());
        assertFalse(student.isFullTime());
        student.addCredits(5);
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME,student.getCredits());
        assertTrue(student.isFullTime());
    }

    public void testInState() {
        Student student = new Student("a");
        assertFalse(student.isInState());
        student.setState(Student.IN_STATE);
        assertTrue(student.isInState());
        student.setState("MD");
        assertFalse(student.isInState());
    }

    public void testCalculateGpa() {
        Student student = new Student("a");
        assertGpa(student, 0.0);
        student.addGrade(Student.Grade.A);
        assertGpa(student, 4.0);
        student.addGrade(Student.Grade.B);
        assertGpa(student, 3.5);
        student.addGrade(Student.Grade.C);
        assertGpa(student, 3.0);
        student.addGrade(Student.Grade.D);
        assertGpa(student, 2.5);
        student.addGrade(Student.Grade.F);
        assertGpa(student, 2.0);
    }

    public void testCalculateRegularStudentGpa() {
        Student student = new Student("a");
        assertGpa(student,0);
        student.addGrade(Student.Grade.A);
        assertGpa(student, 4);
        student.addGrade(Student.Grade.B);
        assertGpa(student, 3.5);
    }

    public void testCalculateHonorsStudentGpa() {
        assertGpa(createHornorsStudent(), 0.0);
        assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
        assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
        assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
        assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
        assertGpa(createHonorsStudent(Student.Grade.F), 0.0);
    }

    private Student createHonorsStudent(Student.Grade grade) {
        Student student = createHornorsStudent();
        student.addGrade(grade);
        return student;
    }

    private Student createHornorsStudent() {
        Student student = new Student("a");
        student.setGrandingStrategy(new HonorsGrandingStrategy());
        return student;
    }

    private void assertGpa(Student student, double expectedGpa) {
        assertEquals(expectedGpa, student.getGpa(), GRADE_TOLERANCE);
    }



}
