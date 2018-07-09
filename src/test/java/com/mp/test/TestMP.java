package com.mp.test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mp.beans.Employee;
import com.mp.mapper.EmployeeMapper;

class TestMP {
	private ApplicationContext ioc=
			new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmployeeMapper employeeMapper=ioc.getBean(EmployeeMapper.class);
	
	/**
	 *  测试通用的删除操作：根据ID 批量删除
	 *  Integer deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);
	 */
	@Test
	public void testDeleteBatchIds() {
		List<Integer> idList=new ArrayList<>();
		idList.add(18);
		idList.add(19);
		Integer result = employeeMapper.deleteBatchIds(idList);
		System.out.println(result);
	}
	
	/**
	 *  测试通用的删除操作：根据 columnMap 条件，删除记录
	 *  Integer deleteByMap(@Param("cm") Map<String, Object> columnMap);
	 * 执行发送的SQL语句：DELETE FROM tbl_employee WHERE last_name = ? AND email = ?
	 */
	@Test
	public void testDeleteByMap() {
		Map<String,Object> columnMap=new HashMap<>();
		columnMap.put("last_name", "MP-dec");
		columnMap.put("email", "2444@qq.com");
		Integer result = employeeMapper.deleteByMap(columnMap);
		System.out.println(result);
	}
	
	/**
	 *  测试通用的删除操作：根据 ID 删除
	 *  Integer deleteById(Serializable id);
	 */
	@Test
	public void testDeleteById() {
		Integer result = employeeMapper.deleteById(19);
		System.out.println(result);
	}
	
	/**
	 *  测试通用的查询：分页查询
	 *  List<T> selectPage(RowBounds rowBounds, @Param("ew") Wrapper<T> wrapper);
	 *    rowBounds 分页查询条件（可以为 RowBounds.DEFAULT）
	 *    wrapper   实体对象封装操作类（可以为 null）
	 *  发送的SQL语句为：SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee
	 *  由发送的SQL语句可知：SQL语句上并没有采用limit关键字进行分页，而是查询所有记录数，底层使用Mybatis RowBounds 进行内存分页
	 *  如果要使用真正的物理分页必须借助分页插件实现
	 */
	@Test
	public void testSelectPage() {
		//Page  参数1：当前页码数     参数2：每页显示记录数
		List<Employee> emps = employeeMapper.selectPage(new Page<>(2,2), null);
		for(Employee emp:emps) {
			System.out.println(emp);
		}
	}
	
	/**
	 *  测试通用的查询：根据 Map集合封装条件查询
	 *  List<T> selectByMap(@Param("cm") Map<String, Object> columnMap);
	 */
	@Test
	public void testSelectByMap() {
		Map<String,Object> columnMap=new HashMap<>();
		//通过名字和性别进行查询
		columnMap.put("last_name", "Tom");  //key必须是对应数据表的列名
		columnMap.put("gender", 1);
		List<Employee> emps = employeeMapper.selectByMap(columnMap);
		for(Employee emp:emps) {
			System.out.println(emp);
		}
	}
	
	/**
	 *  测试通用的查询：通过多个ID进行查询
	 *  List<T> selectBatchIds(@Param("coll") Collection<? extends Serializable> idList);      
	 *     idList 主键ID列表
	 * 运行执行的SQL语句：SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE id IN ( ? , ? , ? )
	 */
	@Test
	public void testSelectBatchIds() {
		List<Integer> idList=new ArrayList<>();
		idList.add(1);
		idList.add(4);
		idList.add(15);
		List<Employee> emps = employeeMapper.selectBatchIds(idList);
		for(Employee emp:emps) {
			System.out.println(emp);
		}
	}
	
	/**
	 *  测试通用的查询：根据 entity 条件，查询一条记录
	 *  T selectOne(@Param("ew") T entity);
	 *  执行的SQL语句： SELECT id,last_name AS lastName,email,gender,age FROM tbl_employee WHERE last_name=? AND email=?
	 */
	@Test
	public void testSelectOne() {
		//通过lastName + email 组合查询
		Employee employee=new Employee();
		employee.setLastName("Tom");
		employee.setEmail("tom@atguigu.com");
		
		Employee emp = employeeMapper.selectOne(employee);
		System.out.println(emp);
	}
	
	/**
	 *  测试通用的查询：根据 ID 查询
	 *  T selectById(Serializable id);
	 */
	@Test
	public void testSelectById() {
		Employee employee = employeeMapper.selectById(4);
		System.out.println(employee);
	}
	
	/**
	 * 测试 通用的更新所有列的方法
	 * Integer updateAllColumnById(@Param("et") T entity);
	 * 
	 * 执行时发送的SQL语句：UPDATE tbl_employee SET last_name=?,email=?,gender=?,age=? WHERE id=?
	 *                wqh(String), null, null, 22(Integer), 15(Integer)
	 * 更新时，没有设置的实体属性值会置为空，也就是会丢失，你不想修改的列的数据
	 */
	@Test
	public void testUpdateAllColumnById() {
		Employee employee=new Employee();
		employee.setId(15);
		employee.setLastName("wqh");
		employee.setAge(22);
		Integer result = employeeMapper.updateAllColumnById(employee);
		System.out.println(result);
	}
	
	/**
	 * 测试 通用的更新方法
	 * Integer updateById(@Param("et") T entity);
	 * 
	 * 执行时发送的SQL语句：UPDATE tbl_employee SET last_name=?, age=? WHERE id=?
	 * 对实体的每一个属性值进行非空判断，只有非空的属性对应的字段才会出现在SQL语句中
	 */
	@Test
	public void testUpdateById() {
		Employee employee=new Employee();
		employee.setId(15);
		employee.setLastName("wzt");
		employee.setAge(22);
		Integer result = employeeMapper.updateById(employee);
		System.out.println(result);
	}
	
	/**
	 * 测试通用 全字段插入操作：
	 * 		Integer insertAllColumn(T entity);
	 * 执行的SQL语句为：INSERT INTO tbl_employee ( last_name,email,gender,age ) VALUES ( ?,?,?,? )
	 * 
	 * 	注：insertAllColumn(T entity) 方法插入时，不管属性值是否非空，属性对应的字段都会出现到SQL语句中
	 * @throws SQLException
	 */
	@Test
	public void testInsertAllColumn() {
		Employee employee=new Employee();
		employee.setLastName("MP-test-2");
		employee.setEmail("25353@qq.com");
		
		employee.setTime(new Date());
		Integer result=employeeMapper.insertAllColumn(employee);
		System.out.println(result);
		
		//获取主键
		System.out.println(employee.getId());
	}
	
	
	/**
	 * 测试 通用的插入操作：
	 * 		Integer insert(T entity);
	 * 执行发送的SQL语句为：INSERT INTO tbl_employee ( last_name, email ) VALUES ( ?, ? )
	 * 	注：	insert(T entity) 执行时，通过实体类T对象的每一个属性做非空判断，如果某一个属性为空，则
	 *       该属性对应的字段就不会出现在SQL语句中，既就是只有非空的属性对应的字段才会出现在SQL语句中
	 * @throws SQLException
	 */
	@Test
	public void testInsert() {
		Employee employee=new Employee();
		employee.setLastName("MP-dec");
		employee.setEmail("2444722463@qq.com");
		/*employee.setGender(1);
		employee.setAge(25);*/
		Integer result=employeeMapper.insert(employee);
		System.out.println(result);
		
		//获取主键
		System.out.println(employee.getId());
	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource ds=ioc.getBean("dataSource", DataSource.class);
		System.out.println(ds);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}

}
