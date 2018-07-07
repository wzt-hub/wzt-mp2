package com.mp.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestMP {
	private ApplicationContext ioc=
			new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testDataSource() throws SQLException {
		DataSource ds=ioc.getBean("dataSource", DataSource.class);
		System.out.println(ds);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}

}
