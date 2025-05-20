package grafico.space.vista;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import grafico.space.modelo.*;
import grafico.space.entidades.*;
import grafico.space.vista.escenas.Ayuda;
import grafico.space.vista.escenas.Credito;
import grafico.space.vista.escenas.Estadistica;

import java.util.ArrayList;

public class ViewManager {
    private final int width = 1024;
    private final int height = 768;
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;
    private final int menuBottonX = 100;
    private final int menuBottonY = 150;
    private ArrayList<BotonSpace> bottons;
    private ArrayList<Nave> naveList;
    private ArrayList<ElegirNave> shipList;
    private Nave choosenShip;
    private Subescena creditsSubsc;
    private Subescena shipChooserSubsc;
    private Subescena scoreSubsc;
    private Subescena helpSubsc;
    private XMLScoreManager scores;

    private Subescena subToHide;

    public ViewManager(){
        bottons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, width,height);
        mainStage = new Stage();
        mainStage.setMaxWidth(width+16);
        mainStage.setMaxHeight(height+39);
        mainStage.setScene(mainScene);
        createBotton();
        createBackground();
        scores = new XMLScoreManager();
    }

    private void showSubScene(Subescena subscene){

            if (subToHide != null){
                subToHide.moveSubscene();

                PauseTransition wait = new PauseTransition(Duration.seconds(0.3));
                wait.setOnFinished(e -> {
                    mainPane.getChildren().remove(subToHide);
                    if (shipChooserSubsc == subToHide) shipChooserSubsc = null;
                    if (creditsSubsc == subToHide) creditsSubsc = null;
                    if (scoreSubsc == subToHide) scoreSubsc = null;
                    if (helpSubsc == subToHide) helpSubsc = null;

                    if (subToHide != subscene){
                        subscene.moveSubscene();
                        subToHide = subscene;
                    }
                });
                wait.play();
            } else {
                subscene.moveSubscene();
                subToHide = subscene;
            }

    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuBotton(BotonSpace botton){
        botton.setLayoutX(menuBottonX);
        botton.setLayoutY(menuBottonY + bottons.size() * 100);
        bottons.add(botton);
        mainPane.getChildren().add(botton);

    }

    private void createStartBotton(){
        BotonSpace start = new BotonSpace("START");
        addMenuBotton(start);

        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (shipChooserSubsc == null){
                    grafico.space.vista.escenas.ElegirNave s = new grafico.space.vista.escenas.ElegirNave(mainStage);
                    mainPane.getChildren().add(s.getShipChooserSubsc());
                    shipChooserSubsc=s.getShipChooserSubsc();
                }
                showSubScene(shipChooserSubsc);
            }
        });
    }

    private void createScoreBotton(){
        BotonSpace score = new BotonSpace("SCORE");
        addMenuBotton(score);

        score.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (scoreSubsc == null){
                    Estadistica e = new Estadistica();
                    mainPane.getChildren().add(e.getScoreSubsc());
                    scoreSubsc = e.getScoreSubsc();
                }
                showSubScene(scoreSubsc);
            }
        });
    }

    private void createHelpBotton(){
        BotonSpace help = new BotonSpace("HELP");
        addMenuBotton(help);

        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (helpSubsc == null){
                    Ayuda a = new Ayuda();
                    mainPane.getChildren().add(a.getHelpSubsc());
                    helpSubsc = a.getHelpSubsc();
                }
                showSubScene(helpSubsc);
            }
        });
    }

    private void createCreditsBotton(){
        BotonSpace credits = new BotonSpace("CREDITS");
        addMenuBotton(credits);

        credits.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (creditsSubsc == null){
                    Credito c = new Credito();
                    mainPane.getChildren().add(c.getCreditsSubsc());
                    creditsSubsc = c.getCreditsSubsc();
                }
                showSubScene(creditsSubsc);
            }
        });
    }

    private void createExitBotton(){
        BotonSpace exit = new BotonSpace("EXIT");
        addMenuBotton(exit);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void  createBotton(){
        createStartBotton();
        createScoreBotton();
        createCreditsBotton();
        createHelpBotton();
        createExitBotton();
        createLogo();

    }

    private void createBackground(){
        Image bg = new Image("deep_blue.png",256,256,false,true);
        BackgroundImage background = new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("space_runner.png");
        logo.setLayoutX(400);
        logo.setLayoutY(50);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }
}
