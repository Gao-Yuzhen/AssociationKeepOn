package model;

public class Activity {
	
	public int id=0;
	private String name;
	private String status;
	private String time;
	private String place;
	private String target;
	private String introduce;
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
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIntroduce() {
		return introduce;
	}
	public Activity(int id,String name, String status, String time, String place, String target, String introduce,
			int associ_id) {
		super();
		this.id=id;
		this.name = name;
		this.status = status;
		this.time = time;
		this.place = place;
		this.target = target;
		this.introduce = introduce;
		this.associ_id = associ_id;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getAssoci_id() {
		return associ_id;
	}
	public void setAssoci_id(int associ_id) {
		this.associ_id = associ_id;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
