package model;

public class Comment {
	
	private int id;
	private String time;
	private String content;
	private int user_id;
	private int topic_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(String time, String content, int user_id, int topic_id) {
		super();
		this.time = time;
		this.content = content;
		this.user_id = user_id;
		this.topic_id = topic_id;
	}
	

}
