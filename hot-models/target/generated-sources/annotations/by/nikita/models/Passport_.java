package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Passport.class)
public abstract class Passport_ extends by.nikita.models.IdAwareEntity_ {

	public static volatile SingularAttribute<Passport, String> passportNumber;
	public static volatile SingularAttribute<Passport, String> dateOfExpiry;
	public static volatile SingularAttribute<Passport, String> passportSerial;
	public static volatile SingularAttribute<Passport, String> countryOfIssue;
	public static volatile SingularAttribute<Passport, String> dateOfIssue;
	public static volatile SingularAttribute<Passport, UserDetails> userDetails;

	public static final String PASSPORT_NUMBER = "passportNumber";
	public static final String DATE_OF_EXPIRY = "dateOfExpiry";
	public static final String PASSPORT_SERIAL = "passportSerial";
	public static final String COUNTRY_OF_ISSUE = "countryOfIssue";
	public static final String DATE_OF_ISSUE = "dateOfIssue";
	public static final String USER_DETAILS = "userDetails";

}

