package by.nikita.dao.api;

import by.nikita.models.PassportData;

import java.util.List;

public interface IPassportDataDao extends IAbstractGenericDao<PassportData> {

    List<PassportData> getPassportDataByUserFirstName(String userFirstName);

    List<PassportData> getPassportDataByUserLastName(String userLastName);

    List<PassportData> getPassportDataByUserFullName(String userFirstName, String userLastName);
}
