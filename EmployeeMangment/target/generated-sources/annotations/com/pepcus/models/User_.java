package com.pepcus.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Date> registrationDate;
	public static volatile SingularAttribute<User, Date> deactivateOn;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile ListAttribute<User, Book> bookList;

	public static final String NAME = "name";
	public static final String REGISTRATION_DATE = "registrationDate";
	public static final String DEACTIVATE_ON = "deactivateOn";
	public static final String ID = "id";
	public static final String BOOK_LIST = "bookList";

}

