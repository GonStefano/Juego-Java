module juegos.juegos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens juegos.juegos to javafx.fxml;
    exports juegos.juegos;
}