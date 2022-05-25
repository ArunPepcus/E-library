package com.pepcus.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.pepcus.model.Address;
import com.pepcus.model.Department;
import com.pepcus.model.Employee;
import com.pepcus.model.Address_;
import com.pepcus.model.Employee_;

@Component
public class EmployeeSpecification {

  public static Specification<Employee> hasFirstName(String name) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(Employee_.NAME), name);
    });
  }

  // public static Specification<Employee> hasDepartment(String department) {
  // return ((root, criteriaQuery, criteriaBuilder) -> {
  // return criteriaBuilder.equal(root.get(Employee_.DEPARTMENT), department);
  // });
  // }

  public static Specification<Employee> hasPhoneNumber(String phoneNumber) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(Employee_.PHONE_NUMBER), phoneNumber);
    });
  }

  public static Specification<Employee> hasEmail(String email) {
    return ((root, criteriaQuery, criteriaBuilder) -> {
      return criteriaBuilder.equal(root.get(Employee_.EMAIL), email);
    });
  }

//  public static Specification<Employee> getAddressByCity(String city) {
//    return (root, criteriaQuery, criteriaBuilder) -> {
//      Join<Employee, Address> addressJoin = root.join("name");
//      return criteriaBuilder.equal(addressJoin.get(Address_.CITY), city);
//    };
//  }

  public static Specification<Employee> getEmployeeDepartment(String department) {
    return ((root, query, criteriaBuilder) -> {
      Join<Employee, Department> join = root.join("departmentList", JoinType.INNER);
      return criteriaBuilder.equal(join.get("name"), department);
    });
  }
}
