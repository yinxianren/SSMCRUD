package com.mycode.test;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.mycode.bean.Employee;

/**
 * 使用spring测试模式提供的测试请求功能，测试curd请求的正确性
 * @author Administrator
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration  //获取ioc
@ContextConfiguration(locations= {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MvcTest {

	//传入springMVC的ioc  需要注解@WebAppConfiguration
	@Autowired
	private WebApplicationContext context;
	//虚拟mvc请求，获取到处理结果
	
	public MockMvc mokMvc;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Before
	public void initMokcMVC() {

		mokMvc=MockMvcBuilders.webAppContextSetup(context).build(); 
	}

	@Test
	public void testPage() {
		try {
			//模拟请求拿到返回值
		
			MvcResult  result=	mokMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "10")).andReturn();
			//请求成功以后，请求域中会有pageInfo,我们可以取出pageInfo进行验证
			MockHttpServletRequest request=result.getRequest();
			PageInfo attribute=(PageInfo) request.getAttribute("pageInfo");

			logger.info("+++++++++++++++++++++++++++当前页面:<"+attribute.getPageNum()+">++++++++++++++++++++++++++++++++++++++");
			logger.info("+++++++++++++++++++++++++++总页面:<"+attribute.getPages()+">++++++++++++++++++++++++++++++++++++++");
			logger.info("+++++++++++++++++++++++++++总记录数:<"+attribute.getTotal()+">++++++++++++++++++++++++++++++++++++++");
			logger.info("+++++++++++++++++++++++++++页面需要连续显示的页面++++++++++++++++++++++++++++++++++++++");
			int[]  navigatePageNums=attribute.getNavigatepageNums();
			for(int i=0;i<navigatePageNums.length;i++) {
				logger.info(""+navigatePageNums[i]);
			}
			logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			logger.info("+++++++++++++++++++++++++++获员工信息++++++++++++++++++++++++++++++++++++++");
			//获员工信息
			List<Employee> empls= attribute.getList();
			for(Employee empl:empls) {
				logger.info(""+empl.toString());
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
