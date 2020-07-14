package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Room.class)
public abstract class Room_ extends IdAwareEntity_ {

	public static volatile SingularAttribute<Room, Integer> roomNumber;
	public static volatile SingularAttribute<Room, RoomDetails> roomDetails;
	public static volatile SingularAttribute<Room, RoomCategory> roomCategory;
	public static volatile ListAttribute<Room, Order> orders;
	public static volatile SingularAttribute<Room, RoomStatus> roomStatus;

	public static final String ROOM_NUMBER = "roomNumber";
	public static final String ROOM_DETAILS = "details";
	public static final String ROOM_CATEGORY = "roomCategory";
	public static final String ORDERS = "orders";
	public static final String ROOM_STATUS = "roomStatus";

}

