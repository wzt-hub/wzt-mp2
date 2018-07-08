package com.mp.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mp.beans.Employee;

/**
 * Mapper接口：
 * 1.基于MyBatis：在Mapper接口中编写CRUD相关的方法，还需要提供Mapper接口
 * 		所对应的SQL映射文件，以及方法对应的SQL语句。
 * 2.基于MyBatis-Plus：只需要让Mapper接口继承BaseMapper接口即可
 * 		BaseMapper<T> ：泛型T指的是当前Mapper接口所操作的实体类的类型
 * 3.Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能
 * @author Administrator
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
	/**
	 * 原生Mybatis插入一行数据之后，获取其主键值的方法：
	 * 1.首先在EmployeeMapper接口中定义方法： Integer insertEmployee(Employee employee);
	 * 2.在EmployeeMapper接口对应的xml文件中：使用useGeneratedKeys=true keyProperty="id" 来获取自增主键
	 * 		<insert useGeneratedKeys=true keyProperty="id">
	 * 			SQL语句...
	 * 		</insert>
	 * 如果为Mybatis-Plus 的话，则不需要做任何操作，因为Mybatis-Plus会自动的将自增主键值回写到实体类中
	 */
	
	
}
