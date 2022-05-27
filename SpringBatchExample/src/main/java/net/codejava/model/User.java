package net.codejava.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name="TestUser")
//@JsonIdentityInfo(
//
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//
//        property = "id")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;

@ManyToMany(targetEntity = Role.class, fetch=FetchType.EAGER,
cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH} )
	@JoinTable(name="Test_User_Roles",
joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
//@JsonIgnore
//@ManyToMany(targetEntity = Role.class, 
//cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER)
	private List<Role> roles=new ArrayList<>();

	
	public User(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role)
	{
		this.roles.add(role);
	}
 public void remoRole(Role role)
 {
	 this.roles.remove(role);
 }
 

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + "]";
	}
//	@OneToOne(cascade = CascadeType.ALL)
//	private ImageStore imagestore;
//
//
//	public ImageStore getImagestore() {
//		return imagestore;
//	}
//
//
//	public void setImagestore(ImageStore imagestore) {
//		this.imagestore = imagestore;
//	}

}
