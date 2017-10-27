package entity;

/**
 * Specifies a PolytechnicCourse entity class that
 * is a subclass of Course class to store the
 * additional values apart from the parent class
 * as a PolytechnicCourse object for data manipulation
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class PolytechnicCourse extends Course{
    private int L1R4;

    public PolytechnicCourse() {

    }

    public PolytechnicCourse(String courseName, String interest, String specialisation, String website, String school, String educationLevel, String courseDescription, Institution institution, int intake, int l1R4) {
        super(courseName, interest, specialisation, website, school, educationLevel, courseDescription, institution, intake);
        L1R4 = l1R4;
    }

    public int getL1R4() {
        return L1R4;
    }

    public void setL1R4(int L1R4) {
        this.L1R4 = L1R4;
    }
}
