package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Room.class)
public abstract class Room_ extends by.nikita.models.AEntity_ {

	public static volatile SingularAttribute<Room, Integer> number;
	public static volatile SingularAttribute<Room, RoomDetails> details;
	public static volatile SingularAttribute<Room, Category> category;
	public static volatile ListAttribute<Room, Order> bookings;
	public static volatile SingularAttribute<Room, RoomStatus> status;

	public static final String NUMBER = "number";
	public static final String DETAILS = "details";
	public static final String CATEGORY = "category";
	public static final String BOOKINGS = "bookings";
	public static final String STATUS = "status";

}

