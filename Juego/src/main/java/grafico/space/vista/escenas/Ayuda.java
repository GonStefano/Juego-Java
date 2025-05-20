package grafico.space.vista.escenas;

import grafico.space.modelo.InfoLabel;
import grafico.space.modelo.Subescena;

public class Ayuda {
    private Subescena helpSubsc;

    public Ayuda() {
        helpSubsc = new Subescena();

        InfoLabel label = new InfoLabel("AYUDA");
        label.setLayoutY(25);
        label.setLayoutX(110);

        helpSubsc.getPane().getChildren().add(label);
    }

    public Subescena getHelpSubsc() {
        return helpSubsc;
    }
}