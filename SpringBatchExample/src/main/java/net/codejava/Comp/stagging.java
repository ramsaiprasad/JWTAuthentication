package net.codejava.Comp;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="inster")
public class stagging {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date inpunch;
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
	public Date getInpunch() {
		return inpunch;
	}
	public void setInpunch(Date date) {
		this.inpunch = date;
	}
	public stagging(Long id, String name, Date inpunch) {
		super();
		this.id = id;
		this.name = name;
		this.inpunch = inpunch;
	}
	public stagging() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
