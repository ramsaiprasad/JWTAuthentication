package net.codejava.model.FollowerLists;

import java.util.List;

import net.codejava.model.Statuses;

public class statusess {
	
	private Long id;
	private byte[] content;
	private String description;
	private Long useridd;
	
	private List<Statuses> statuses;
	
	
	public List<Statuses> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<Statuses> statuses) {
		this.statuses = statuses;
	}
	public Long getUseridd() {
		return useridd;
	}
	public void setUseridd(Long useridd) {
		this.useridd = useridd;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public statusess(Long id, byte[] content, String description,Long useridd) {
		super();
		this.id = id;
		this.content = content;
		this.description = description;
		this.useridd=useridd;
	}
	public statusess() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
