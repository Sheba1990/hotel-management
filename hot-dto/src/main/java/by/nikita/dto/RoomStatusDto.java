package by.nikita.dto;

import java.util.Objects;

public class RoomStatusDto {

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomStatusDto that = (RoomStatusDto) o;
        return statusName.equals(that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusName);
    }
}
