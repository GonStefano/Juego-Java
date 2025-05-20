package grafico.space.modelo;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import grafico.space.entidades.Nave;

public class ElegirNave extends VBox {
    private ImageView circleImage;
    private ImageView naveImagen;

    private String circleNotChoosen = "/grey_circle.png";
    private String circleChoosen = "/red_choosen.png";
    private Nave nave;

    private boolean isCircleChoosen;

    public ElegirNave(Nave nave){
        circleImage = new ImageView(circleNotChoosen);
        naveImagen = new ImageView(nave.getImagen());
        this.nave=nave;
        isCircleChoosen=false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(naveImagen);
    }

    public Nave getNave() {
        return nave;
    }

    public boolean getIsCircleChoosen() {
        return isCircleChoosen;
    }

    public void setIsCircleChoosen(boolean isCircleChoosen){
        this.isCircleChoosen=isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }
}
