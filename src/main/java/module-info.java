module com.fxfinals.fxapp_finals {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    opens com.fxfinals.fxapp_finals.controllers to javafx.fxml, javafx.base;
    exports com.fxfinals.fxapp_finals.controllers;

    opens com.fxfinals.fxapp_finals to javafx.fxml;
    exports com.fxfinals.fxapp_finals;
}