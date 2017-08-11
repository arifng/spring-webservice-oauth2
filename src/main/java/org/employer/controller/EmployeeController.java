package org.employer.controller;

import org.employer.dao.IEmployeeDao;
import org.employer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by arif on 7/14/2017.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    IEmployeeDao employeeDao;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        Employee match = employeeDao.getEmployee(employee.getUsername());
        if(match == null) {
            if(employeeDao.addEmployee(employee) > 0)
                return new ResponseEntity<String>("Employee created Successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Employee already exists!", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> editEmployee(@RequestBody Employee employee) {
        Employee match = employeeDao.getEmployee(employee.getUsername());
        if(match == null) {
            return new ResponseEntity<String>("Employee doesn't exists!", HttpStatus.NOT_FOUND);
        } else {
            if(employeeDao.updateEmployee(employee) > 0)
                return new ResponseEntity<String>("Employee updated successfully!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeDetails(@PathVariable("username") String username) {
        Employee employee = employeeDao.getEmployee(username);
        if(employee == null) {
            return new ResponseEntity<Employee>(employee,HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable("username") String username) {
        Employee match = employeeDao.getEmployee(username);
        if(match == null) {
            return new ResponseEntity<String>("Employee doesn't exists!", HttpStatus.NOT_FOUND);
        }  else {
            if(employeeDao.deleteEmployee(username) > 0)
                return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployee() {
        List<Employee> list = employeeDao.findAllEmployee();
        if(list.size() < 0) {
            return new ResponseEntity<List<Employee>>(list, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
    }
}
