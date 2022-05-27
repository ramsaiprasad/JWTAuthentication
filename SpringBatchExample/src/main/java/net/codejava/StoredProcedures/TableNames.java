package net.codejava.StoredProcedures;

public class TableNames {
	
	private String name;
	
	private  int TotalSpaceKB;
	private int UsedSpaceKB;
	private int UnusedSpaceKB;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSpaceKB() {
		return TotalSpaceKB;
	}

	public void setTotalSpaceKB(int totalSpaceKB) {
		TotalSpaceKB = totalSpaceKB;
	}

	public int getUsedSpaceKB() {
		return UsedSpaceKB;
	}

	public void setUsedSpaceKB(int usedSpaceKB) {
		UsedSpaceKB = usedSpaceKB;
	}

	public int getUnusedSpaceKB() {
		return UnusedSpaceKB;
	}

	public void setUnusedSpaceKB(int unusedSpaceKB) {
		UnusedSpaceKB = unusedSpaceKB;
	}

	public TableNames() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TableNames(String name, int totalSpaceKB, int usedSpaceKB, int unusedSpaceKB) {
		super();
		this.name = name;
		TotalSpaceKB = totalSpaceKB;
		UsedSpaceKB = usedSpaceKB;
		UnusedSpaceKB = unusedSpaceKB;
	}

	
	

}
