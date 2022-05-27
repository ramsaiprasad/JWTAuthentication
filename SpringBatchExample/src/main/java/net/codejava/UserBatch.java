package net.codejava;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BatchProcessing")
public class UserBatch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String dept;
	private int salary;
	private int leave;
	public UserBatch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserBatch(Long id, String name, String dept, int salary,int leave) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		this.leave=leave;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getLeave() {
		return leave;
	}
	public void setLeave(int leave) {
		this.leave = leave;
	}
	@Override
	public String toString() {
		return "UserBatch [id=" + id + ", name=" + name + ", dept=" + dept + ", salary=" + salary + ", leave=" + leave
				+ "]";
	}
	
	
	
	

}
