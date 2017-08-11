package org.employer.dao;

import org.employer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by arif on 7/14/2017.
 */
public class EmployeeDao implements IEmployeeDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addEmployee(Employee employee) {
        String sql = "insert into employee(username, password, firstname, lastname, email, address, phone) values(?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, new Object[]{employee.getUsername(), employee.getPassword(), employee.getFirstname(),
                employee.getLastname(), employee.getEmail(), employee.getAddress(), employee.getPhone() });
    }

    @Override
    public int updateEmployee(Employee employee) {
        StringBuffer sb = new StringBuffer("update employee set ");
        boolean hasPrev = false;
        if(employee.getPassword() != null) {
            sb.append("password = '" + employee.getPassword() +"'");
            hasPrev = true;
        }
        if(employee.getFirstname() != null) {
            if(hasPrev) sb.append(", ");
            else hasPrev = true;
            sb.append("firstname = '" + employee.getFirstname() +"'");
        }
        if(employee.getLastname() != null) {
            if(hasPrev) sb.append(", ");
            else hasPrev = true;
            sb.append("lastname = '" + employee.getLastname() +"'");
        }
        if(employee.getEmail() != null) {
            if(hasPrev) sb.append(", ");
            else hasPrev = true;
            sb.append("email = '" + employee.getEmail() +"'");
        }
        if(employee.getAddress() != null) {
            if(hasPrev) sb.append(", ");
            else hasPrev = true;
            sb.append("address = '" + employee.getAddress() +"'");
        }
        if(employee.getPhone() != null) {
            if(hasPrev) sb.append(", ");
            else hasPrev = true;
            sb.append("phone = '" + employee.getPhone() +"'");
        }
        if(employee.getEnabled() != null) {
            if(hasPrev) sb.append(", ");
            else hasPrev = true;
            sb.append("enabled = '" + employee.getEnabled() +"'");
        }
        if(employee.getRole() != null) {
            if(hasPrev) sb.append(", ");
            sb.append("role = '" + employee.getRole() +"'");
        }
        sb.append("where username = '" + employee.getUsername() + "'");

        if(!hasPrev) return 0;

        return jdbcTemplate.update(sb.toString());
    }

    @Override
    public Employee getEmployee(String username) {
        String sql = "select * from employee where username = '" + username + "'";
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeMapper());

        return employees.size() > 0 ? employees.get(0) : null;
    }

    @Override
    public int deleteEmployee(String username) {
        String sql = "delete from employee where username = '" + username + "'";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Employee> findAllEmployee() {
        String sql = "select * from employee";
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeMapper());
        return employees;
    }

    private class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setUsername(resultSet.getString("username"));
            employee.setPassword(resultSet.getString("password"));
            employee.setFirstname(resultSet.getString("firstname"));
            employee.setLastname(resultSet.getString("lastname"));
            employee.setEmail(resultSet.getString("email"));
            employee.setAddress(resultSet.getString("address"));
            employee.setPhone(resultSet.getInt("phone"));
            employee.setEnabled(resultSet.getInt("enabled"));
            employee.setRole(resultSet.getString("role"));
            return employee;
        }
    }
}
