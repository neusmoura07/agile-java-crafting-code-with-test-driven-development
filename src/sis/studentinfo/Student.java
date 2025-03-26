package sis.studentinfo;

import java.util.ArrayList;

public class Student {
    private String name;
    private int credits;
    private String state = "";
    private GrandingStrategy grandingStrategy = new BasicGradingStrategy();

    static final String IN_STATE = "CO";
    static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;

    private ArrayList<Grade> grades = new ArrayList<Grade>();


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



    public Student(final String name) {
        this.name = name;
        credits = 0;
    }

    public String getName() {
        return this.name;
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




}
