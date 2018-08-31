package model;

public class UserAssoci {

	private int uId;
	private int aId;
	private String status;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public String getStatus() {
		return status;
	}
	public UserAssoci() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAssoci(int uId, int aId, String status) {
		super();
		this.uId = uId;
		this.aId = aId;
		this.status = status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
