module com.a2zbuysell.a2zbuysell {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.a2zbuysell.a2zbuysell to javafx.fxml;
    exports com.a2zbuysell.a2zbuysell;
}