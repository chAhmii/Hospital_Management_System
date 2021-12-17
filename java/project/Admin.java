package project;

public class Admin extends Staff{
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

    public Admin(){}
	public Admin(String n, String g, String a, int ag, int id, int salary, String password) {
		super(n, g, a, ag, id, salary);
		this.password = password;
	}
	
	public void addDoctor(String name, String gender, String add, int ag, int id, int salry, String passwrd) {
		SQLDbConnection.insertDoctor(id, name, gender, add, ag, salry, passwrd);
	}
}
