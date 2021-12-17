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
    public TableView<Doctor_information> table;
    @FXML
    private TableColumn<Doctor_information, Integer> Doctor_Id;

    @FXML
    private TableColumn<Doctor_information, String> Doctor_name;

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
                Doctor_information obj = new Doctor_information();
                Doctor_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
                Doctor_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            String sql = "select * from doctor";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Doctor_information dc = new Doctor_information();
                dc.name = rs.getString("name");
                dc.id = rs.getInt("id");
                table.getItems().add(dc);
            }
            System.out.println("Data has been deleted!");
        } catch (Exception e) {
            e.printStackTrace();
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
            Main.admin.setPassword(cont.a_password.getText());
            Main.admin.setId(Integer.parseInt(cont.a_username.getText()));
            if (!Main.admin.getPassword().equals(SQLDbConnection.getAdminPassword(Main.admin.getId()))) {
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
            Main.patient.setPassword(cont.p_password.getText());
            Main.patient.setPersonalHealthNumber(Integer.parseInt(cont.p_id.getText()));
            System.out.println("ID " + SQLDbConnection.getAdminPassword(Main.patient.getPersonalHealthNumber()));
            System.out.println("Password " + Main.patient.getPassword());
            if (!Main.patient.getPassword().equals(SQLDbConnection.getPatientPassword(Main.patient.getPersonalHealthNumber()))) {
                cont.P_LABEL.setText("Incorrect Password or Username!! ");
            } else {
                m.changescene("screen1.fxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Doctor_Authority_Login(ActionEvent event) throws IOException {
        try {
            Main.doc = new Doctor();
            Main.doc.setPassword(cont.d_pas.getText());
            Main.doc.setId(Integer.parseInt(cont.d_id.getText()));
            System.out.println(Main.doc.getId());
            if (Main.doc.getPassword().equals(SQLDbConnection.getDoctorPassword(Main.doc.getId()))) {
                m.changescene("d_home.fxml");
            } else {
                cont.D_LABEL.setText("Incorrect Password or UserID!! ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Add_Bed(ActionEvent event) {
        SQLDbConnection.insertBed();
        System.out.println("Successfully");
    }

    @FXML
    void Doctor_Add_Verify(ActionEvent e) throws IOException {
        Main.doc = new Doctor();
        try {
            Main.doc.id = Integer.parseInt(cont.id.getText());
            Main.doc.name = cont.name.getText();
            Main.doc.age = Integer.parseInt(cont.age.getText());
            Main.doc.password = cont.password.getText();
            Main.doc.gender = cont.gender.getText();
            Main.doc.salary = Integer.parseInt(cont.salary.getText());
            Main.doc.Address = cont.address.getText();
            SQLDbConnection.insertDoctor(Main.doc.id, Main.doc.name, Main.doc.gender, Main.doc.Address, Main.doc.age, Main.doc.salary, Main.doc.password);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        cont = m.changescene("admin_methods.fxml");
    }

    @FXML
    void Doctor_Adding(ActionEvent e) throws IOException {
        cont = m.changescene("Doctor_info.fxml");
    }

    @FXML
    void d_login(ActionEvent event) throws IOException {
        cont = m.changescene("d_login.fxml");
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
            String name = cont.p_name.getText();
            int Age = Integer.parseInt(cont.patient_age.getText());
            String Disease = cont.patient_disease.getText();
            String Address = cont.patient_Address.getText();
            String password = cont.p_pass.getText();
            String gender = set_radio();
            Patient.createNewAccount(Age, gender, Address, Disease, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.changescene("p_login.fxml");
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

    @FXML
    public void Remove_Bed(ActionEvent event) {

    }

    @FXML
    public void remove_doctor(ActionEvent event) throws IOException {
        cont = m.changescene("a_removeDoctor.fxml");
    }

    @FXML
    public void Remove(ActionEvent event) throws IOException {
        SQLDbConnection.deleteDoctor(Integer.parseInt(cont.Remove_id.getText()));
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
    void goto_home(ActionEvent event) {

    }

    @FXML
    void goto_report(ActionEvent event) throws IOException {
        cont = m.changescene("d_report.fxml");
    }

    void show_Report(int PHN) throws SQLException {
        String name = "Ahmed ";
        int age = 34;
//        ResultSet rs = SQLDbConnection.getPatientPassword(PHN);
//        // we are reading one row, so no need to loop
//        System.out.println(rs );
//        if(rs != null)
//        {
//            while (rs.next()) {
//                if (rs.getString(1).equals(Integer.toString(PHN))) {
//                    name = rs.getString("name");
//                    age = rs.getInt("age");
//                }
//            }
//        }

        cont.r_name_label.setText(name);
        cont.r_age_label.setText(Integer.toString(age));
    }


    @FXML
    void input_report(ActionEvent event) throws IOException, SQLException {
        PHN = Integer.parseInt(cont.P_PHN.getText());
        show_Report(PHN);
    }

    @FXML
    public void d_home(ActionEvent event) throws IOException {
        m.changescene("d_home.fxml");
    }
}
