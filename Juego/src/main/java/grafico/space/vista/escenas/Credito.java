package grafico.space.vista.escenas;

import grafico.space.modelo.InfoLabel;
import grafico.space.modelo.Subescena;

public class Credito {
    private Subescena creditsSubsc;

    public Credito(){
        createCreditsSubScene();
    }

    private void createCreditsSubScene() {
        creditsSubsc = new Subescena();

        InfoLabel label = new InfoLabel("CREDITOS");
        label.setLayoutY(25);
        label.setLayoutX(110);

        creditsSubsc.getPane().getChildren().add(label);
    }

    public Subescena getCreditsSubsc() {
        return creditsSubsc;
    }

}
