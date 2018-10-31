package com.mycode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycode.bean.Department;
import com.mycode.inform.Message;
import com.mycode.service.DepartmentService;

@Controller
public class DepartmentController {
   
	@Autowired
	private DepartmentService depService;
	
	
	/**
	 * ajax请求 ，获取部门信息
	 * @return
	 */
	
	@RequestMapping("/department")
	@ResponseBody
	public Message getDepartment() {
		List<Department> depList=depService.getDepartmentInfo();
		Message msg=new Message();
		return msg.success().addMapValue("depList", depList);
	}
	
	
}
