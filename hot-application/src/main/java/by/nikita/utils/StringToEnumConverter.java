package by.nikita.utils;

import by.nikita.models.enums.RoomStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, RoomStatus> {
    @Override
    public RoomStatus convert(String source) {
        try {
            return RoomStatus.valueOf(source);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
