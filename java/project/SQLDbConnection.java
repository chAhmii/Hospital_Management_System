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
			System.out.println("SQLite Connected!");
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

	public static ResultSet getPatientPassword(int PHN) {
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
		return rs;
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
			String sql = "Select name from Doctor where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			name = rs.getString(1);
			System.out.println(name);
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
			String sql = "Select gender from Doctor where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			gender = rs.getString(1);
			System.out.println(gender);
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

	public static String getDoctorAge(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String age = "-1";
		try {
			String sql = "Select age from Doctor where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			age = rs.getString(1);
			System.out.println(age);
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

	public static String getDoctorSalary(int id) {
		// lets read specific row on the database
		Connection con = SQLDbConnection.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String salary = "-1";
		try {
			String sql = "Select salary from Doctor where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			rs = ps.executeQuery();

			// we are reading one row, so no need to loop
			salary = rs.getString(1);
			System.out.println(salary);
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



}
