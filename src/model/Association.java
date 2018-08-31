package model;

public class Association {

	private int id;
	public Association(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	private String name;
	private String password;
	private String status;
	private String leader;
	private String time;
	private int memberNum;
	private int finance;
	private int admin;
	public Association(int id, String name, String password, String leader) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.leader = leader;
	}
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
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public int getFinance() {
		return finance;
	}
	public void setFinance(int finance) {
		this.finance = finance;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public Association() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
