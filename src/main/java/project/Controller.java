package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    int PHN;
    @FXML
    public TextField a_username;
    @FXML
    public TextField a_password;
    @FXML
    public Button p_signup;

    @FXML
    public TextField patient_age;
    @FXML
    public TextField patient_disease;
    @FXML
    public TextField patient_Address;

    //    Doctor_info elements
    @FXML
    public TextField id;
    @FXML
    public TextField name;
    @FXML
    public TextField gender;
    @FXML
    public TextField address;
    @FXML
    public TextField age;
    @FXML
    public TextField salary;
    @FXML
    public TextField password;
    @FXML
    public ComboBox Gender;
//    End

    @FXML
    public RadioButton male;
    @FXML
    public RadioButton female;
    @FXML
    public ToggleGroup select;
    public TextField p_name;

    @FXML
    public PasswordField p_pass;
    public PasswordField d_pas;
    public TextField d_id;
    public Label A_LABEL;
    public Label D_LABEL;
    public Label P_LABEL;

    //  Patient_Login
    @FXML
    public TextField p_id;
    @FXML
    public TextField p_password;


    @FXML
    private Button admin_button;

    @FXML
    private Button doc_button;

    @FXML
    private Button nurse_button;

    @FXML
    private Button back;


    @FXML
    private Label address_label;

    @FXML
    private Label age_label;

    @FXML
    private Button d_home_btn;

    @FXML
    private Button d_prescribe_btn;

    @FXML
    private Button d_report_btn;

    @FXML
    private Label gender_label;

    @FXML
    private Label id_label;

    @FXML
    private Label name_label;

    @FXML
    private Label salary_label;

    @FXML
    private Button patient_button;

    @FXML
    private TextField P_PHN;

    @FXML
    private TextField P_PRESCRIPTION;


    //    Remove Doctor
    @FXML
    public TableView<Doctor_information> table_Doctor;
    @FXML
    public TableView<Bed_Information> table_bed;
    @FXML
    private TableColumn<Doctor_information, Integer> Doctor_Id;

    @FXML
    private TableColumn<Doctor_information, String> Doctor_name;

    @FXML
    private TableColumn<Bed_Information, Integer> Bed_Id;

    @FXML
    private TableColumn<Bed_Information, Integer> Patient_PHN;

    @FXML
    private TextField Admit_Bed_ID;


    @FXML
    private TableView<Bed_Information> table_admit;
    @FXML
    private TableColumn<Bed_Information, Integer> Availabilty;

    @FXML
    private TableColumn<Bed_Information, Integer> BBed;

    @FXML
    private TextField Remove_inp;

    @FXML
    private TextField Remove_id;

    @FXML
    private Button r_button;


    //    Report Page
    @FXML
    private Label r_address_label;

    @FXML
    private Label r_age_label;

    @FXML
    private Label r_disease_name;

    @FXML
    private Label r_gender_label;

    @FXML
    private Label r_name_label;


//    Doctor_Home
    @FXML
    private Label d_ID_label;

    @FXML
    private Label d_address_label;

    @FXML
    private Label d_age_label;

    @FXML
    private Label d_gender_label;

    @FXML
    private Label d_name_label;

    @FXML
    private Label d_salary_label;


//    Patient_Report
@FXML
private Label p_PHN_label;

    @FXML
    private Label p_address_label;

    @FXML
    private Label p_age_label;

    @FXML
    private Label p_disease_label;

    @FXML
    private Label p_gender_label;

    @FXML
    private Label p_name_label;

    @FXML
    private Label p_bill_label;


//    Patient_ Prescription
    @FXML
    private Label patient_doctor_prescription;

    @FXML
    private Label Bill_label;

    @FXML
    private Button remove_btn;
//    Patient report
    @FXML
    private Label Report_bill_report;


//    Book Appointment
    @FXML
    private TextField appointemnt_day;

    @FXML
    private TextField appointemnt_month;

    @FXML
    private TextField appointment_name;


    @FXML
    private Label add_bed;
//    Patient Admit

    @FXML
    private Label Invalid_label;
    @FXML
    private TextField Admit_patient_PHN;

    @FXML
    private RadioButton D_Female;

    @FXML
    private RadioButton D_male;

    @FXML
    private Label SIGN_Label;
    static Controller cont;
    Main m = new Main();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection con = SQLDbConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (Doctor_Id != null) {
            System.out.println("CALLED DOCTOR REMOVED");
            try {
                Doctor_information doctor = new Doctor_information();
                Doctor_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
                Doctor_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
            String sql = "select * from doctor";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Doctor_information dc = new Doctor_information();
                dc.name = rs.getString("name");
                dc.id = rs.getInt("id");
                table_Doctor.getItems().add(dc);
            }
            System.out.println("Data has been deleted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (Bed_Id != null) {
            System.out.println("CALLED Bed REMOVED");
            try {
                Bed_Information bed = new Bed_Information();
                Patient_PHN.setCellValueFactory(new PropertyValueFactory<>("PHN"));
                Bed_Id.setCellValueFactory(new PropertyValueFactory<>("bedID"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if(table_bed != null) {
                    String sql = "select * from ward";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        Bed_Information bd = new Bed_Information();
                        bd.setPHN(rs.getInt("PHN"));
                        bd.setBedID(rs.getInt("bedID"));
                        table_bed.getItems().add(bd);
                    }
                }
                System.out.println("ward has been deleted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (Availabilty != null) {
            System.out.println("CALLED Admit ward REMOVED");
            try {
                Doctor_information doctor = new Doctor_information();
                BBed.setCellValueFactory(new PropertyValueFactory<>("bedID"));
                Availabilty.setCellValueFactory(new PropertyValueFactory<>("PHN"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String sql = "select * from ward where PHN = '0'";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Bed_Information bd = new Bed_Information();
                    bd.setPHN(rs.getInt("PHN"));
                    bd.setBedID(rs.getInt("bedID"));
                    table_admit.getItems().add(bd);
                }
                System.out.println("Data has been deleted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void a_login(ActionEvent event) throws IOException {
        cont = m.changescene("a_login.fxml");
    }


    @FXML
    void Admin_Authority_Login(ActionEvent event) throws IOException {
        try {
            Main.admin = new Admin();
            if(cont.a_password.getText().equals("") || cont.a_username.getText().equals(""))
            {
                cont.A_LABEL.setText("Enter UserID or Password!! ");
                return;
            }
            Main.admin.setPassword(cont.a_password.getText());
            Main.admin.setId(Integer.parseInt(cont.a_username.getText()));
            if (!Main.admin.getPassword().equals(SQLDbConnection.getAdminPassword(Main.admin.getId()))) {
                cont = m.changescene("a_login.fxml");
                cont.A_LABEL.setText("Incorrect Password or Username!! ");
            } else {
                m.changescene("admin_methods.fxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Patient_Authority_Login(ActionEvent event) throws IOException {
        try {
            Main.patient = new Patient();
            if(cont.p_password.getText().equals("") || cont.p_id.getText().equals(""))
            {
                cont.P_LABEL.setText("Enter UserID or Password!! ");
                return;
            }
            Main.patient.setPassword(cont.p_password.getText());
            Main.patient.setPersonalHealthNumber(Integer.parseInt(cont.p_id.getText()));
            if (!Main.patient.getPassword().equals(SQLDbConnection.getPatientPassword(Main.patient.getPersonalHealthNumber()))) {
                cont = m.changescene("p_login.fxml");
                cont.P_LABEL.setText("Incorrect Password or Username!! ");
            } else {
                cont = m.changescene("p_home.fxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void Doctor_Authority_Login(ActionEvent event) throws IOException {
        try {
            Main.doc = new Doctor();
            if(cont.d_pas.getText().equals("") || cont.d_id.getText().equals(""))
            {
                cont.D_LABEL.setText("Enter UserID or Password!! ");
                return;
            }
            Main.doc.setPassword(cont.d_pas.getText());
            Main.doc.setId(Integer.parseInt(cont.d_id.getText()));
            if (Main.doc.getPassword().equals(SQLDbConnection.getDoctorPassword(Main.doc.getId()))) {
                cont = m.changescene("d_home.fxml");
                set_Doctor_Dashboard(Main.doc.getId());

            } else {
                cont =  m.changescene("d_login.fxml");
                cont.D_LABEL.setText("Incorrect Password or UserID!! ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void set_Doctor_Dashboard(int id)
    {
        Main.doc.name = SQLDbConnection.getDoctorName(id);
        cont.d_name_label.setText(Main.doc.name);
        Main.doc.age = SQLDbConnection.getDoctorAge(id);
        cont.d_age_label.setText(String.valueOf(Main.doc.age));
        Main.doc.gender = SQLDbConnection.getDoctorGender(id);
        cont.d_gender_label.setText(Main.doc.gender);
        Main.doc.salary = Integer.parseInt(SQLDbConnection.getDoctorSalary(id));
        cont.d_salary_label.setText(String.valueOf(Main.doc.salary));
        Main.doc.Address = SQLDbConnection.getDoctorAddress(id);
        cont.d_address_label.setText(Main.doc.Address);
        cont.d_ID_label.setText(String.valueOf(Main.doc.id));
    }


    @FXML
    void Add_Bed(ActionEvent event) throws IOException {
        cont = m.changescene("admin_methods.fxml");
        SQLDbConnection.insertBed();
        cont.add_bed.setText("Bed Added Successfully!! ");

    }

    @FXML
    void Doctor_Add_Verify(ActionEvent e) throws IOException {
        Main.doc = new Doctor();
        try {
            try{
                Main.doc.id = Integer.parseInt(cont.id.getText());
            }catch (NumberFormatException exception)
            {
                cont.Invalid_label.setText("Invalid Credentials!! ");
                return;
            }
            Main.doc.name = cont.name.getText();
            Main.doc.age = Integer.parseInt(cont.age.getText());
            Main.doc.password = cont.password.getText();
            Main.doc.gender = set_radio2();
            try{
                Main.doc.id = Integer.parseInt(cont.salary.getText());
            }catch (NumberFormatException exception)
            {
                cont.Invalid_label.setText("Invalid Credentials!! ");
                return;
            }
            Main.doc.Address = cont.address.getText();
            cont = m.changescene("admin_methods.fxml");
            SQLDbConnection.insertDoctor(Main.doc.id, Main.doc.name, Main.doc.gender, Main.doc.Address, Main.doc.age, Main.doc.salary, Main.doc.password);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    void Doctor_Adding(ActionEvent e) throws IOException {
        cont = m.changescene("Doctor_info.fxml");
    }

    @FXML
    void d_login(ActionEvent event) throws IOException {
        cont = m.changescene("d_login.fxml");
        Main.patient.setPersonalHealthNumber(Integer.parseInt(cont.p_id.getText()));
        set_Doctor_Dashboard(Main.doc.getId());
    }

    @FXML
    void n_login(ActionEvent event) throws IOException {
        m.changescene("n_login.fxml");
    }

    @FXML
    void Home_screen(ActionEvent event) throws IOException {
        m.changescene("screen1.fxml");
    }

    @FXML
    void SIGNUP(ActionEvent e) throws IOException {
        cont = m.changescene("signup_Screen.fxml");

    }

    @FXML
    void p_login(ActionEvent event) throws IOException {
            cont = m.changescene("p_login.fxml");
    }

    @FXML
    void p_login_setinfo(ActionEvent event) throws IOException {
        try {
            int Age = 0;
            String name = cont.p_name.getText();
            try{
                Age = Integer.parseInt(cont.patient_age.getText());
            }catch (NumberFormatException exception)
            {
                cont.SIGN_Label.setText("Invalid Credentials!! ");
                return;
            }
            String Disease = cont.patient_disease.getText();
            String Address = cont.patient_Address.getText();
            String password = cont.p_pass.getText();
            String gender = set_radio();
            Patient.createNewAccount(Age, gender, Address, Disease, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cont = m.changescene("p_login.fxml");
    }

    String set_radio() {
        try {
            if (cont.male.isSelected())
                return "male";
            else if (cont.female.isSelected())
                return "female";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    String set_radio2() {
        try {
            if (cont.D_male.isSelected())
                return "male";
            else if (cont.D_Female.isSelected())
                return "female";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    public void Remove_Bed(ActionEvent event) throws IOException {
        cont  = m.changescene("a_removeBed.fxml");
    }

    @FXML
    public void remove_doctor(ActionEvent event) throws IOException {
        cont = m.changescene("a_removeDoctor.fxml");
    }



    @FXML
    public void Remove_Doctor_Confirmation(ActionEvent event) throws IOException {
        System.out.println(Integer.parseInt(cont.Remove_id.getText()));
        SQLDbConnection.deleteDoctor(Integer.parseInt(cont.Remove_id.getText()));
        m.changescene("admin_methods.fxml");
    }


    @FXML
    public void Remove_Bed_Confirmation(ActionEvent event) throws IOException {
        SQLDbConnection.removeBed(Integer.parseInt(cont.Remove_inp.getText()));
        m.changescene("admin_methods.fxml");
    }
    @FXML
    public void Prescription_page(ActionEvent event) throws IOException {
        cont = m.changescene("d_prescribe.fxml");
    }

    @FXML
    public void Submit_prescription(ActionEvent event) throws IOException {
        m.changescene("d_home.fxml");
        System.out.println("PHN " + cont.P_PHN.getText());
        SQLDbConnection.setPatientPrescription(cont.P_PRESCRIPTION.getText(), Integer.parseInt(cont.P_PHN.getText()));
    }

    @FXML
    void goto_report(ActionEvent event) throws IOException {
        cont = m.changescene("d_report.fxml");
    }

    void show_Report(int PHN) throws SQLException {
        Main.patient = new Patient();
        Main.patient.name = SQLDbConnection.getPatientName(PHN);
        System.out.println(SQLDbConnection.getPatientName(PHN));
        System.out.println(Main.patient.name);
        Main.patient.age = SQLDbConnection.getPatientAge(PHN);
        Main.patient.setAddress(SQLDbConnection.getPatientAddress(PHN));
        Main.patient.gender = SQLDbConnection.getPatientGender(PHN);
        Main.patient.setDisease(SQLDbConnection.getPatientDisease(PHN));
        cont.r_name_label.setText(Main.patient.name);
        cont.r_address_label.setText(Main.patient.Address);
        cont.r_disease_name.setText(Main.patient.getDisease());
        cont.r_gender_label.setText(Main.patient.getGender());
        cont.r_age_label.setText(Integer.toString(Main.patient.age));
    }


    @FXML
    void input_report(ActionEvent event) throws IOException, SQLException {
        PHN = Integer.parseInt(cont.P_PHN.getText());
        show_Report(PHN);
    }

    @FXML
    public void d_home(ActionEvent event) throws IOException {
        cont  = m.changescene("d_home.fxml");
        set_Doctor_Dashboard(Main.doc.getId());
    }

    @FXML
    public void change_Report_Page(ActionEvent event) throws IOException {
        cont = m.changescene("p_report.fxml");
    }

    @FXML
    public void Patient_report_Generation(ActionEvent event) throws IOException {
        cont = m.changescene("p_report.fxml");
        int PHN = Main.patient.getPersonalHealthNumber();
        Main.patient.name = SQLDbConnection.getPatientName(PHN);
        cont.p_name_label.setText(Main.patient.name);
        Main.patient.age = SQLDbConnection.getPatientAge(PHN);
        cont.p_age_label.setText(String.valueOf(Main.patient.age));

        Main.patient.setAddress(SQLDbConnection.getPatientAddress(PHN));
        cont.p_address_label.setText(Main.patient.Address);

        Main.patient.gender = SQLDbConnection.getPatientGender(PHN);
        cont.p_gender_label.setText(Main.patient.gender);
        cont.p_PHN_label.setText(String.valueOf(Main.patient.getPersonalHealthNumber()));

        Main.patient.setDisease(SQLDbConnection.getPatientDisease(PHN));
        cont.p_disease_label.setText(Main.patient.getDisease());

        Main.patient.setBill(SQLDbConnection.getBill(PHN));
        cont.p_bill_label.setText(String.valueOf(Main.patient.getBill()));
        SQLDbConnection.addBill(200, Main.patient.getPersonalHealthNumber());
    }

    @FXML
    public void Generate_prescription_btn(ActionEvent event) throws IOException {
        cont = m.changescene("p_prescription.fxml");
    }

    @FXML
    public void P_prescription_generator(ActionEvent event) throws IOException {
        cont = m.changescene("p_prescription.fxml");
        String pres = SQLDbConnection.getPatientPrescription(Main.patient.getPersonalHealthNumber());
        cont.patient_doctor_prescription.setText(pres);
        SQLDbConnection.addBill(500, Main.patient.getPersonalHealthNumber());
    }

    @FXML
    public void p_home(ActionEvent event) throws IOException {
        cont  = m.changescene("p_home.fxml");
    }



    @FXML
    public void Book_Appointment(ActionEvent event) throws IOException {
        cont  = m.changescene("p_book.fxml");
    }

    @FXML
    public void Confirm_Appointment(ActionEvent event) throws IOException {
       SQLDbConnection.setAppointment(cont.appointment_name.getText(),Main.patient.getPersonalHealthNumber(),Integer.parseInt(cont.appointemnt_day.getText()),cont.appointemnt_month.getText());
       m.changescene("p_home.fxml");
    }

    @FXML
    public  void Admit_page_Change(ActionEvent event) throws IOException {
        cont = m.changescene("p_admit.fxml");
    }

    @FXML
    public void Reserved_Bed(ActionEvent event) throws IOException {
        int id  = Integer.parseInt(cont.Admit_Bed_ID.getText());
        SQLDbConnection.admitPatient(id,Main.patient.getPersonalHealthNumber());
        m.changescene("p_admit.fxml");
    }


}
