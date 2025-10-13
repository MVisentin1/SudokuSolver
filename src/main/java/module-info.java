module com.visentin.s {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.visentin.s.controller to javafx.fxml;
    exports com.visentin.s;
}