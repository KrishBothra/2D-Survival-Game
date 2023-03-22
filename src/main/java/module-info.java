module com.example.template {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.template to javafx.fxml;
    exports com.example.template;
}