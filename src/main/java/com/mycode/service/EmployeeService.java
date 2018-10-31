package com.mycode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycode.bean.Employee;
import com.mycode.dao.EmployeeMapper;

@Service
public class EmployeeService {
   
	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	
	/**
	 * 添加一个员工
	 * @return
	 */
	public Boolean addEmpl(Employee emp) {
		return (employeeMapper.insert(emp)==0)?false:true;
	}
	

	
	
	
}
