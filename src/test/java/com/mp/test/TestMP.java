package com.mp.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mp.beans.Employee;
import com.mp.mapper.EmployeeMapper;

class TestMP {
	private ApplicationContext ioc=
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmployeeMapper employeeMapper=ioc.getBean(EmployeeMapper.class);
	/**
	 * 测试 通用的插入操作
	 * @throws SQLException
	 */
	@Test
	public void testCommonInsert() {
		Employee employee=new Employee();
		employee.setLastName("MP");
		employee.setEmail("2444722463@qq.com");
		employee.setGender(1);
		employee.setAge(25);
		employeeMapper.insert(employee);
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource ds=ioc.getBean("dataSource", DataSource.class);
		System.out.println(ds);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}

}
