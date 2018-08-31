package model;

public class Finance {

	private int id;
	private String name;
	private int total;
	private int income;
	private int expense;
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	public int getAssoci_id() {
		return associ_id;
	}
	public void setAssoci_id(int associ_id) {
		this.associ_id = associ_id;
	}
	public Finance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Finance(String name, int income, int expense, int associ_id) {
		super();
		this.name = name;
		this.income = income;
		this.expense = expense;
		this.associ_id = associ_id;
		this.total=income-expense;
	}
	
}
