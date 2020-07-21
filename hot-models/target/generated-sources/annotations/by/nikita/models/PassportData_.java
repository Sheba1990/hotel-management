package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PassportData.class)
public abstract class PassportData_ extends AbstractIdAwareEntity_ {

	public static volatile SingularAttribute<PassportData, String> passportNumber;
	public static volatile SingularAttribute<PassportData, String> dateOfExpiry;
	public static volatile SingularAttribute<PassportData, String> passportSerial;
	public static volatile SingularAttribute<PassportData, String> countryOfIssue;
	public static volatile SingularAttribute<PassportData, String> dateOfIssue;
	public static volatile SingularAttribute<PassportData, UserDetails> userDetails;

	public static final String PASSPORT_NUMBER = "passportNumber";
	public static final String DATE_OF_EXPIRY = "dateOfExpiry";
	public static final String PASSPORT_SERIAL = "passportSerial";
	public static final String COUNTRY_OF_ISSUE = "countryOfIssue";
	public static final String DATE_OF_ISSUE = "dateOfIssue";
	public static final String USER_DETAILS = "userDetails";

}

