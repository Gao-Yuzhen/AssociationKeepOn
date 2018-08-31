package model;

public class Place {
	
	private int id;
	private String name;
	private String status;
	private int admin_id;
	private String capacity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public Place() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Place(String name, String status, String capacity,int admin_id) {
		super();
		this.name = name;
		this.status = status;
		this.admin_id = admin_id;
		this.capacity = capacity;
	}
	

}
