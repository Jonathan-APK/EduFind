package entity;

public class PolytechnicCourse extends Course{
    private int L1R4;

    public PolytechnicCourse() {

    }

    public PolytechnicCourse(String courseName, String interest, String specialization, Institution institution, String website, String school, String educationLevel, String description, int intake, int l1R4) {
        super(courseName, interest, specialization, institution, website, school, educationLevel, description, intake);
        L1R4 = l1R4;
    }

    public int getL1R4() {
        return L1R4;
    }

    public void setL1R4(int L1R4) {
        this.L1R4 = L1R4;
    }
}
