package com.mp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mp.mapper.EmployeeMapper;

class TestMP {
	private ApplicationContext ioc=
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmployeeMapper employeeMapper=ioc.getBean(EmployeeMapper.class);
	
	
	
	
	

}
