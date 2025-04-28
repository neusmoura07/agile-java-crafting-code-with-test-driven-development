package sis.studentinfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.net.*;

abstract public class Session implements Comparable<Session>, Iterable<Student>, java.io.Serializable {
    private Course course;
    private transient List<Student> students = new ArrayList<Student>();
    private Date startDate;
    private int numberOfCredits;
    private String name;

    protected Session(Course course, Date startDate) {
        this.course = course;
        this.startDate = startDate;
    }

    public int compareTo(Session that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare != 0)
            return compare;
        return this.getNumber().compareTo(that.getNumber());
    }

    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getDepartment() {
        return course.getDepartment();
    }

    public String getNumber() {
        return course.getNumber();
    }

    int getNumberOfStudents() {
        return students.size();
    }

    private URL url;

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    Student get(int index) {
        return students.get(index);
    }

    protected Date getStartDate() {
        return startDate;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    abstract protected int getSessionLength();

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    double averageGpaForPartTimeStudents() {
        double total = 0.0;
        int count = 0;
        //for (Student student: students) - Forma mais comum
        // for (Iterator<Student> it = students.iterator(); it.hasNext(); ) - Forma ultrapassada Iterator implementada
        //for (Enumeration<Student> it = students.elements(); it.hasMoreElements(); )  - Forma ultrapassada Enumeration implementada
        for (Student student: students) {
            if (student.isFullTime())
                continue;
            count++;
            total += student.getGpa();
        }
        if (count == 0) return 0.0;
        return total / count;
    }

    public Iterator<Student> iterator() {
        return students.iterator();
    }

    public void setUrl(String urlString) throws SessionException {
        try {
            this.url = new URL(urlString);
        } catch (MalformedURLException e) {
            log(e);
            throw new SessionException(e);
        }

    }

    public URL getUrl() {
        return url;
    }

    private void log(Exception e) {
        e.printStackTrace();
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        // 1) desserializa todos os campos não-transient
        in.defaultReadObject();
        // 2) re­inicializa a lista de estudantes
        this.students = new LinkedList<>();
    }

}
