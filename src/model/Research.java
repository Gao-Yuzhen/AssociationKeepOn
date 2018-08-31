package model;

public class Research {
	
	private int id;
	private String title;
	private String content;
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
	public void setTitle(String title) {
		this.title = title;
	}
	public Research(int id, String title, String content, int associ_id) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.associ_id = associ_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAssoci_id() {
		return associ_id;
	}
	public void setAssoci_id(int associ_id) {
		this.associ_id = associ_id;
	}
	public Research() {
		super();
		// TODO Auto-generated constructor stub
	}

}
