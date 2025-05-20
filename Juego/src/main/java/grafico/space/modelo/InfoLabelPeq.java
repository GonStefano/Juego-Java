package grafico.space.modelo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.InputStream;

public class InfoLabelPeq extends Label {

    private String font_Path = "/kenvector_future.ttf";

    public InfoLabelPeq(String text){
        setPrefHeight(50);
        setPrefWidth(130);
        BackgroundImage bgImage = new BackgroundImage(new Image("/buttonBlue.png",130,50,false,true), BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(bgImage));
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10,10,10,10));
        setLabelFont();
        setText(text);
    }

    private void setLabelFont(){
        try {
            InputStream input = getClass().getResourceAsStream(font_Path);
            setFont(Font.loadFont(input,15));
        }catch (Exception e){
            setFont(Font.font("verdana",15));
        }
    }
}
