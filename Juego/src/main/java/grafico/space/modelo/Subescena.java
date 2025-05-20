package grafico.space.modelo;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class Subescena extends SubScene {
    private final String font_Path = "/kenvector_future.ttf";
    private final String bg_image = "yellow_panel.png";
    private boolean isHidden=true;
    public Subescena() {
        super(new AnchorPane(),600,400);
        prefHeight(400);
        prefWidth(600);
        BackgroundImage image = new BackgroundImage(new Image(bg_image,600,400,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root = (AnchorPane) this.getRoot();

        root.setBackground(new Background(image));

        setLayoutX(1024);
        setLayoutY(180);
    }


    public void moveSubscene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden){
            transition.setToX(-676);
            isHidden=false;
        } else {
            transition.setToX(0);
            isHidden=true;
        }

        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
