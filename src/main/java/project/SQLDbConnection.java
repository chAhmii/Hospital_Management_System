package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class SQLDbConnection {
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "urdu12345"); // connecting
																											// to our
																											// database
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e + "");
		}
		return con;
	}

	public static int insertPatient(int age, String gender, String address, String disease,
			String name, String password) {
			Connection con = SQLDbConnection.connect();
			PreparedStatement ps = null;
			int PHN = Integer.parseInt(readMaxPHN());
			PHN++;
			try {
				String sql = "INSERT INTO Patient(personalHealthNumber, age, gender, address, disease, name, bill, password, prescription) VALUES(?,?,?,?,?,?,?,?,?) ";
				ps = con.prepareStatement(sql);
				ps.setString(1, Integer.toString(PHN));
				ps.setString(2, Integer.toString(age));
				ps.setString(3, gender);
				ps.setString(4, address);
				ps.setString(5, disease);
				ps.setString(6, name);
				ps.setString(7, Integer.toString(0));
				ps.setString(8, password);
				ps.setString(9, "");
				ps.execute();
				System.out.println("Data has been inserted!");
			} catch (SQLException e) {
				System.out.println(e.toString());
			} finally {
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
			return PHN;
	}

	public static String readMaxPHN() {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String PHN = "-1";
		try {
			String sql = "Select * from patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while(rs.next())
			{
				PHN = rs.getString(1);

			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}

		return PHN;
	}

	public static void deleteDoctor(int id) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "delete from Doctor WHERE id = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.execute();
			System.out.println("Data has been deleted!");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	public static void insertDoctor(int id, String name, String gender, String address, int age, int salary, String password) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO Doctor(id, name, gender, address, age, salary, password) VALUES(?,?,?,?,?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ps.setString(2, name);
			ps.setString(3, gender);
			ps.setString(4, address);
			ps.setString(5, Integer.toString(age));
			ps.setString(6, Integer.toString(salary));
			ps.setString(7, password);
			ps.execute();
			System.out.println("Data has been inserted!");
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}


	public static String getDoctorPassword(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Password = "-1";
		try {
			String sql = "Select * from doctor";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while(rs.next())
			{
				if(rs.getString(1).equals(Integer.toString(id))){
					Password = rs.getString("password");
				}


			}
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return Password;
	}

	public static String getPatientPassword(int PHN) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Password = "-1";
		try {
			String sql = "Select * from patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while(rs.next())
			{
				if(rs.getString(1).equals(Integer.toString(PHN))){
					Password = rs.getString("password");
				}
			}
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return Password;
	}

	public static String getPatientName(int phn) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Name = "-1";
		try {
			String sql = "Select * from Patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("personalHealthNumber").equals(Integer.toString(phn))) {
					Name = rs.getString("name");
				}
			}
//			System.out.println(Name);
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Patient id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		System.out.println("Name in databae " + Name);
		return Name;
	}

	public static int getPatientAge(int phn) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int Age = -1;
		try {
			String sql = "Select * from Patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("personalHealthNumber").equals(Integer.toString(phn))) {
					Age = rs.getInt("age");
				}
			}
//			System.out.println(Name);
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Patient id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return Age;
	}

	public static String getPatientGender(int phn) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String gender = "-1";
		try {
			String sql = "Select * from Patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("personalHealthNumber").equals(Integer.toString(phn))) {
					gender = rs.getString("gender");
				}
			}
//			System.out.println(Name);
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Patient id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return gender;
	}

	public static String getPatientAddress(int phn) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String address = "-1";
		try {
			String sql = "Select * from Patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("personalHealthNumber").equals(Integer.toString(phn))) {
					address = rs.getString("address");
				}
			}
//			System.out.println(Name);
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Patient id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return address;
	}

	public static String getPatientDisease(int phn) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String disease = "-1";
		try {
			String sql = "Select * from Patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("personalHealthNumber").equals(Integer.toString(phn))) {
					disease = rs.getString("disease");
				}
			}
//			System.out.println(Name);
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Patient id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return disease;
	}

	public static void setPatientPrescription(String Prescription, int phn) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE patient set prescription = ? WHERE personalHealthNumber = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, Prescription);
			ps.setString(2, Integer.toString(phn));
			ps.execute();
			System.out.println("Data has been updated");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}
	public static boolean isEmpty(int bedID) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int phn = 0;
		try {
			String sql = "Select * from ward";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString(1).equals(Integer.toString(bedID))) {
					phn = rs.getInt("phn");
				}
			}
		} catch (SQLException e) {
			System.out.println("bedID is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		if (phn == 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean admitPatient(int bedId, int PHN) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		boolean result = false;
		try {
			if (isEmpty(bedId)) {
				String sql = "UPDATE ward set PHN = ? WHERE bedID = ? ";
				ps = con.prepareStatement(sql);
				ps.setString(1, Integer.toString(PHN));
				ps.setString(2, Integer.toString(bedId));
				ps.execute();
				System.out.println("Bed has been booked for Patient");
				result = true;
			}
			else {
				System.out.println("Please choose an empty bed");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return result;
	}
	public static String getAdminPassword(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Password = "-1";
		try {
			String sql = "Select * from admin";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while(rs.next())
			{
				if(rs.getString(1).equals(Integer.toString(id))){
					Password = rs.getString(2);
				}


			}
		} catch (SQLException e) {
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return Password;
	}
	public static String getDoctorName(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "-1";
		try {
			String sql = "Select * from doctor";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while(rs.next())
			{
				if(rs.getString(1).equals(Integer.toString(id))){
					name = rs.getString(2);
				}
			}
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return name;
	}

	public static String getDoctorGender(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String gender = "-1";
		try {
			String sql = "Select * from Doctor";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("id").equals(Integer.toString(id))) {
					gender = rs.getString("gender");
				}
			}
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return gender;
	}

	public static int getDoctorAge(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int age = -1;
		try {
			String sql = "Select * from Doctor";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("id").equals(Integer.toString(id))) {
					age = rs.getInt("age");
				}
			}
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return age;
	}

	public static int getBill(int PHN) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int bill = 0;
		try {
			String sql = "Select * from patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString(1).equals(Integer.toString(PHN))) {
					bill = rs.getInt("bill");
				}
			}
		} catch (SQLException e) {
			System.out.println("PHN is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return bill;
	}

	public static void addBill(int bill, int phn) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		int bill2 = getBill(phn);
		bill2 += bill;
		try {
			String sql = "UPDATE Patient set bill = ? WHERE personalHealthNumber = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(bill2));
			ps.setString(2, Integer.toString(phn));
			ps.execute();
			System.out.println("Data has been updated");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
	}

	public static String getPatientPrescription(int phn) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Prescription = "-1";
		try {
			String sql = "Select * from Patient";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("personalHealthNumber").equals(Integer.toString(phn))) {
					Prescription = rs.getString("prescription");
				}
			}
			System.out.println(Prescription);
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Patient id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return Prescription;
	}
	public static String getDoctorSalary(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String salary = "-1";
		try {
			String sql = "Select * from Doctor";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("id").equals(Integer.toString(id))) {
					salary = String.valueOf(rs.getInt("salary"));
				}
			}
		} catch (SQLException e) {
//					System.out.println(e.toString());
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return salary;
	}

	public static String getDoctorAddress(int id) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String address = "-1";
		try {
			String sql = "Select * from Doctor";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while (rs.next()) {
				if (rs.getString("id").equals(Integer.toString(id))) {
					address = rs.getString("address");
				}
			}
		} catch (SQLException e) {
			System.out.println("Doctor id is not found");
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return address;
	}
	public static int getMaxBed() {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String PHN = "-1";
		int result = -1;
		try {
			String sql = "Select * from ward";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			while(rs.next())
			{
				PHN = rs.getString(1);

			}
			result = Integer.parseInt(PHN);
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			// close connections
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
		}
		return result;
	}

	public static int insertBed() {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		int bedId = getMaxBed();
		bedId++;
		System.out.println(bedId);
		try {
			String sql = "INSERT INTO Ward(bedID, PHN) VALUES(?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(bedId));
			ps.setString(2, Integer.toString(0));
			ps.execute();
			System.out.println("Data has been inserted!");
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		return bedId;
	}

	/////////////////////////////////////////////////////

	private static void readAllData() {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM Account";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("ALL USERS\n");
			while (rs.next()) {
				String Name = rs.getString("name");
				String address = rs.getString("address");
				String ph = rs.getString("phone");
				String acc = rs.getString("acc");

				System.out.println("Name: " + Name);
				System.out.println("Address: " + address);
				System.out.println("Phone number: " + ph);
				System.out.println("Account number: " + acc + "\n\n");

			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	private static void deleteRow(String arg) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "delete from users WHERE email = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, arg);
			ps.execute();
			System.out.println("Data has been deleted!");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void setAppointment(String doctor, int PHN, int day, String month) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "INSERT INTO Appointments(Doctor, PHN, Day, Month) VALUES(?,?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, doctor);
			ps.setString(2, Integer.toString(PHN));
			ps.setString(3, Integer.toString(day));
			ps.setString(4, month);
			ps.execute();
			System.out.println("Data has been inserted!");
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}

	public static void removeBed(int bedId) {
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		try {
			String sql = "delete from ward WHERE bedId = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(bedId));
			ps.execute();
			System.out.println("Bed has been deleted!");
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
