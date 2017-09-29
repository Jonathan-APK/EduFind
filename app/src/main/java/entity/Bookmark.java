package entity;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class Bookmark {
    private String interest;
    private String specialization;
    private int L1R4;
    private int postalCode;

    public Bookmark() {
    }

    public Bookmark(String interest, String specialization, int l1R4, int postalCode) {
        this.interest = interest;
        this.specialization = specialization;
        L1R4 = l1R4;
        this.postalCode = postalCode;
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

    public void setL1R4(int l1R4) {
        L1R4 = l1R4;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
