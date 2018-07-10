package com.mp.test;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mp.beans.Employee;

class TestMP {
	private ApplicationContext ioc=
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	/**
	 * 1.使用ActiveRecord 进行插入操作
	 */
	@Test
	public void testARInsert() {
		Employee employee=new Employee();
		employee.setLastName("张三");
		employee.setEmail("wqh@qq.com");
		employee.setAge(10);
		employee.setGender(1);
		employee.setTime(new Date());
		
		//进行插入操作
		boolean result = employee.insert();
		System.out.println(result);
	}
	
	/**
	 * 2.使用ActiveRecord 进行修改操作
	 */
	@Test
	public void testARUpdate() {
		Employee employee=new Employee();
		employee.setId(20);
		employee.setLastName("zs");
		employee.setEmail("zs@qq.com");
		employee.setAge(35);
		
		boolean result = employee.updateById();
		System.out.println(result);
	}
	
	/**
	 * 3.使用ActiveRecord 进行查询操作
	 */
	@Test
	public void testARSelect() {
		/**
		 * 1.根据ID进行查询
		 */
		/*Employee employee=new Employee();
		employee.setId(20);                    //方法1
		Employee emp = employee.selectById();
		//Employee emp = employee.selectById(20);   //方法2
		System.out.println(emp);*/
		/**
		 * 2.查询所有记录数
		 */
		/*Employee employee=new Employee();
		List<Employee> emps = employee.selectAll();
		for(Employee emp:emps) {
			System.out.println(emp);
		}*/
		
		/**
		 * 3.根据条件构造器进行查询
		 */
		/*Employee employee=new Employee();
		EntityWrapper<Employee> wrapper=new EntityWrapper<>();
		wrapper.between("age", 25, 35);
		wrapper.eq("gender", 0);
		List<Employee> emps = employee.selectList(wrapper);
		for(Employee emp:emps) {
			System.out.println(emp);
		}*/
		
		/**
		 * 4.根据条件构造器查询总记录数
		 * 	查询所有性别为女且年龄在35-50之间的总人数
		 * 运行执行的SQL语句：SELECT COUNT(1) FROM tbl_employee WHERE (gender = ? AND age BETWEEN ? AND ?)
		 */
		Employee employee=new Employee();
		int count = employee.selectCount(new EntityWrapper<Employee>()
				.eq("gender", 0)
				.between("age", 35, 50));
		System.out.println(count);
	}
	
	/**
	 * 4.使用ActiveRecord 进行删除操作
	 * 	注意：删除不存在的数据逻辑上也是属于成功的
	 */
	@Test
	public void testARDelete() {
		/**
		 * 1.根据ID删除操作
		 */
		/*Employee employee=new Employee();
		//employee.setId(22);
		//boolean result = employee.deleteById();
		boolean result = employee.deleteById(21);
		System.out.println(result);*/
		
		/**
		 * 2.根据条件进行删除操作
		 */
		Employee employee=new Employee();
		boolean result = employee.delete(new EntityWrapper<Employee>().eq("gender", 1)
				.like("email", "zs"));
		System.out.println(result);
	}
	
	/**
	 * 5.使用ActiveRecord 进行分页复杂操作 分页复杂操作
	 */
	@Test
	public void testARPageQuery() {
		Employee employee=new Employee();
		Page<Employee> pages = employee.selectPage(new Page<Employee>(1,3), new EntityWrapper<Employee>()
				.eq("gender", 0));
		List<Employee> emps = pages.getRecords();   //获取分页数据
		for(Employee emp:emps) {
			System.out.println(emp);
		}
	}
}
