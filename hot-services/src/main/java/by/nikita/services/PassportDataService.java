package by.nikita.services;

import by.nikita.dao.api.IPassportDataDao;
import by.nikita.dto.PassportDataDto;
import by.nikita.models.PassportData;
import by.nikita.services.api.IPassportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PassportDataService implements IPassportDataService {

    @Autowired
    IPassportDataDao passportDataDao;

    @Override
    public PassportDataDto addPassportData(PassportDataDto passportDataDto) {
        PassportData passportData = new PassportData();
        passportData.setPassportNumber(passportDataDto.getPassportNumber());
        passportData.setDateOfIssue(passportDataDto.getDateOfIssue());
        passportData.setDateOfExpiry(passportDataDto.getDateOfExpiry());
        passportData.setCountryOfIssue(passportDataDto.getCountryOfIssue());
        return PassportDataDto.entityToDto(passportDataDao.create(passportData));
    }

    @Override
    public List<PassportDataDto> getAllPassports() {
        return PassportDataDto.convertList(passportDataDao.getAll());
    }

    @Override
    public PassportDataDto getPassportDataById(long id) {
        return PassportDataDto.entityToDto(passportDataDao.get(id));
    }

    @Override
    public List<PassportDataDto> getPassportDataByUserFirstName(String userFirstName) {
        return PassportDataDto.convertList(passportDataDao.getPassportDataByUserFirstName(userFirstName));
    }

    @Override
    public List<PassportDataDto> getPassportDataByUserLastName(String userLastName) {
        return PassportDataDto.convertList(passportDataDao.getPassportDataByUserLastName(userLastName));
    }

    @Override
    public List<PassportDataDto> getPassportDataByUserFullName(String userFirstName, String userLastName) {
        return PassportDataDto.convertList(passportDataDao.getPassportDataByUserFullName(userFirstName, userLastName));
    }

    @Override
    public void updatePassportData(long id, PassportDataDto passportDataDto) {
        PassportData passportData = passportDataDao.get(id);
        if (passportDataDto.getPassportNumber() != null && !StringUtils.isEmpty(passportDataDto.getPassportNumber())) {
            passportData.setPassportNumber(passportDataDto.getPassportNumber());
        }
        if (passportDataDto.getDateOfIssue() != null && !StringUtils.isEmpty(passportDataDto.getDateOfIssue())) {
            passportData.setDateOfIssue(passportDataDto.getDateOfIssue());
        }
        if (passportDataDto.getDateOfExpiry() != null && !StringUtils.isEmpty(passportDataDto.getDateOfExpiry())) {
            passportData.setDateOfExpiry(passportDataDto.getDateOfExpiry());
        }
        if (passportDataDto.getCountryOfIssue() != null && !StringUtils.isEmpty(passportDataDto.getCountryOfIssue())) {
            passportData.setCountryOfIssue(passportDataDto.getCountryOfIssue());
        }
        passportDataDao.update(passportData);
    }

    @Override
    public void deletePassportData(long id) {
        passportDataDao.delete(passportDataDao.get(id));
    }
}
