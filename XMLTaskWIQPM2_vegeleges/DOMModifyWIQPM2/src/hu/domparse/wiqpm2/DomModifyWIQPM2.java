package hu.domparse.wiqpm2;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomModifyWIQPM2 {
    public static void main(String[] args) {
		
		try {
			
			//xml file megnyitása
			File xmlf = new File("XMLWIQPM2.xml");
	
			//dokumentum létrehozasa és normalizálása
	        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = documentBuilder.parse(xmlf);
	        document.getDocumentElement().normalize();

            //metodusok 
            query1(document);
            query2(document);
            query3(document);
            query4(document);
            query5(document);

            //a módosított adatok kiírasa a konzolra
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(document);
	        System.out.println("Módosítás után:\n");       
	        StreamResult consoleResult = new StreamResult(System.out);
	        transformer.transform(source, consoleResult);
	       
		} catch (Exception e ) {
            e.printStackTrace();
        }
	}

    public static void query1(Document document){
        // 1. Caps nevű játékos rangjának a módosítása
        NodeList nList = document.getElementsByTagName("jatekos");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                Node node1 = elem.getElementsByTagName("j_nev").item(0);
                String text1 = node1.getTextContent();
                if("Caps".equals(text1)){
                    Node node2 = elem.getElementsByTagName("j_rang").item(0);
                    node2.setTextContent("5");
                }
            }
        }
    }

    public static void query2(Document document){
        // 2. 3. id-jű játékos csapatának változtatása
        NodeList nList = document.getElementsByTagName("jatekos");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                NamedNodeMap attr = nNode.getAttributes();
				Node nodeAttrID = attr.getNamedItem("j_azonosito");
                Node nodeAttrCsapat = attr.getNamedItem("csapat_nev");
                if (nodeAttrID.getTextContent().equals("3")) {
					nodeAttrCsapat.setTextContent("Mad Lions");
				}
            }
        }
    }

    public static void query3(Document document){
        // 3. Szponzorált csapat modosítása
        NodeList nList = document.getElementsByTagName("szp_csapat");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                NamedNodeMap attribute = nNode.getAttributes();
                Node nodeAttrCsapat = attribute.getNamedItem("csapat_nev");
                if (nodeAttrCsapat.getTextContent().equals("G2")) {
					nodeAttrCsapat.setTextContent("T1");
                    Element elem = (Element) nNode;
                    Node node1 = elem.getElementsByTagName("miota_szp").item(0);
                    node1.setTextContent("2023");
				}
            }
        }
    }

    public static void query4(Document document){
        // 4. Hana Bank nevű szponzor nevének változtatása
        NodeList nList = document.getElementsByTagName("szponzor");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                String sz_nev = elem.getAttribute("sz_nev");
                if (sz_nev.equals("Hana Bank")) {
                    elem.setAttribute("sz_nev", "Anah Bank");
				}
            }
        }
    }

    public static void query5(Document document){
        // 5. LEC régiót képviselő csapatok régiójának változtatása LCL-re
        NodeList nList = document.getElementsByTagName("csapat");
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;
                Node regio = elem.getElementsByTagName("regio").item(0);
                String text1 = regio.getTextContent();
                if (text1.equals("LEC")) {
					regio.setTextContent("LCL");
				}
            }
        }
    }

}