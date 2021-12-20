package project;

public class Doctor extends Staff {
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	String password;

	public Doctor(String n, String g, String a, int ag, int id, int salary, String password) {
		super(n, g, a, ag, id, salary);
		this.password = password;
	}

	public Doctor(){}

	public void generatePrescription(Patient P) {
		String disease = P.getDisease();
		if (disease.equalsIgnoreCase("Corona")) {
			System.out.println("2 pills panadol daily");
		} else if (disease.equalsIgnoreCase("flu")) {
			System.out.println("1 pill kestine daily");
		} else if (disease.equalsIgnoreCase("cough")) {
			System.out.println("1 pill Acefyl daily");
		}
	}
}
