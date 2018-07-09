package com.mp.beans;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 *  定义JavaBean中成员变量时所使用的类型
 *  因为每一个基本数据类型都有一个默认值
 *  int  --> 0
 *  boolean --> false
 *   包装类型使用更符合实际：null
 *   学生考试，如果没有参加则应该是null，和参加考试只考了0分，意义则不同 
 * @author Administrator
 */

/**
 * Mybatis-Plus会默认使用实体类的类名到数据库中找对应的表
 */
//如果在数据库中所有的表具有相同的前缀，则可以在applicationContext.xml文件中配置全局的表前缀策略
//@TableName(value="tbl_employee")    
public class Employee {
	/**
	 * @TableId
	 * 	value：指定表中的主键列的列名，如果实体属性名与列名一致，可以省略不指定
	 *  type：指定主键策略
	 */
	//可以在applicationContext.xml文件中配置全局的主键策略，这样就不必每一个实体类单独配置
	//@TableId(value="id",type=IdType.AUTO)
	private Integer id ;
	private String lastName;
	private String email ;
	private Integer gender ;
	private Integer age ;
	
	//不是数据表中的字段
	@TableField(exist=false)
	private Date time;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", age="
				+ age + "]";
	}
}
