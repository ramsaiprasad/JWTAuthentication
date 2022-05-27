package net.codejava.model.FollowerLists;

public class FollowerList {
	
	private Long id;
	private String username;
	private byte[] profilePic;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public byte[] getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}
	public FollowerList() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
