package org.employer.dao;

import org.employer.model.Employee;

import java.util.List;

/**
 * Created by arif on 7/14/2017.
 */
public interface IEmployeeDao {
    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    Employee getEmployee(String username);

    int deleteEmployee(String username);

    List<Employee> findAllEmployee();
}
