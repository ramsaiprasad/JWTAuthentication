package net.codejava;

public class AccountLock1 {

	private Long Id;
	private int count;
	
	

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public AccountLock1(int count) {
		super();
		this.count = count;
	}

	public AccountLock1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

