module main.converter {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens main.converter to javafx.fxml;
    exports main.converter;
}