package entity;

public class PolytechnicCourse extends Course{
    private int L1R4;

    public PolytechnicCourse() {

    }

    public PolytechnicCourse(String courseName, String interest, String specialization, String website, String school, String educationLevel, String courseDescription, Institution institution, int intake, int l1R4) {
        super(courseName, interest, specialization, website, school, educationLevel, courseDescription, institution, intake);
        L1R4 = l1R4;
    }

    public PolytechnicCourse(String courseName, String school, int intake, int l1R4, String website) {
        super(courseName, school, intake, website);
        this.L1R4 = l1R4;
    }

    public int getL1R4() {
        return L1R4;
    }

    public void setL1R4(int L1R4) {
        this.L1R4 = L1R4;
    }
}
