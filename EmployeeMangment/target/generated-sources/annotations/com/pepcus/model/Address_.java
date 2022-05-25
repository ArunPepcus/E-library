package com.pepcus.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> country;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> addressType;
	public static volatile SingularAttribute<Address, String> street1;
	public static volatile SingularAttribute<Address, Long> id;
	public static volatile SingularAttribute<Address, String> street2;
	public static volatile SingularAttribute<Address, String> state;

	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	public static final String ADDRESS_TYPE = "addressType";
	public static final String STREET1 = "street1";
	public static final String ID = "id";
	public static final String STREET2 = "street2";
	public static final String STATE = "state";

}

