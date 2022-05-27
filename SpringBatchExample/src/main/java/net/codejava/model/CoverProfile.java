package net.codejava.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="coverprofile")
public class CoverProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String url;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public CoverProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CoverProfile(Long id, String url) {
		super();
		this.id = id;
		this.url = url;
	}
	@OneToOne(fetch=FetchType.LAZY,optional=false,
			cascade=CascadeType.ALL)
	private JUser user;
	public JUser getUser() {
		return user;
	}
	public void setUser(JUser user) {
		this.user = user;
	}
	
	

}
