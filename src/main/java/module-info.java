module com.example.template {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.template to javafx.fxml;
    exports com.example.template;
}