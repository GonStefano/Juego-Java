package grafico.space.modelo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.InputStream;

public class InfoLabel extends Label {
    private final String font_path = "/kenvector_future.ttf";
    private final String bgImage = "/red_small_panel.png";

    public InfoLabel(String text){
        setPrefWidth(380);
        setPrefHeight(49);
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);
        BackgroundImage bgroundImage = new BackgroundImage(new Image(bgImage,380,49,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(bgroundImage));
    }

    /**
     * Aplica la fuente personalizada a la etiqueta.
     * Si no se encuentra el archivo de fuente, se utiliza "Verdana" como fuente alternativa.
     */
    private void setLabelFont(){
        try {
            InputStream font = getClass().getResourceAsStream(font_path);
            setFont(Font.loadFont(font,23));
        }catch (Exception e){
            setFont(Font.font("Verdana",23));
        }
    }
}
