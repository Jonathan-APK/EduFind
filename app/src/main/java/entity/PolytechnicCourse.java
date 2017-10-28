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

    public int getL1R4() {
        return L1R4;
    }

    public void setL1R4(int L1R4) {
        this.L1R4 = L1R4;
    }
}
