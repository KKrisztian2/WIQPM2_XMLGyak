package domWIQPM21115;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DomModifyWIQPM2{
	 public static void main(String[] args) {
	        try {
	            File xmlFile = new File("WIQPM2_kurzusfelvetel.xml");
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(xmlFile);
	            doc.getDocumentElement().normalize();

	            modifyOraado(doc);
	            modifyNyelv(doc);
	            
	            saveXmlToFile(doc, "WIQPM2_kurzusfelvetel1.xml");
	            printXmlToConsole(doc);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	 private static void modifyOraado(Document doc) {
		 	String ujOraado1 = "Nagy Péter";
		 	String ujOraado2 = "Okuwawe Vendaru";
	        NodeList kurzusokNodeList = doc.getElementsByTagName("kurzus");
	        int db = 0;
	        for (int i = 0; i < kurzusokNodeList.getLength(); i++) {
	            Element kurzusElement = (Element) kurzusokNodeList.item(i);
	            NodeList oraadoNodeList = kurzusElement.getElementsByTagName("oraado");
	            if (oraadoNodeList.getLength() == 0 && db < 2) {
	                Element oraadoElement = doc.createElement("oraado");
	                oraadoElement.appendChild(doc.createTextNode(db == 0 ? ujOraado1 : ujOraado2));
	                kurzusElement.appendChild(oraadoElement);
	                db++;
	            }
	        }
	    }
	 
	 private static void modifyNyelv(Document doc) {
	        NodeList kurzusokNodeList = doc.getElementsByTagName("kurzus");

	        for (int i = 0; i < kurzusokNodeList.getLength(); i++) {
	            Element kurzusElement = (Element) kurzusokNodeList.item(i);
	            String nyelv = kurzusElement.getAttribute("nyelv");

	            if ("angol".equals(nyelv)) {
	                kurzusElement.setAttribute("nyelv", "német");
	            }
	        }
	    }

	    private static void printXmlToConsole(Document doc) {
	        try {
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult consoleResult = new StreamResult(System.out);
	            transformer.transform(source, consoleResult);
	            System.out.println("\n\n");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static void saveXmlToFile(Document doc, String fileName) {
	        try {
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new File(fileName));
	            transformer.transform(source, result);
	            System.out.println("XML módosítva: " + fileName);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}