package net.codejava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="adre")
public class Adress {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String state;
	private String district;
	private String taluka;
	private String village;
	private String country;
	
	
	
	
	
	public Adress() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Adress(Long id, String state, String district, String taluka, String village, String country) {
		super();
		this.id = id;
		this.state = state;
		this.district = district;
		this.taluka = taluka;
		this.village = village;
		this.country = country;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTaluka() {
		return taluka;
	}
	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@OneToOne
    @PrimaryKeyJoinColumn
	private JUser user;





	public JUser getUser() {
		return user;
	}


	public void setUser(JUser user) {
		this.user = user;
	}
	
	
	
	
	

}
