module com.example.projectics108 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectics108 to javafx.fxml;
    exports com.example.projectics108;
}