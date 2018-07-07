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
	
}
