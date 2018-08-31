package model;

public class Volunteer {

	private int id;
	private String name;
	private String time;
	private String capacity;
	private int associ_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public Volunteer(int id, String name, String time, String capacity, int associ_id) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.capacity = capacity;
		this.associ_id = associ_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public int getAssoci_id() {
		return associ_id;
	}
	public void setAssoci_id(int associ_id) {
		this.associ_id = associ_id;
	}
	public Volunteer() {
		super();
		// TODO Auto-generated constructor stub
	}
}
