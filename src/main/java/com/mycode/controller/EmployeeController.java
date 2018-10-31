package com.mycode.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycode.bean.Employee;
import com.mycode.inform.Message;
import com.mycode.service.EmployeeService;

@Controller
public class EmployeeController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private EmployeeService employeeService;



	/**
	 *   
	 *  采用该方式记得导入JACKSON包
	 *   使用Rest风格URI,将页面的post普通请求转为deleter或者put请求
	 *   type:定义规则
	 * 
	 * GET  :查询
	 * POST :保存
	 * PUT  :修改
	 * DELETE :删除
	 * 
	 * JSR303校验
	 * 1.必须导入Hibernate-Validator
	 * 
	 */
 
	@RequestMapping(value="/emps",method=RequestMethod.POST)
	@ResponseBody                      //@Valid  数据校验必须的标识  BindingResult result绑定校验的结果
	public Message addEmplsToJson(@Valid Employee emp,BindingResult result) {
		logger.info("*************addNewEmplyee:name="+emp.getEmpName()+"&gender="+emp.getGender()+"&mail="+emp.getEmail()+"&deparId="+emp.getDeptId()+"***************");
		Boolean status=false;//默认设置不成功
		Message msg=new Message();
		if(result.hasErrors()) {
			List<FieldError>  errors=result.getFieldErrors();
			for (FieldError fieldError : errors) {
				logger.info("***********:错误字段名>>>"+fieldError.getField());
				logger.info("***********:错误信息>>>"+fieldError.getDefaultMessage());
				//添加错误信息，
				msg.addMapValue(fieldError.getField(), fieldError.getDefaultMessage());
			}
			
			
		}else {
			status=employeeService.addEmpl(emp);
		}
		
		return (status==true)?(msg.success()):(msg.failed());
	}

	
	
	
	

	@RequestMapping(value="/emps",method=RequestMethod.GET)
	@ResponseBody   //该注解自动将数据转为Json格式
	public Message getEmplsToJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		//引入pageHelper分页插件（1.需要引入相关jar包，2.在mybati-config.xml在要配置相关信息）
		//在查询之前只需要调用，传入的页码和每页显示几行
		PageHelper.startPage(pn,10);//显示第pn页，每页显示5行
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> empls=employeeService.getAll();
		//使用pageInfo包装查询后面的结果，只需要将pageInfo交给页面就可以
		PageInfo page=new PageInfo(empls,5);//查询的数据和显示8页
		Message msg=new Message();
		return msg.success().addMapValue("pageInfo", page);
	}


   

	


	/**
	 * 查询员工数据（分页查询）
	 * @return
	 */
	//停用，改成采用json方法
	//@RequestMapping("/emps")
	public String getEmpls(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model) {
		//引入pageHelper分页插件（1.需要引入相关jar包，2.在mybati-config.xml在要配置相关信息）
		//在查询之前只需要调用，传入的页码和每页显示几行
		PageHelper.startPage(pn,10);//显示第pn页，每页显示5行
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> empls=employeeService.getAll();
		//使用pageInfo包装查询后面的结果，只需要将pageInfo交给页面就可以
		PageInfo page=new PageInfo(empls,8);//查询的数据和显示8页
		model.addAttribute("pageInfo", page);

		logger.info("+++++++++++++++++++++++++++info:<"+empls.size()+">++++++++++++++++++++++++++++++++++++++");
		return "employeeList";
	}

}
