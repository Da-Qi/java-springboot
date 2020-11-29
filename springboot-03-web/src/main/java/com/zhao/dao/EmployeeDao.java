package com.zhao.dao;

import com.zhao.pojo.Department;
import com.zhao.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    static {
        employees = new HashMap<Integer, Employee>();
        try {
            employees.put(1001, new Employee(1001, "AA", "A565214727@qq.com", 1, new Department(101, "教学部"), new SimpleDateFormat("yyyy-MM-dd").parse("1999-9-01")));
            employees.put(1002, new Employee(1002, "BB", "B565214727@qq.com", 0, new Department(102, "市场部"), new SimpleDateFormat("yyyy-MM-dd").parse("1999-9-02")));
            employees.put(1003, new Employee(1003, "CC", "C565214727@qq.com", 1, new Department(103, "教研部"),new SimpleDateFormat("yyyy-MM-dd").parse("1999-9-03")));
            employees.put(1004, new Employee(1004, "DD", "D565214727@qq.com", 0, new Department(104, "运营部"),new SimpleDateFormat("yyyy-MM-dd").parse("1999-9-04")));
            employees.put(1005, new Employee(1005, "EE", "E565214727@qq.com", 1, new Department(105, "后勤部"),new SimpleDateFormat("yyyy-MM-dd").parse("1999-9-05")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private DepartmentDao departmentDao;

    //主键自增
    private static Integer initId = 1006;

    //增加员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        //因为表单提交的数据中，部门只传了一个id过来
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }
}
