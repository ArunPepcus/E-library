package com.pepcus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pepcus.model.Address;
import com.pepcus.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> ,JpaSpecificationExecutor<Employee> {

 //public List<Employee> findByDepartment(String department);

  public List<Employee> findByName(String name);

}