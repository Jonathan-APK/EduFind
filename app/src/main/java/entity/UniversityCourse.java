package entity;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class UniversityCourse extends Course{
    private double gradePointAverage;

    public UniversityCourse() {
    }

    public UniversityCourse(String courseName, String interest, String specialization, Institution institution, String website, String school, String educationLevel, String description, int intake, double gradePointAverage) {
        super(courseName, interest, specialization, institution, website, school, educationLevel, description, intake);
        this.gradePointAverage = gradePointAverage;
    }

    public double getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }
}
