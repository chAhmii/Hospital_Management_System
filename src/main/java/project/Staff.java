package project;

public class Staff extends Person {
	protected int id;
	protected int salary;

	public Staff(String n, String g, String a, int ag, int id, int salary) {
		super(n, g, a, ag);
		this.id = id;
		this.salary = salary;
	}

	public Staff(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}
