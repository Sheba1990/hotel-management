package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoomCategory.class)
public abstract class RoomCategory_ extends AbstractIdAwareEntity_ {

	public static volatile ListAttribute<RoomCategory, Room> rooms;
	public static volatile SingularAttribute<RoomCategory, String> categoryName;

	public static final String ROOMS = "rooms";
	public static final String CATEGORY_NAME = "categoryName";

}

