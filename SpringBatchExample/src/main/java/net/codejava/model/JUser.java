package net.codejava.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import net.codejava.ApproveRequest.FollowerRequest;
import net.codejava.ApproveRequest.MyrequetsTable;
import net.codejava.StoredProcedures.FieldValues;
import net.codejava.model.FollowerLists.Story;

@Entity
@Table(	name = "Jusers", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class JUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String email;
	private String password;
	private byte[] profilePic;
	private byte[] coverPic;
	private Date dateofbirth;
	private Date accountCreationDate;
	private String gender;
  @Column(nullable=true)	
	private Integer age;
  private String resetPasswordToke;
  private Integer failed_attempts;
 private Date  locktime;
 private String verificationCode;
 private String accountType;
 
 
 
 public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public String getVerificationCode() {
	return verificationCode;
}
public void setVerificationCode(String verificationCode) {
	this.verificationCode = verificationCode;
}

@Column(nullable=true)	
 private Integer accountLocked;
 
	
	
	public Integer getAccountLocked() {
	return accountLocked;
}
public void setAccountLocked(Integer accountLocked) {
	this.accountLocked = accountLocked;
}
	public Integer getFailed_attempts() {
		return failed_attempts;
	}
	public void setFailed_attempts(Integer failed_attempts) {
		this.failed_attempts = failed_attempts;
	}
	public Date getLocktime() {
		return locktime;
	}
	public void setLocktime(Date locktime) {
		this.locktime = locktime;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles_JWT", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	
	private Set<JRole> roles = new HashSet<>();
	public JUser() {
	}
	public JUser(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	

	public JUser(Long id,String username, String email, 
			String password, Integer status,byte[] profilePic,
			byte[] coverPic,Date dateofbirth,Date accountCreationDate,String gender,Integer age ) {
		super();
		this.id=id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
		this.profilePic=profilePic;
		this.coverPic=coverPic;
		this.dateofbirth=dateofbirth;
		this.accountCreationDate=accountCreationDate;
		this.gender=gender;
		this.age=age;
	}
	
	
	
	
	
	
	
	public JUser(List<Statuses> statuses) {
		super();
		this.statuses = statuses;
	}
	public byte[] getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(byte[] coverPic) {
		this.coverPic = coverPic;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	
	
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public Date getAccountCreationDate() {
		return accountCreationDate;
	}
	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Set<JRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<JRole> roles) {
		this.roles = roles;
	}
	
	public String getResetPasswordToke() {
		return resetPasswordToke;
	}
	public void setResetPasswordToke(String resetPasswordToke) {
		this.resetPasswordToke = resetPasswordToke;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	private ImageProfile profile;
	public ImageProfile getProfile() {
		return profile;
	}
	public void setProfile(ImageProfile profile) {
		this.profile = profile;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	private CoverProfile cover;
	public CoverProfile getCover() {
		return cover;
	}
	public void setCover(CoverProfile cover) {
		this.cover = cover;
	}
	
	public void addRole(JRole role)
	{
		this.roles.add(role);
	}
 public void remoRole(JRole role)
 {
	 this.roles.remove(role);
 }
 @OneToOne(cascade=CascadeType.ALL)
 private ProfilePhoto profilephoto;
 public ProfilePhoto getProfilephoto() {
	return profilephoto;
}
public void setProfilephoto(ProfilePhoto profilephoto) {
	this.profilephoto = profilephoto;
}


public JUser(Long id, String username, ProfilePhoto profilephoto, List<Posts> posts, Status statu) {
	super();
	this.id = id;
	this.username = username;
	this.profilephoto = profilephoto;
	this.posts = posts;
	this.statu = statu;
}
/*
 * using one to many relation ship
 */
@JsonIgnore
@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true)
 private List<Posts> posts;
 
public List<Posts> getPosts() {
	return posts;
}

public void setPosts(List<Posts> posts) {
	this.posts = posts;
}
@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
private List<Followers> followers;

@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<Following> following;
@OneToMany(mappedBy="user" ,cascade=CascadeType.ALL,orphanRemoval=true)
private List<Statuses> statuses;

@OneToMany
@JoinTable(name = "user_story_Table", 
joinColumns = @JoinColumn(name = "USER_ID"),
inverseJoinColumns = @JoinColumn(name = "Story_ID"))
private List<Story> story;

public List<FollowerRequest> getFollowersrequest() {
	return followersrequest;
}
public void setFollowersrequest(List<FollowerRequest> followersrequest) {
	this.followersrequest = followersrequest;
}

@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<FollowerRequest> followersrequest;
@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<MyrequetsTable> myrequests;


@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<FieldValues> FValues;








public List<FieldValues> getFValues() {
	return FValues;
}
public void setFValues(List<FieldValues> fValues) {
	FValues = fValues;
}
public List<MyrequetsTable> getMyrequests() {
	return myrequests;
}
public void setMyrequests(List<MyrequetsTable> myrequests) {
	this.myrequests = myrequests;
}
public List<Story> getStory() {
	return story;
}
public void setStory(List<Story> story) {
	this.story = story;
}
public List<Statuses> getStatuses() {
	return statuses;
}
public void setStatuses(List<Statuses> statuses) {
	this.statuses = statuses;
}
public List<Following> getFollowing() {
	return following;
}
public void setFollowing(List<Following> following) {
	this.following = following;
}
public List<Followers> getFollowers() {
	return followers;
}
public void setFollowers(List<Followers> followers) {
	this.followers = followers;
}

private Integer status;
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}

public Status getStatu() {
	return statu;
}
public void setStatu(Status statu) {
	this.statu = statu;
}
@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
private Status statu;

@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
private ProfilePicture picture;
public ProfilePicture getPicture() {
	return picture;
}
public void setPicture(ProfilePicture picture) {
	this.picture = picture;
}

@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
private Adress adress;
public Adress getAdress() {
	return adress;
}
public void setAdress(Adress adress) {
	this.adress = adress;
}
@OneToOne(mappedBy="user",cascade=CascadeType.ALL)
private PerminantAddress padress;
public PerminantAddress getPadress() {
	return padress;
}
public void setPadress(PerminantAddress padress) {
	this.padress = padress;
}

@OneToOne(cascade=CascadeType.ALL)
@JoinColumn(name="Account_Lock_Inforamtion")
private AccountLock attempts;



public AccountLock getAttempts() {
	return attempts;
}
public void setAttempts(AccountLock attempts) {
	this.attempts = attempts;
}




}
