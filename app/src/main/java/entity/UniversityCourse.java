package entity;

/**
 * Specifies a UniversityCourse entity class that
 * is a subclass of Course class to store the
 * additional values apart from the parent class
 * as a UniversityCourse object for data manipulation
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class UniversityCourse extends Course{
    private double gradePointAverage;

    public UniversityCourse() {
    }

    public UniversityCourse(String courseName, String interest, String specialisation, String website, String school, String educationLevel, String courseDescription, Institution institution, int intake, double gradePointAverage) {
        super(courseName, interest, specialisation, website, school, educationLevel, courseDescription, institution, intake);
        this.gradePointAverage = gradePointAverage;
    }

    public double getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }
}
