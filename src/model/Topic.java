package model;

public class Topic {

	private int id;
	private String title;
	private String content;
	private String time;
	private int user_id;
	private int associ_id;
	public int getAssoci_id() {
		return associ_id;
	}
	public void setAssoci_id(int associ_id) {
		this.associ_id = associ_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Topic(int id, String title, String content, String time, int user_id,int associ_id) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.user_id = user_id;
		this.associ_id = associ_id;
	}
	
}
