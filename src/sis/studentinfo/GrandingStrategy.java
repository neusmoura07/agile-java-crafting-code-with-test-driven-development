package sis.studentinfo;

import java.io.Serializable;

public interface GrandingStrategy extends Serializable {
    int getGradePointsFor(Student.Grade grade);
}
