module Project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens project to javafx.fxml;
    exports project;
}