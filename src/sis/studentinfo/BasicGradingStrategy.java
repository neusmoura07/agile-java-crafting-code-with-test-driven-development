package sis.studentinfo;

public class BasicGradingStrategy implements GrandingStrategy {

    public int getGradePointsFor(Student.Grade grade) {
        return grade.getPoints();
        }
    }
