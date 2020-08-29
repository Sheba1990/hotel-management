package by.nikita.models;

import javax.persistence.*;

@Entity
@Table(name = "passport_data_table")
public class PassportData extends AbstractIdAwareEntity {

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "date_of_issue")
    private String dateOfIssue;

    @Column(name = "date_of_expiry")
    private String dateOfExpiry;

    @Column(name = "country_of_issue")
    private String countryOfIssue;

    @OneToOne(mappedBy = "passportData", fetch = FetchType.LAZY)
    private UserInDetails userInDetails;

    public PassportData() {
    }

    public PassportData(
            String passportNumber,
            String dateOfIssue,
            String dateOfExpiry,
            String countryOfIssue,
            UserInDetails userInDetails) {
        this.passportNumber = passportNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.countryOfIssue = countryOfIssue;
        this.userInDetails = userInDetails;

    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public UserInDetails getUserInDetails() {
        return userInDetails;
    }

    public void setUserInDetails(UserInDetails userInDetails) {
        this.userInDetails = userInDetails;
    }
}

