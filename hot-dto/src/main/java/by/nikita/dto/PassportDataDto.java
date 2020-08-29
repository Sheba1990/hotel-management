package by.nikita.dto;

import by.nikita.models.PassportData;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PassportDataDto extends AbstractIdAwareDto {

    private String userFirstName;

    private String userMiddleName;

    private String userLastName;

    private String passportNumber;

    private String dateOfIssue;

    private String dateOfExpiry;

    private String countryOfIssue;


    public static List<PassportDataDto> convertList(List<PassportData> passportDataList) {
        List<PassportDataDto> passports = new ArrayList<>();
        for (PassportData passportData : passportDataList) {
            PassportDataDto passportDataDto = new PassportDataDto();
            passportDataDto.setId(passportData.getId());
            passportDataDto.setCountryOfIssue(passportData.getCountryOfIssue());
            if (passportData.getUserInDetails() != null) {
                passportDataDto.setUserFirstName(passportData.getUserInDetails().getFirstName());
                passportDataDto.setUserLastName(passportData.getUserInDetails().getLastName());
            } else {
                passportDataDto.setUserFirstName(null);
                passportDataDto.setUserLastName(null);
            }
            passports.add(passportDataDto);
        }
        return passports;
    }

    public static PassportDataDto entityToDto(PassportData passportData) {
        PassportDataDto passportDataDto = new PassportDataDto();
        passportDataDto.setId(passportData.getId());
        passportDataDto.setPassportNumber(passportData.getPassportNumber());
        passportDataDto.setDateOfIssue(passportData.getDateOfIssue());
        passportDataDto.setDateOfExpiry(passportData.getDateOfExpiry());
        passportDataDto.setCountryOfIssue(passportData.getCountryOfIssue());
        if (passportData.getUserInDetails() != null) {
            passportDataDto.setUserFirstName(passportData.getUserInDetails().getFirstName());
            passportDataDto.setUserMiddleName(passportData.getUserInDetails().getMiddleName());
            passportDataDto.setUserLastName(passportData.getUserInDetails().getLastName());
        } else {
            passportDataDto.setUserFirstName(null);
            passportDataDto.setUserLastName(null);
            passportDataDto.setUserMiddleName(null);
        }
        return passportDataDto;
    }

    public PassportDataDto() {
    }

    public PassportDataDto(PassportData passportData) {
        this.passportNumber = passportData.getPassportNumber();
        this.dateOfIssue = passportData.getDateOfIssue();
        this.dateOfExpiry = passportData.getDateOfExpiry();
        this.countryOfIssue = passportData.getCountryOfIssue();
        this.userFirstName = passportData.getUserInDetails().getFirstName();
        this.userLastName = passportData.getUserInDetails().getLastName();
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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
