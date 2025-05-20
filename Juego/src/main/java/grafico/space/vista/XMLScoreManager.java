package grafico.space.vista;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLScoreManager {
    private final int maxScores = 5;
    private Estadistica[] scores = new Estadistica[maxScores];
    private final String path = "score.xml";

    /**
     * Lee el archivo XML y carga las puntuaciones en el arreglo interno.
     * Si el archivo no existe o hay error, imprime el stack trace.
     */
    public void leerXML(){
        try {
            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();
            NodeList listaScores = root.getElementsByTagName("score");

            for (int i=0 ; i<listaScores.getLength() && (i < maxScores) ; i++){
                Element scoreElement = (Element) listaScores.item(i);
                String contenido = scoreElement.getTextContent().trim();
                int valor = contenido.isEmpty() ? 0 : Integer.parseInt(contenido);
                scores[i] = new Estadistica(valor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe en el archivo XML las puntuaciones actuales almacenadas en el arreglo.
     * Si ocurre un error, imprime el stack trace.
     */
    public  void escribirXML(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("scores");
            doc.appendChild(root);

            for (int i=0 ; i < scores.length ; i++){
                Element score = doc.createElement("score");
                score.setTextContent(String.valueOf(scores[i].getScore()).trim());
                root.appendChild(score);
            }

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT,"yes");
            t.transform(new DOMSource(doc), new StreamResult(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve el arreglo con las puntuaciones actuales.
     * @return arreglo de objetos Estadistica con las puntuaciones
     */
    public Estadistica[] getScores() {
        return scores;
    }

    /**
     * Añade un nuevo score al arreglo de puntuaciones si es mayor que alguno de los existentes.
     * Mantiene el arreglo ordenado de mayor a menor y descarta la puntuación más baja si es necesario.
     * @param score puntuación nueva a añadir
     * @return true si la puntuación fue añadida, false si no fue suficientemente alta
     */
    public boolean añadirScore(int score){
        for (Estadistica s : scores){
            if (s.getScore() < score) {
                int num = s.getScore();
                s.setScore(score);
                añadirScore(num);
                return true;
            }
        }
        return false;
    }

    /**
     * Imprime por consola todas las puntuaciones actuales almacenadas.
     */
    public void obtenerScores(){
        for (Estadistica s : scores){
            System.out.println(s.getScore());
        }
    }

    /**
     * Devuelve la cantidad máxima de puntuaciones que puede almacenar.
     * @return número máximo de puntuaciones
     */
    public int tamañoScores(){
        return scores.length;
    }
}
