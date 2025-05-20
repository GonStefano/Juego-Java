package grafico.space.modelo;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.InputStream;

public class BotonSpace extends Button {
    private final String font_path = "/kenvector_future.ttf";
    private final String botton_pressed = "-fx-background-color: transparent; -fx-background-image: url('/yellow_button_pressed.png');";
    private final String botton_released = "-fx-background-color: transparent; -fx-background-image: url('/yellow_button.png');";

    public BotonSpace(String text){
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(botton_released);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            InputStream font = getClass().getResourceAsStream(font_path);
            setFont(Font.loadFont(font,23));
        }catch (Exception e){
            setFont(Font.font("verdana",23));
        }
    }

    private void setBottonPressedStyle(){
        setStyle(botton_pressed);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setBottonReleasedStyle(){
        setStyle(botton_released);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setBottonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    setBottonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
    }

}
