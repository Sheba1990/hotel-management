package by.nikita.models;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserDetails.class)
public abstract class UserDetails_ extends AbstractIdAwareEntity_ {

	public static volatile SingularAttribute<UserDetails, String> firstName;
	public static volatile SingularAttribute<UserDetails, String> lastName;
	public static volatile SingularAttribute<UserDetails, ContactData> contactData;
	public static volatile SingularAttribute<UserDetails, LocalDate> birthDate;
	public static volatile SingularAttribute<UserDetails, PassportData> passport;
	public static volatile SingularAttribute<UserDetails, String> middleName;
	public static volatile SingularAttribute<UserDetails, User> user;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String CONTACT_DATA = "contactData";
	public static final String BIRTH_DATE = "birthDate";
	public static final String PASSPORT = "passport";
	public static final String MIDDLE_NAME = "middleName";
	public static final String USER = "user";

}

