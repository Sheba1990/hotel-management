package by.nikita.models;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserDetails.class)
public abstract class UserDetails_ extends IdAwareEntity_ {

	public static volatile SingularAttribute<UserDetails, String> firstName;
	public static volatile SingularAttribute<UserDetails, String> lastName;
	public static volatile SingularAttribute<UserDetails, ContactData> contactData;
	public static volatile SingularAttribute<UserDetails, LocalDate> birthdate;
	public static volatile SingularAttribute<UserDetails, Passport> passport;
	public static volatile SingularAttribute<UserDetails, String> middleName;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String CONTACT_DATA = "contactData";
	public static final String BIRTHDATE = "birthdate";
	public static final String PASSPORT = "passport";
	public static final String MIDDLE_NAME = "middleName";

}

