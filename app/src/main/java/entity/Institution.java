package entity;

/**
 * Created by boonleng94 on 27/9/2017.
 */

public class Institution {
    private String institution;
    private int schoolPostalCode;

    public Institution() {

    }

    public Institution(String institution, int schoolPostalCode) {
        this.institution = institution;
        this.schoolPostalCode = schoolPostalCode;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getSchoolPostalCode() {
        return schoolPostalCode;
    }

    public void setSchoolPostalCode(int schoolPostalCode) {
        this.schoolPostalCode = schoolPostalCode;
    }
}
