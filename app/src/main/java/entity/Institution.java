package entity;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class Institution {
    private String institution;
    private String instiDescription;
    private int postalCode;

    public Institution() {

    }

    public Institution(String institution, String instiDescription, int postalCode) {
        this.institution = institution;
        this.instiDescription = instiDescription;
        this.postalCode = postalCode;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getInstiDescription() {
        return instiDescription;
    }

    public void setInstiDescription(String instiDescription) {
        this.instiDescription = instiDescription;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
