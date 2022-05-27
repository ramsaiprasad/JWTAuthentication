package net.codejava.StoredProcedures;

public class UserRequest {
	
	private Integer blogId;
	private String blogTitle;
	private Integer yearOfPost;
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequest(Integer blogId, String blogTitle, Integer yearOfPost) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.yearOfPost = yearOfPost;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public Integer getYearOfPost() {
		return yearOfPost;
	}
	public void setYearOfPost(Integer yearOfPost) {
		this.yearOfPost = yearOfPost;
	}
	
	

}
