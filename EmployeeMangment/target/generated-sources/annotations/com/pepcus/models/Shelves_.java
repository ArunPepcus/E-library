package com.pepcus.models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Shelves.class)
public abstract class Shelves_ {

	public static volatile SingularAttribute<Shelves, Integer> id;
	public static volatile SingularAttribute<Shelves, String> shelves_name;
	public static volatile ListAttribute<Shelves, Book> bookRelation;

	public static final String ID = "id";
	public static final String SHELVES_NAME = "shelves_name";
	public static final String BOOK_RELATION = "bookRelation";

}

