package model;

public class Vote {
	
	private int id;
	private String title;
	private String time;
	private int associ_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public Vote(int id, String title, String time, int associ_id) {
		super();
		this.id = id;
		this.title = title;
		this.time = time;
		this.associ_id = associ_id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getAssoci_id() {
		return associ_id;
	}
	public void setAssoci_id(int associ_id) {
		this.associ_id = associ_id;
	}
	public Vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
