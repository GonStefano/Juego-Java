package grafico.space.vista;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import grafico.space.modelo.InfoLabelPeq;
import grafico.space.entidades.MeteoritoCafe;
import grafico.space.entidades.MeteoritoGris;
import grafico.space.entidades.Nave;

import java.util.Random;

public class GestorJuego {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private final int alto = 800;
    private final int ancho = 600;
    private Scene menuScene;
    private ImageView nave;
    private Nave jugador;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angulo;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private String bgImage = "/deep_blue.png";

    private ImageView[] brownMeteor;
    private ImageView[] greyMeteor;
    Random randomPositionMeteor;

    private ImageView star;
    private String star_Path = "/star_gold.png";
    private InfoLabelPeq pointsLabel;
    private ImageView[] vidajugador;

    private int points;
    private  final int starRadius=12;
    private  final int shipRadius=17;
    private  final int meteorRadius=20;
    private XMLScoreManager scoresManager;

    public GestorJuego(Stage mainStage){
        initializeStage(mainStage);
        createKeyListeners();
        randomPositionMeteor = new Random();
        scoresManager = new XMLScoreManager();
        scoresManager.leerXML();
    }

    /**
     * Crea los listeners para controlar cuando se presionan o liberan las teclas A (izquierda) y D (derecha).
     */
    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A){
                    isLeftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.D) {
                    isRightKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.A ){
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.D) {
                    isRightKeyPressed = false;
                }
            }
        });
    }

    /**
     * Inicializa la escena y panel principal del juego, y guarda la escena del menú para poder volver.
     * @param mainStage la ventana principal de la aplicación
     */
    private void initializeStage(Stage mainStage) {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, ancho, alto);
        menuScene = mainStage.getScene();
        gameStage = mainStage;
        gameStage.setScene(gameScene);
    }

    /**
     * Inicia una nueva partida con la nave seleccionada por el jugador.
     * Crea todos los elementos gráficos y empieza el bucle principal.
     * @param nave nave seleccionada por el jugador
     */
    public void createNewGame(Nave nave){
        jugador = nave;
        createBackGround();
        createGameElements(nave);
        createShip(nave);
        createGameLoop();
        gameStage.setMaxWidth(ancho +16);
        gameStage.setMaxHeight(alto +39);
        gameStage.setResizable(false);
        gameStage.show();
    }

    /**
     * Crea los elementos del juego como meteoritos, estrellas, puntos y vidas.
     * @param ship nave seleccionada para obtener imagen de vida
     */
    private void createGameElements(Nave ship){
        star = new ImageView(star_Path);
        setElementPosition(star);
        gamePane.getChildren().add(star);
        pointsLabel = new InfoLabelPeq("POINTS : 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
        vidajugador = new ImageView[3];
        for (int i = 0; i< vidajugador.length ; i++){
            vidajugador[i] = new ImageView(ship.getImagenVida());
            vidajugador[i].setLayoutX(455+(i*50));
            vidajugador[i].setLayoutY(80);
            gamePane.getChildren().add(vidajugador[i]);
        }

        brownMeteor = new ImageView[3];
        for (int i=0 ; i< brownMeteor.length ; i++){
            MeteoritoCafe n = new MeteoritoCafe();
            brownMeteor[i] = new ImageView(n.getImagen());
            setElementPosition(brownMeteor[i]);
            gamePane.getChildren().add(brownMeteor[i]);
        }
        greyMeteor = new ImageView[3];
        for (int i=0 ; i< greyMeteor.length ; i++){
            MeteoritoGris n = new MeteoritoGris();
            greyMeteor[i] = new ImageView(n.getImagen());
            setElementPosition(brownMeteor[i]);
            gamePane.getChildren().add(greyMeteor[i]);
        }
    }

    /**
     * Mueve todos los elementos móviles del juego: estrella y meteoritos.
     * Actualiza la posición y rotación para meteoritos.
     */
    private void moveGameElements(){
        star.setLayoutY(star.getLayoutY()+5);

        for (int i=0 ; i< brownMeteor.length ; i++){
            brownMeteor[i].setLayoutY(brownMeteor[i].getLayoutY()+7);
            brownMeteor[i].setRotate(brownMeteor[i].getRotate()+4);
        }
        for (int i=0 ; i< greyMeteor.length ; i++){
            greyMeteor[i].setLayoutY(greyMeteor[i].getLayoutY()+7);
            greyMeteor[i].setRotate(greyMeteor[i].getRotate()+4);
        }
    }

    /**
     * Comprueba colisiones entre la nave y la estrella o meteoritos.
     * Actualiza la puntuación o vidas en consecuencia.
     */
    private void checkCollision(){
        if (starRadius + shipRadius > calcularDistancia(nave.getLayoutX()+49, star.getLayoutX()+15,nave.getLayoutY()+37,star.getLayoutY()+15)){
            setElementPosition(star);
            points+=5;
            String textToSet = "POINTS : ";
            if (points<10){
                textToSet += "0";
            }
            pointsLabel.setText(textToSet + points);
        };
        for (int i=0 ; i< brownMeteor.length ; i++){
            if (meteorRadius + shipRadius > calcularDistancia(nave.getLayoutX()+49,brownMeteor[i].getLayoutX()+20, nave.getLayoutY()+37,brownMeteor[i].getLayoutY()+20)){
                removeLife();
                setElementPosition(brownMeteor[i]);
            }
        }
        for (int i=0 ; i< greyMeteor.length ; i++){
            if (meteorRadius + shipRadius > calcularDistancia(nave.getLayoutX()+49,greyMeteor[i].getLayoutX()+20, nave.getLayoutY()+37,greyMeteor[i].getLayoutY()+20)){
                removeLife();
                setElementPosition(greyMeteor[i]);
            }
        }
    }


    /**
     * Elimina una vida visualmente y actualiza la vida del jugador.
     * Si las vidas llegan a cero, termina el juego y vuelve al menú.
     */
    private void removeLife(){
        gamePane.getChildren().remove(vidajugador[jugador.getVida()]);
        jugador.quitarVida();
        if (jugador.getVida()<0){
            gameTimer.stop();
            gameStage.setScene(menuScene);
            scoresManager.añadirScore(points);
            scoresManager.escribirXML();
        }
    }


    /**
     * Calcula la distancia euclidiana entre dos puntos (x1, y1) y (x2, y2).
     */
    private double calcularDistancia(double x1,double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    }

    /**
     * Comprueba si los elementos (estrella y meteoritos) han salido de la pantalla.
     * Si es así, los reposiciona arriba para que vuelvan a caer.
     */
    private void checkElemenet(){
        if (star.getLayoutY()>1000){
            setElementPosition(star);
        }

        for (int i=0 ; i< brownMeteor.length ; i++){
            if (brownMeteor[i].getLayoutY()>900){
                setElementPosition(brownMeteor[i]);
            }
        }
        for (int i=0 ; i< greyMeteor.length ; i++){
            if (greyMeteor[i].getLayoutY()>900){
                setElementPosition(greyMeteor[i]);
            }
        }
    }

    /**
     * Posiciona un elemento gráfico en una posición aleatoria arriba de la pantalla,
     * para que parezca que cae desde fuera de la vista.
     * @param image el ImageView que se quiere posicionar
     */
    private void setElementPosition(ImageView image){
        image.setLayoutX(randomPositionMeteor.nextInt(550)+25);
        image.setLayoutY(-(randomPositionMeteor.nextInt(3200)+600));
    }

    /**
     * Crea la nave seleccionada por el jugador y la añade a la pantalla en la posición inicial.
     * @param choosenShip nave seleccionada
     */
    private void createShip(Nave choosenShip){
        nave = new ImageView(choosenShip.getImagen());
        nave.setLayoutY(alto -90);
        nave.setLayoutX(ancho /2);
        gamePane.getChildren().add(nave);
    }

    /**
     * Crea el bucle principal del juego usando AnimationTimer.
     * En cada frame actualiza el fondo, elementos móviles, colisiones y la nave.
     */
    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBG();
                moveGameElements();
                checkElemenet();
                checkCollision();
                moveShip();
            }
        };
        gameTimer.start();
    }

    /**
     * Controla el movimiento de la nave en función de las teclas pulsadas.
     * Además rota la nave para simular giro visual hacia izquierda o derecha.
     */
    private void moveShip(){
        if (isLeftKeyPressed && !isRightKeyPressed){
            if (angulo > -30){
                angulo -= 5;
            }
            nave.setRotate(angulo);
            if (nave.getLayoutX() > 0) {
                nave.setLayoutX(nave.getLayoutX()-3);
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed){
            if (angulo < 30){
                angulo += 5;
            }
            nave.setRotate(angulo);
            if (nave.getLayoutX() < 502){
                nave.setLayoutX(nave.getLayoutX() + 3);
            }
        }
        if (isLeftKeyPressed && isRightKeyPressed){
            if (angulo < 0){
                angulo += 5;
            } else if (angulo > 0) {
                angulo -= 5;
            }
            nave.setRotate(angulo);
        }
        if (!isRightKeyPressed && !isLeftKeyPressed){
            if (angulo < 0){
                angulo += 5;
            } else if (angulo > 0) {
                angulo -= 5;
            }
            nave.setRotate(angulo);
        }

    }

    /**
     * Crea el fondo del juego con dos paneles para crear efecto de scroll infinito.
     */
    private void createBackGround(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for (int i=0 ; i<12 ; i++){
            ImageView bgImage1 = new ImageView(bgImage);
            ImageView bgImage2 = new ImageView(bgImage);
            GridPane.setConstraints(bgImage1, i%3,i/3);
            GridPane.setConstraints(bgImage2, i%3,i/3);
            gridPane1.getChildren().add(bgImage1);
            gridPane2.getChildren().add(bgImage2);
        }
        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane1,gridPane2);
    }

    /**
     * Mueve ambos paneles del fondo hacia abajo para simular el desplazamiento.
     * Cuando un panel sale de la pantalla, lo reposiciona arriba para repetir el efecto.
     */
    private void moveBG(){
        gridPane1.setLayoutY(gridPane1.getLayoutY()+0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY()+0.5);

        if (gridPane1.getLayoutY() >= 1024){
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1024){
            gridPane2.setLayoutY(-1024);
        }
    }
}
