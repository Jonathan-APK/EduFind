package entity;

public class PolytechnicCourse {
    private String polyCourseName;
    private int L1R4;

    public PolytechnicCourse(String polyCourseName, int L1R4) {
        this.polyCourseName = polyCourseName;
        this.L1R4 = L1R4;
    }

    public String getPolyCourseName() {
        return polyCourseName;
    }

    public void setPolyCourseName(String polyCourseName) {
        this.polyCourseName = polyCourseName;
    }

    public int getL1R4() {
        return L1R4;
    }

    public void setL1R4(int L1R4) {
        this.L1R4 = L1R4;
    }
}
