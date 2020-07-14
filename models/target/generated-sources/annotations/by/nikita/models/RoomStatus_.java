package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoomStatus.class)
public abstract class RoomStatus_ extends IdAwareEntity_ {

	public static volatile ListAttribute<RoomStatus, Room> rooms;
	public static volatile SingularAttribute<RoomStatus, String> name;

	public static final String ROOMS = "rooms";
	public static final String NAME = "name";

}

