package entity;

public class Course {
    private String courseName;
    private String interest;
    private String specialization;
    private String website;
    private String school;
    private String educationLevel;
    private String courseDescription;
    private Institution institution;
    private int intake;

    public Course() {
    }

    public Course(String courseName, String interest, String specialization, String website, String school, String educationLevel, String courseDescription, Institution institution, int intake) {
        this.courseName = courseName;
        this.interest = interest;
        this.specialization = specialization;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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
