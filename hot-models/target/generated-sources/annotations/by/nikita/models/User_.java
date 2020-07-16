package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends AbstractIdAwareEntity_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile ListAttribute<User, Order> orders;
	public static volatile SingularAttribute<User, String> activationCode;
	public static volatile SingularAttribute<User, UserDetails> userDetails;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> username;

	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String ACTIVE = "active";
	public static final String ORDERS = "orders";
	public static final String ACTIVATION_CODE = "activationCode";
	public static final String USER_DETAILS = "userDetails";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

