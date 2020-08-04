package by.nikita.dto;

import java.util.List;

public class RoomStatusDto {

    private String statusName;

    private List<RoomDto> rooms;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
