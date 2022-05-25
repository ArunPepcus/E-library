package com.pepcus.models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile SingularAttribute<Book, Date> modifiedOn;
	public static volatile SingularAttribute<Book, String> name;
	public static volatile SingularAttribute<Book, Integer> id;
	public static volatile SingularAttribute<Book, Date> addedOn;
	public static volatile SingularAttribute<Book, Date> issueOn;

	public static final String MODIFIED_ON = "modifiedOn";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String ADDED_ON = "addedOn";
	public static final String ISSUE_ON = "issueOn";

}

