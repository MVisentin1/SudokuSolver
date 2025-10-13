module com.visentin.sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.visentin.sudoku.controller to javafx.fxml;
    exports com.visentin.sudoku;
}