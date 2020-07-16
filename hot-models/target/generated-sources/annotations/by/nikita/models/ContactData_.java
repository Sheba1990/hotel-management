package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactData.class)
public abstract class ContactData_ extends AbstractIdAwareEntity_ {

	public static volatile SingularAttribute<ContactData, String> phoneNumber;
	public static volatile SingularAttribute<ContactData, Address> address;
	public static volatile SingularAttribute<ContactData, UserDetails> userDetails;

	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ADDRESS = "address";
	public static final String USER_DETAILS = "userDetails";

}

