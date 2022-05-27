package net.codejava.DBOperations;

public class EmployeeRequest {
	
	private Long id;
	private String name;
	
	private Long departmentname;

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

	public Long getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(Long departmentname) {
		this.departmentname = departmentname;
	}

	public EmployeeRequest(Long id, String name, Long departmentname) {
		super();
		this.id = id;
		this.name = name;
		this.departmentname = departmentname;
	}

	public EmployeeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
