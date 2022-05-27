package net.codejava.model.FollowerLists;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Story_Table")
public class Story {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private byte[] story;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getStory() {
		return story;
	}
	public void setStory(byte[] story) {
		this.story = story;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Story(Long id, byte[] story, Long userId) {
		super();
		this.id = id;
		this.story = story;
		this.userId = userId;
	}
	public Story() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
