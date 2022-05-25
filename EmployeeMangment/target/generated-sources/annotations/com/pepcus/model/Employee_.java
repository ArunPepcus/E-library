package com.pepcus.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, String> phoneNumber;
	public static volatile ListAttribute<Employee, Address> addressList;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile ListAttribute<Employee, Department> departmentList;
	public static volatile SingularAttribute<Employee, String> email;

	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ADDRESS_LIST = "addressList";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String DEPARTMENT_LIST = "departmentList";
	public static final String EMAIL = "email";

}

