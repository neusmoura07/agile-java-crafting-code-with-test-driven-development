package sis.studentinfo;

public class Course {
    private String departament;
    private String number;
    private int numberOfCredits;

    public Course(String departament, String number) {
        this.departament = departament;
        this.number = number;
    }

    public String getDepartment(){
        return departament;
    }

    public String getNumber() {
        return number;
    }
    @Override
    public boolean equals(Object object) {
        if(object == null)
            return false;

        if(this.getClass() != object.getClass())
            return false;

        Course that = (Course) object;
        return this.departament.equals(that.departament) && this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + departament.hashCode();
        result = result * hashMultiplier + number.hashCode();
        return  result;
    }
}
