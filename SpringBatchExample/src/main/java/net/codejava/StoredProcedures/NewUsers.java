package net.codejava.StoredProcedures;
import javax.persistence.*;
@Entity
@Table(name = "blog")
@NamedStoredProcedureQueries({
//@NamedStoredProcedureQuery(name = "getAllBlogs",procedureName = "getAllBlogs"),
@NamedStoredProcedureQuery(name = "getAllBlogs", procedureName = "getAllBlogs", parameters = {@StoredProcedureParameter(mode = ParameterMode.IN,name = "tblogId",type=int.class)} )})

public class NewUsers {
	
	@Id
	private Integer blogId;
	private String blogTitle;
	private Integer yearOfPost;
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
	public NewUsers(Integer blogId, String blogTitle, Integer yearOfPost) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.yearOfPost = yearOfPost;
	}
	public NewUsers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
