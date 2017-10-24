package entity;

/**
 * Specifies a Institution entity class that
 * stores the values of an institution as an
 * Institution object for data manipulation
 *
 * @author  Minions
 * @version 1.0
 * @since   2017-10-24
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
