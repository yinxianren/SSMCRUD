package com.mycode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycode.bean.Department;
import com.mycode.dao.DepartmentMapper;

@Service
public class DepartmentService {
    
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDepartmentInfo() {
		
		return departmentMapper.selectByExample(null);
	}

}
