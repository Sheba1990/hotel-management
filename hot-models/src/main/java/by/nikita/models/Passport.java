package by.nikita.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "passport_table")
public class Passport extends IdAwareEntity {

    @Column(name = "passport_serial")
    private String passportSerial;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "date_of_issue")
    private String dateOfIssue;

    @Column(name = "date_of_expiry")
    private String dateOfExpiry;

    @Column(name = "country")
    private String countryOfIssue;

    @OneToOne(mappedBy = "passport")
    private UserDetails userDetails;

    public Passport() {
    }

    public Passport(
            String passportSerial,
            String passportNumber,
            String dateOfIssue,
            String dateOfExpiry,
            String countryOfIssue,
            UserDetails userDetails) {
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.countryOfIssue = countryOfIssue;
        this.userDetails = userDetails;

    }

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}

