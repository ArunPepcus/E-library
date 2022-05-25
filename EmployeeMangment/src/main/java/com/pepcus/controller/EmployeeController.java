package com.pepcus.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.model.Employee;
import com.pepcus.repository.EmployeeRepository;
import com.pepcus.service.EmployeeService;
import com.pepcus.specification.EmployeeSpecification;

import lombok.Data;

@Data
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeSpecification employeeSpecification;

  // public EmployeeController(EmployeeService employeeService) {
  // super();
  // this.employeeService = employeeService;
  // }

  /*
   * mapping between the employee and address in a database
   */
  @PostMapping
  public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {

    return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
  }

  /*
   * build get all employees
   */
  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  /*
   * build get employee by id
   */
  @GetMapping("{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
    return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
  }

  /*
   * build get employee by field shorting like employee
   * name,email,phoneNumber,department
   */
  @GetMapping("/sorts/{field}")
  public List<Employee> findEmployeeWithSorting(@PathVariable String field) {
    List<Employee> employeeSorting = employeeService.findEmployeeWithSorting(field);
    return employeeSorting;
  }

  /*
   * fetching by name in db using jpa repository
   */
  @GetMapping("/name/{name}")
  public List<Employee> findByName(@PathVariable String name) {

    List<Employee> employeeSortingName = employeeRepository.findByName(name);
    return employeeSortingName;
  }
  /*
   * fetching by department in db using jpa repository
   */

  // @GetMapping("/department/{department}")
  // public List<Employee> findByDepartment(@PathVariable("department") String
  // department) {
  //
  // List<Employee> employeeSortingName =
  // employeeRepository.findByDepartment(department);
  // return employeeSortingName;
  // }

  /*
   * build get employee by name filter like-/filters/It(it-department name)
   */
  @GetMapping("/filters/{name}")
  public List<Employee> findEmployeeByFilter(@PathVariable String name) {
    List<Employee> employeeFilter = employeeService.findEmployeeByFilter(name);
    return employeeFilter;
  }

  /*
   * build update employee
   */
  @PutMapping("{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
    return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
  }

  /*
   * this method build for delete employee
   */
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
    employeeService.deleteEmployee(id);
    return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

  }

  /*
   * this method for pagination
   */
  @GetMapping("page/{offset}/{pageSize}")
  public Page<Employee> findEmployeeByPagination(@PathVariable Integer offset, @PathVariable Integer pageSize) {
    Page<Employee> pages = employeeService.findEmployeeByPagination(offset, pageSize);
    return pages;

  }
  

  /*
   * Specification handler
   */
  @GetMapping("/findByNameAndDepartment")
  public List<Employee> findByFirstNameAndDepartment(@RequestParam("name") String name,
      @RequestParam("department") String department) {
    Specification<Employee> specification = Specification.where(EmployeeSpecification.hasFirstName(name)
        .and(Specification.where(EmployeeSpecification.getEmployeeDepartment(department))));
    return employeeRepository.findAll(specification);
  }

  // @GetMapping("/spect/{name}/{city}")
  // public List<Employee> findByNameAndcity(@PathVariable("name") String name,
  // @PathVariable("city") String city) {
  // Specification<Employee> specification =
  // Specification.where(EmployeeSpecification.hasFirstName(name)
  // .and(Specification.where(EmployeeSpecification.getAddressByCity(city))));
  // return employeeRepository.findAll(specification);
  // }

}
