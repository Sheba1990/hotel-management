package by.nikita.models;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ extends by.nikita.models.AEntity_ {

	public static volatile SingularAttribute<Order, Integer> number;
	public static volatile SingularAttribute<Order, Integer> amountOfGuests;
	public static volatile SingularAttribute<Order, User> user;
	public static volatile SingularAttribute<Order, LocalDate> dateOfCheckIn;
	public static volatile SingularAttribute<Order, LocalDate> dateOfCheckOut;
	public static volatile SingularAttribute<Order, Room> room;

	public static final String NUMBER = "number";
	public static final String AMOUNT_OF_GUESTS = "amountOfGuests";
	public static final String USER = "user";
	public static final String DATE_OF_CHECK_IN = "dateOfCheckIn";
	public static final String DATE_OF_CHECK_OUT = "dateOfCheckOut";
	public static final String ROOM = "room";

}

