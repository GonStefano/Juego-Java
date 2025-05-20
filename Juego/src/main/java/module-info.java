module juegos.juegos {
    requires javafx.controls;
    requires javafx.fxml;


    opens juegos.juegos to javafx.fxml;
    exports juegos.juegos;
}