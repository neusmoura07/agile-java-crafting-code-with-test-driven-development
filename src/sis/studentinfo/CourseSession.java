package sis.studentinfo;

import java.util.*;

/**
 * Provides a represetation of a single-semester session of a specific university course.
 * @author nvmoura
 */
public class CourseSession implements Comparable<CourseSession> {
    private static int count;
    private String department;
    private String number;
    private List<Student> students = new LinkedList<Student>();
    private Date startDate;
    private int numberOfCredits;

    /**
     *Constrói uma CourseSession iniciando em uma data especifica
     * @param startDate
     */

    private CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;

    }

    public static CourseSession create(String department, String number, Date startDate) {
        incrementCount();
        return new CourseSession(department, number, startDate);

    }

    public List<Student> getallStudents() {
        return students;
    }

    public String getDepartment() {
        return this.department;
    }

    /**
     *
     * @return Date a ultima data da sessao do curso
     */

    public String getNumber() {
        return this.number;
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    private static void incrementCount() {
        ++count;
    }


    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }


    int getNumberOfStudents() {
        return this.students.size();
    }

    static int getCount() {
        return count;
    }

    static void resetCount() {
        count = 0;
    }

    Student get(int index) {
        return students.get(index);
    }

    Date getStartDate() {
        return startDate;
    }

    Date getEndDate() {
        final int sessionLenght = 16;
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        int numberOfDays = sessionLenght * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public int compareTo(CourseSession that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare == 0)
            compare = this.getNumber().compareTo(that.getNumber());

        return compare;
    }


}
