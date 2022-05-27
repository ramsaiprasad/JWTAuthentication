package net.codejava.Comp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rect")
public class Tert {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	
	private Long id;
	public Tert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tert(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	private String name;
	private Long DeptId;

	
	
	public Long getDeptId() {
		return DeptId;
	}
	public void setDeptId(Long deptId) {
		DeptId = deptId;
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
	
	
	
	

}
