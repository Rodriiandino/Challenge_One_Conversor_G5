module main.converter {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.converter to javafx.fxml;
    exports main.converter;
}