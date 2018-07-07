package com.mp.beans;

/**
 *  定义JavaBean中成员变量时所使用的类型
 *  因为每一个基本数据类型都有一个默认值
 *  int  --> 0
 *  boolean --> false
 *   包装类型使用更符合实际：null
 *   学生考试，如果没有参加则应该是null，和参加考试只考了0分，意义则不同 
 * @author Administrator
 */
public class Employee {
	private Integer id ;
	private String lastName;
	private String email ;
	private Integer gender ;
	private Integer age ;
	
	
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
