package entity;

/**
 * Specifies a Bookmark entity class that allow
 * users to save search parameters as a Bookmark object.
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
 */
public class Bookmark {
    private String interest;
    private String specialization;
    private String date;
    private String time;
    private int L1R4;
    private int postalCode;

    public Bookmark() {
    }

    public Bookmark(String interest, String specialization, int L1R4, int postalCode, String date, String time) {
        this.interest = interest;
        this.specialization = specialization;
        this.L1R4 = L1R4;
        this.postalCode = postalCode;
        this.date = date;
        this .time = time;
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

    public int getL1R4() {
        return L1R4;
    }

    public void setL1R4(int L1R4) {
        this.L1R4 = L1R4;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
