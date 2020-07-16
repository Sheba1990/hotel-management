package by.nikita.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ extends by.nikita.models.IdAwareEntity_ {

	public static volatile SingularAttribute<Address, String> country;
	public static volatile ListAttribute<Address, ContactData> contactDataList;
	public static volatile SingularAttribute<Address, String> province;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> homeNumber;

	public static final String COUNTRY = "country";
	public static final String CONTACT_DATA_LIST = "contactDataList";
	public static final String PROVINCE = "province";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String POSTAL_CODE = "postalCode";
	public static final String HOME_NUMBER = "homeNumber";

}

