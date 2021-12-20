package project;

import java.sql.SQLException;

public class Patient extends Person {
	private int personalHealthNumber;
	private String disease;

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	private int bill;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String password;
	private int roomNum;

	public Patient(int PHN, String D, int R, String n, String g, String a, int ag) {
		super(n, g, a, ag);
		personalHealthNumber = PHN;
		disease = D;
		roomNum = R;
	}

	public Patient(){
		super();
	}
	public int getPersonalHealthNumber() {
		return personalHealthNumber;
	}

	public void setPersonalHealthNumber(int personalHealthNumber) {
		this.personalHealthNumber = personalHealthNumber;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public static void createNewAccount(int age, String gender, String address, String disease, String name, String password) throws SQLException {
		String Max = SQLDbConnection.readMaxPHN();
	    SQLDbConnection.insertPatient(age, gender, address, disease, name,password);
	}


}
