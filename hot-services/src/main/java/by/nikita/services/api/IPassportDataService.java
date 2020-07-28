package by.nikita.services.api;

import by.nikita.dto.PassportDataDto;

import java.util.List;

public interface IPassportDataService {

    PassportDataDto addPassportData(PassportDataDto passportDataDto);

    List<PassportDataDto> getAllPassports();

    PassportDataDto getPassportDataById(long id);

    List<PassportDataDto> getPassportDataByUserFirstName(String userFirstName);

    List<PassportDataDto> getPassportDataByUserLastName(String userLastName);

    List<PassportDataDto> getPassportDataByUserFullName(String userFirstName, String userLastName);

    void updatePassportData(long id, PassportDataDto passportDataDto);

    void deletePassportData(long id);
}
