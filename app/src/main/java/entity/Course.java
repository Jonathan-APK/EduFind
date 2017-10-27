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
    private String courseDescription;
    private String careerProspect;
    private Institution institution;
    private int intake;

    public Course() {
    }

    public Course(String courseName, String interest, String specialisation, String website, String courseDescription, Institution institution, int intake) {
        this.courseName = courseName;
        this.interest = interest;
        this.specialisation = specialisation;
        this.website = website;
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

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCareerProspect() {
        return careerProspect;
    }

    public void setCareerProspect(String careerProspect) {
        this.careerProspect = careerProspect;
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
