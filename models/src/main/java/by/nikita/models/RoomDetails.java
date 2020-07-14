package by.nikita.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "room_details_table")
public class RoomDetails extends IdAwareEntity {

    private Double pricePerNight;

    private Integer floor;

    private Integer amountOfRooms;

    private Integer capacity;

    private String description;

    private String picture;

    private boolean hasSeaView;

    private boolean hasBath;

    private boolean hasBabyBed;

    private boolean hasWifi;

    private boolean hasBreakfast;


}

