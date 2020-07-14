package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoomDetails.class)
public abstract class RoomDetails_ extends IdAwareEntity_ {

	public static volatile SingularAttribute<RoomDetails, Boolean> hasSeaView;
	public static volatile SingularAttribute<RoomDetails, Boolean> hasWifi;
	public static volatile SingularAttribute<RoomDetails, Boolean> hasBabyBed;
	public static volatile SingularAttribute<RoomDetails, String> description;
	public static volatile SingularAttribute<RoomDetails, Boolean> hasBreakfast;
	public static volatile SingularAttribute<RoomDetails, Integer> floor;
	public static volatile SingularAttribute<RoomDetails, Integer> amountOfRooms;
	public static volatile SingularAttribute<RoomDetails, Double> pricePerNight;
	public static volatile SingularAttribute<RoomDetails, String> picture;
	public static volatile SingularAttribute<RoomDetails, Integer> capacity;
	public static volatile SingularAttribute<RoomDetails, Boolean> hasBath;

	public static final String HAS_SEA_VIEW = "hasSeaView";
	public static final String HAS_WIFI = "hasWifi";
	public static final String HAS_BABY_BED = "hasBabyBed";
	public static final String DESCRIPTION = "description";
	public static final String HAS_BREAKFAST = "hasBreakfast";
	public static final String FLOOR = "floor";
	public static final String AMOUNT_OF_ROOMS = "amountOfRooms";
	public static final String PRICE_PER_NIGHT = "pricePerNight";
	public static final String PICTURE = "picture";
	public static final String CAPACITY = "capacity";
	public static final String HAS_BATH = "hasBath";

}

