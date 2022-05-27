package net.codejava.model.FollowerLists;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import net.codejava.model.Followers;
import net.codejava.model.Statuses;
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class SearchUser {
	
	private Long id;
	private String username;
	private Integer followersCount;
	private Integer followingCount;
	private String isfollowing;
	
	
	
	
	
	
	public Integer getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	public String getIsfollowing() {
		return isfollowing;
	}

	public void setIsfollowing(String isfollowing) {
		this.isfollowing = isfollowing;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	private byte[] profilePic;
	
	private List<Followers> followers;
	
	private List<statusess> statuses;
	
	
	

	public List<statusess> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<statusess> statuses) {
		this.statuses = statuses;
	}

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

	public List<Followers> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Followers> followers) {
		this.followers = followers;
	}
	

}
