package net.codejava.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="productItems")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String productName;
	private String quantity;
	private byte[] productContent;
	
	
	
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Long id, String productName, String quantity, byte[] productContent) {
		super();
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
		this.productContent = productContent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public byte[] getProductContent() {
		return productContent;
	}
	public void setProductContent(byte[] productContent) {
		this.productContent = productContent;
	}
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<ProductExtend> productextend;

	public List<ProductExtend> getProductextend() {
		return productextend;
	}
	public void setProductextend(List<ProductExtend> productextend) {
		this.productextend = productextend;
	}
	
	

}
