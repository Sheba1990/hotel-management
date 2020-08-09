package by.nikita.models;

import by.nikita.models.enums.Gender;
import by.nikita.models.utils.StringToDateSQLConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_details_table")
public class UserDetails extends AbstractIdAwareEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date", columnDefinition = "DATE")
    //@Convert(converter = StringToDateSQLConverter.class)
    private LocalDate birthDate;

    @Column(name = "age")
    private Long age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", referencedColumnName = "id")
    private PassportData passportData;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_data_id", referencedColumnName = "id")
    private ContactData contactData;

    @OneToOne(mappedBy = "userDetails", fetch = FetchType.LAZY)
    private User user;

    public UserDetails() {
    }

    public UserDetails(
            String firstName,
            String lastName,
            String middleName,
            Gender gender,
            LocalDate birthDate,
            Long age,
            PassportData passportData,
            ContactData contactData,
            User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.age = age;
        this.passportData = passportData;
        this.contactData = contactData;
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public PassportData getPassportData() {
        return passportData;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

