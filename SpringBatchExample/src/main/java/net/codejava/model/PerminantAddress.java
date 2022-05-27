package net.codejava.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="perminantAdress")
public class PerminantAddress {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=true)
	private String village;
	@Column(nullable=true)
	private String street;
	@Column(nullable=true)
	private String landmark;
	@Column(nullable=true)
	private String Taluka;
	@Column(nullable=true)
	private String district;
	@Column(nullable=true)
	private String state;
	@Column(nullable=true)
	private String country;
	
	
	
	
	
	public PerminantAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PerminantAddress(Long id, String village, String street, String landmark, String taluka, String district,
			String state, String country) {
		super();
		this.id = id;
		this.village = village;
		this.street = street;
		this.landmark = landmark;
		Taluka = taluka;
		this.district = district;
		this.state = state;
		this.country = country;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getTaluka() {
		return Taluka;
	}
	public void setTaluka(String taluka) {
		Taluka = taluka;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
