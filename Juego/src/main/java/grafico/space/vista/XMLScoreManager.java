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

    public Estadistica[] getScores() {
        return scores;
    }

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

    public void obtenerScores(){
        for (Estadistica s : scores){
            System.out.println(s.getScore());
        }
    }

    public int tamañoScores(){
        return scores.length;
    }
}
