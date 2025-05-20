package grafico.space.vista.escenas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import grafico.space.modelo.InfoLabel;
import grafico.space.modelo.Subescena;
import grafico.space.vista.XMLScoreManager;

import java.io.InputStream;

public class Estadistica {
    private Subescena scoreSubsc;
    private XMLScoreManager scores;

    public Estadistica(){
        scores = new XMLScoreManager();
        createScoreSubscene();
    }

    private void createScoreSubscene(){
        scoreSubsc = new Subescena();
        InfoLabel title = createTitleForSubscene("ESTADISTICA");
        VBox box = new VBox();
        box.setStyle("-fx-background-color: rgba(0, 0, 0, 0.2); -fx-background-radius: 30;");
        box.setPrefWidth(380);
        box.setSpacing(20);
        box.setLayoutX(110);
        box.setLayoutY(100);
        box.setPadding(new Insets(20,20,20,20));

        scores.leerXML();
        int cont =0;
        for (grafico.space.vista.Estadistica s : scores.getScores()){
            cont++;
            String text = cont + ". " + s.getScore();
            Label lb = createLabelScore(text);
            box.getChildren().add(lb);
        }
        box.setAlignment(Pos.CENTER);
        scoreSubsc.getPane().getChildren().add(title);
        scoreSubsc.getPane().getChildren().add(box);
    }

    private  InfoLabel createTitleForSubscene(String text){
        InfoLabel label = new InfoLabel(text);
        label.setLayoutX(110);
        label.setLayoutY(25);
        return label;
    }

    private Label createLabelScore(String text){
        Label label = new Label(text);
        label.setLayoutX(50);
        label.setLayoutY(10);
        try {
            InputStream input = getClass().getResourceAsStream("/kenvector_future.ttf");
            label.setFont(Font.loadFont(input,23));
        }catch (Exception e){
            label.setFont(Font.font("verdana",23));
        }
        return label;
    }

    public Subescena getScoreSubsc() {
        return scoreSubsc;
    }

}
