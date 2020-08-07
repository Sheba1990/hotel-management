package by.nikita.models.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = false)
public class StringToDateSQLConverter implements AttributeConverter<String, Date> {

    public Date convertToDatabaseColumn(String entityValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(entityValue, formatter).plusDays(1);
//        return Optional.ofNullable(localDate).map(Date::valueOf).orElse(null);
        if(localDate == null)
            return null;
        return Date.valueOf(localDate);
    }

    public String convertToEntityAttribute(Date databaseValue) {
        LocalDate localDate = databaseValue.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = localDate.format(formatter);
        if (databaseValue == null)
            return null;
        return date;
    }

}
