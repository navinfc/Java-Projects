module main.ecommerce_d {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens main.ecommerce_d to javafx.fxml;
    exports main.ecommerce_d;
}