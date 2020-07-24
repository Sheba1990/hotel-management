package by.nikita.dto;

import by.nikita.models.UserDetails;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsDto extends AbstractIdAwareDto {

    private String userName;

    private String userFirstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userMiddleName;

    private String userLastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate userBirthDate;

    public static List<UserDetailsDto> convertList(List<UserDetails> userDetailsList) {
        List<UserDetailsDto> userDetailss = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setId(userDetails.getId());
            userDetailsDto.setUserName(userDetails.getUser().getUsername());
            userDetailss.add(userDetailsDto);
        }
        return userDetailss;
    }

    public static UserDetailsDto entityToDto(UserDetails userDetails) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setId(userDetails.getId());
        userDetailsDto.setUserName(userDetails.getUser().getUsername());
        userDetailsDto.setUserFirstName(userDetails.getFirstName());
        userDetailsDto.setUserMiddleName(userDetails.getMiddleName());
        userDetailsDto.setUserLastName(userDetails.getLastName());
        userDetailsDto.setUserBirthDate(userDetails.getBirthDate());
        return userDetailsDto;
    }

    public UserDetailsDto() {
    }

    public UserDetailsDto(UserDetails userDetails) {
        this.id = userDetails.getId();
        this.userName = userDetails.getUser().getUsername();
        this.userFirstName = userDetails.getFirstName();
        this.userMiddleName = userDetails.getMiddleName();
        this.userLastName = userDetails.getLastName();
        this.userBirthDate = userDetails.getBirthDate();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserMiddleName() {
        return userMiddleName;
    }

    public void setUserMiddleName(String userMiddleName) {
        this.userMiddleName = userMiddleName;
    }

    public LocalDate getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(LocalDate userBirthDate) {
        this.userBirthDate = userBirthDate;
    }
}
