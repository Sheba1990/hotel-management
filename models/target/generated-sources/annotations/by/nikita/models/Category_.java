package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ extends by.nikita.models.AEntity_ {

	public static volatile ListAttribute<Category, Room> rooms;
	public static volatile SingularAttribute<Category, String> name;

	public static final String ROOMS = "rooms";
	public static final String NAME = "name";

}

