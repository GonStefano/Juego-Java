package grafico.space.vista.escenas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import grafico.space.modelo.InfoLabel;
import grafico.space.modelo.BotonSpace;
import grafico.space.modelo.Subescena;
import grafico.space.entidades.*;
import grafico.space.vista.GestorJuego;

import java.util.ArrayList;

public class ElegirNave {
    private ArrayList<Nave> naveList;
    private ArrayList<grafico.space.modelo.ElegirNave> shipList;
    private Subescena shipChooserSubsc;
    private Nave choosenShip;
    private Stage mainStage;

    public ElegirNave(Stage mainStage){
        this.mainStage = mainStage;
        createShipChoosenSubscene();
    }

    private void createShipChoosenSubscene(){
        shipChooserSubsc = new Subescena();
        InfoLabel label = new InfoLabel("ELIGE TU NAVE");
        label.setLayoutX(110);
        label.setLayoutY(25);
        shipChooserSubsc.getPane().getChildren().add(label);
        shipChooserSubsc.getPane().getChildren().add(createShipToChoose());
        shipChooserSubsc.getPane().getChildren().add(createRunnerBotton());
    }

    private HBox createShipToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipList = new ArrayList<>();
        naveList = new ArrayList<>();
        NaveRoja rojo = new NaveRoja();
        NaveAzul azul = new NaveAzul();
        NaveNaranja naranja = new NaveNaranja();
        NaveVerde verde = new NaveVerde();
        naveList.add(rojo);
        naveList.add(azul);
        naveList.add(naranja);
        naveList.add(verde);
        for (Nave n : naveList){
            grafico.space.modelo.ElegirNave shipToPick = new grafico.space.modelo.ElegirNave(n);
            shipList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for (grafico.space.modelo.ElegirNave ship : shipList){
                        ship.setIsCircleChoosen(false);
                    }
                    shipToPick.setIsCircleChoosen(true);
                    choosenShip = shipToPick.getNave();
                }
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }

    private BotonSpace createRunnerBotton(){
        BotonSpace botton = new BotonSpace("START");
        botton.setLayoutY(300);
        botton.setLayoutX(350);

        botton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (choosenShip != null){
                    GestorJuego game = new GestorJuego(mainStage);
                    game.createNewGame(choosenShip);
                }
            }
        });

        return botton;
    }

    public Subescena getShipChooserSubsc() {
        return shipChooserSubsc;
    }

}
