package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.ToIntFunction;

public class Patient extends Person {
	private int personalHealthNumber;
	private String disease;
	private int roomNum;

	public Patient(int PHN, String D, int R, String n, String g, String a, int ag) {
		super(n, g, a, ag);
		personalHealthNumber = PHN;
		disease = D;
		roomNum = R;
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

	public static int createNewAccount(int age, String gender, String address, String disease, String name, String Password) {
		int phn= SQLDbConnection.insertPatient(age, gender, address, disease, name, Password);
		return phn;
	}
	
	
		
	
}
