package net.codejava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="videoPlaye")
public class VideoPlayer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String filename;
	private String url;
	private String size;
	
	
	
	
	public VideoPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VideoPlayer(Long id, String filename, String url, String size) {
		super();
		this.id = id;
		this.filename = filename;
		this.url = url;
		this.size = size;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	
	
	

}
