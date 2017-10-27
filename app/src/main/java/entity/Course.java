package entity;

/**
 * Specifies a Course entity class that stores
 * the values of a course as a Course object for
 * data manipulation
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class Course {
    private String courseName;
    private String interest;
    private String specialisation;
    private String website;
    private String school;
    private String educationLevel;
    private String courseDescription;
    private Institution institution;
    private int intake;

    public Course() {
    }
    public Course(String courseName, String school, int intake, String website) {
        this.courseName = courseName;
        this.school = school;
        this.intake = intake;
        this.website = website;
    }

    public Course(String courseName, String interest, String specialisation, String website, String school, String educationLevel, String courseDescription, Institution institution, int intake) {
        this.courseName = courseName;
        this.interest = interest;
        this.specialisation = specialisation;
        this.website = website;
        this.school = school;
        this.educationLevel = educationLevel;
        this.courseDescription = courseDescription;
        this.institution = institution;
        this.intake = intake;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getspecialisation() {
        return specialisation;
    }

    public void setspecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }
}
