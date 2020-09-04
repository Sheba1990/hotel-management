package by.nikita.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(source, formatter);
        return localDate;
    }

}