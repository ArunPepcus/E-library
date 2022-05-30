package com.pepcus.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pepcus.model.Employee;

public interface EmployeeService {

  Employee saveEmployee(Employee employee);

  List<Employee> getAllEmployees();

  Employee getEmployeeById(long id);

  Employee updateEmployee(Employee employee, long id);

  void deleteEmployee(long id);

  List<Employee> findEmployeeWithSorting(String field);

  List<Employee> findEmployeeByFilter(String name);

  Page<Employee> findEmployeeByPagination(Integer offset, Integer pageSize);

  void testMethod();
}
