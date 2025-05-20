module juegos.juegos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens juegos.juegos to javafx.fxml;
    exports juegos.juegos;
}