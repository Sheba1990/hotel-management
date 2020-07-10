package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends by.nikita.models.AEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> activationCode;
	public static volatile ListAttribute<User, Order> bookings;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, UserDetails> userDetails;

	public static final String PASSWORD = "password";
	public static final String ACTIVATION_CODE = "activationCode";
	public static final String BOOKINGS = "bookings";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";
	public static final String USER_DETAILS = "userDetails";

}

