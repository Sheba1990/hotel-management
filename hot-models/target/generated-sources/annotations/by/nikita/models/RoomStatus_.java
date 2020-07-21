package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoomStatus.class)
public abstract class RoomStatus_ extends AbstractIdAwareEntity_ {

	public static volatile ListAttribute<RoomStatus, Room> rooms;
	public static volatile SingularAttribute<RoomStatus, String> statusName;

	public static final String ROOMS = "rooms";
	public static final String STATUS_NAME = "statusName";

}

