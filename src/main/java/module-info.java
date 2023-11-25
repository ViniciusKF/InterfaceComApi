module com.mycompany.interfacecomapi {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.interfacecomapi to javafx.fxml;
    exports com.mycompany.interfacecomapi;
}
