package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoomCategory.class)
public abstract class RoomCategory_ extends by.nikita.models.IdAwareEntity_ {

	public static volatile ListAttribute<RoomCategory, Room> rooms;
	public static volatile SingularAttribute<RoomCategory, String> name;

	public static final String ROOMS = "rooms";
	public static final String NAME = "name";

}

