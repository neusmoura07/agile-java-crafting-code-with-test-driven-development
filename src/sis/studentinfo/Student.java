package sis.studentinfo;

import java.util.*;

public class Student implements Comparable<Student> {
    private String firstName = "";
    private String middleName = "";
    private String lastName;
    private String name;
    private int credits;
    private String state = "";
    private GrandingStrategy grandingStrategy = new BasicGradingStrategy();

    static final String IN_STATE = "CO";
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;

    private ArrayList<Grade> grades = new ArrayList<Grade>();
    private List<Integer> charges = new ArrayList<Integer>();

    public int compareTo(Student o) {
        return 0;
    }


    public enum Grade {
        A(4), B(3), C(2), D(1), F(0);

        private int points;

        Grade(int points) {
            this.points = points;
        }

        int getPoints() {
            return points;
        }

    };

    public Student(String fullName) {
        final int maximumNumberOfNameParts = 3;
        this.name = fullName;
        credits = 0;

        List<String> nameParts = split(fullName);

        if(nameParts.size() > maximumNumberOfNameParts) {
            String message = "Student name '" + fullName + "' contains more than " + maximumNumberOfNameParts + " parts";
            throw new StudentNameFormatException(message);
        }

        setName(nameParts);
    }

    public String getName() {
        return this.name;
    }

    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int totalCharges() {
        int total = 0;
        for(Integer charge: charges)
            total += charge;
        return total;
    }

    boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }
    boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    int getCredits() {
        return credits;
    }

    void addCredits(int credits) {
        this.credits += credits;
    }

    void setState(String state) {
        this.state = state;
    }

    void addGrade(Grade grade) {
        grades.add(grade);
    }

    void setGrandingStrategy(GrandingStrategy grandingStrategy) {
        this.grandingStrategy = grandingStrategy;
    }


    double getGpa() {
        if(grades.isEmpty())
            return 0.0;

        double total = 0.0;

        for(Grade grade: grades) {
            total += grandingStrategy.getGradePointsFor(grade);
        }
        return total / grades.size();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }
    private void setName(List<String> nameParts) {
        this.lastName = removeLast(nameParts);
        String name = removeLast(nameParts);

        if(nameParts.isEmpty())
            this.firstName = name;
        else {
            this.middleName = name;
            this.firstName = removeLast(nameParts);
        }
    }

    private String removeLast(List<String> list) {
        if(list.isEmpty())
            return "";
        return list.remove(list.size() - 1);
    }

    private List<String> split(String fullName) {
        return new ArrayList<>(Arrays.asList(fullName.split(" ")));
    }





}
