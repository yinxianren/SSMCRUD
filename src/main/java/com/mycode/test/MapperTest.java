package com.mycode.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycode.bean.Department;
import com.mycode.bean.Employee;
import com.mycode.dao.DepartmentMapper;
import com.mycode.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {

	
    private Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private SqlSession sqlSession;
	/**
	 * 测试departmentMapper\
	 *使用spring的单元测试
	 */
	
	@Test
	public void testURCD() {
		
		logger.debug("+++++++++++++++++++++++++++debug:<"+departmentMapper+">++++++++++++++++++++++++++++++++++++++");
		logger.info("+++++++++++++++++++++++++++info:<"+departmentMapper+">++++++++++++++++++++++++++++++++++++++");
	}
	
	@Test
	public void insetDepartmentSelective() {
		
		departmentMapper.insertSelective(new Department(null, "development department"));//开发部
		departmentMapper.insertSelective(new Department(null, "maketing department"));//市场部
		departmentMapper.insertSelective(new Department(null, "finance department"));//财务部
		departmentMapper.insertSelective(new Department(null, "personnel department"));//人事部
	}
	
	@Test
	public void insetEmployeeSelective() {
		employeeMapper.insertSelective(new Employee(null, "steven", "M", "steven@163.com", 1));
		
	}
	
	@Test
	public void insetEmployeeSession() {
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<100;i++) {
			
			String uuid=UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uuid, "M", uuid+"@163.com", 4));
		}
		logger.info("+++++++++++++++++++++++++++info:<"+sqlSession+">++++++++++++++++++++++++++++++++++++++");
	}
	
	
}
