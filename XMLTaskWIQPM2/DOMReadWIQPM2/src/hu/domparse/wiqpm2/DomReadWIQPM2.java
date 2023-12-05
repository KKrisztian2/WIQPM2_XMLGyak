package hu.domparse.wiqpm2;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomReadWIQPM2 {
	public static void main(String[] args) {
	    try {
                File xmlf= new File("XMLWIQPM2.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document document = dBuilder.parse(xmlf);
                document.getDocumentElement().normalize();

                //Fa struktúra kiírása
                printNode(document.getDocumentElement(), 0); 

	        } 
	    	catch (Exception e) 
	    	{
	            e.printStackTrace();
	        }
	    }

	private static void printNode(Node node, int depth) 
	{
	    String indent = " ".repeat(depth * 5);
	    if (node.getNodeType() == Node.ELEMENT_NODE) 
	    {
	        System.out.println(indent + "<" + node.getNodeName() + ">");
	        NodeList nodeL = node.getChildNodes();
	        for (int i = 0; i < nodeL.getLength(); i++) 
	        {
	            printNode(nodeL.item(i), depth + 1);
	        }
	        System.out.println(indent + "</" + node.getNodeName() + ">");
	    } 
	    else if (node.getNodeType() == Node.TEXT_NODE && !node.getTextContent().trim().isEmpty()) 
	    {
	        System.out.println(indent + node.getTextContent().trim());
	    }
	}
}




/*package hu.domparse.wiqpm2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadWIQPM2 {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		
		
		//xml file megnyitasa
		File xmlf = new File("XMLWIQPM2.xml");
		
		//dokumentum létrehozása és normalizálása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document document = dBuilder.parse(xmlf);		
		document.getDocumentElement().normalize();

		System.out.println("Root element: " + document.getDocumentElement().getNodeName());

		//metodusok 
		getSzponzorok(document);
		getSzp_csapatok(document);
		getJatekosok(document);
		getCsapatok(document);
		getEdzok(document);
		getEsemenyek(document);

		//adatok kiirasa txt fileba
        File modFile = new File("XMLWIQPM2.txt");
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);   
        StreamResult resultModFile = new StreamResult(modFile);        
        transformer.transform(source, resultModFile );
		
	}
	public static void getSzponzorok(Document doc) {
		NodeList nList = doc.getElementsByTagName("szponzor");
		System.out.println("\nSzponzorok:\n");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				String sz_nev = elem.getAttribute("sz_nev");
				Node node1 = elem.getElementsByTagName("tipus").item(0);
				String text1 = node1.getTextContent();
				Node node2 = elem.getElementsByTagName("sz_orszag").item(0);
				String text2 = node2.getTextContent();

                System.out.println("Szponzor neve: " + sz_nev);
				System.out.println("Típus:" + text1);
				System.out.println("Sz_orszag: " + text2);

				NodeList nList1 = elem.getElementsByTagName("tulajdonos");
                for(int j = 0; j < nList1.getLength(); j++){
                    Node nNode1 = nList1.item(j);
                    String text3 = nNode1.getTextContent();
                    System.out.println("Tulajdonos: " + text3);
                }
			}
		}
        System.out.println("");
	}
		
	
	public static void getSzp_csapatok(Document doc) {
		NodeList nList = doc.getElementsByTagName("szp_csapat");
		System.out.println("\nSzponzorált csapatok:\n");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				String szp_csapat_azonosito = elem.getAttribute("szp_csapat_azonosito");
                String szp_nev = elem.getAttribute("szp_nev");
                String csapat_nev = elem.getAttribute("csapat_nev");
				Node node1 = elem.getElementsByTagName("miota_szp").item(0);
				String text1 = node1.getTextContent();
				
				System.out.println("Azonosito: " + szp_csapat_azonosito);
                System.out.println("Szponzor: " + szp_nev);
                System.out.println("Szponzorált csapat: " + csapat_nev);
				System.out.println("Mióta aszponzor:" + text1);
			}
		}
        System.out.println("");
	}
	
	public static void getJatekosok(Document doc) {
        NodeList nList = doc.getElementsByTagName("jatekos");
		System.out.println("\nJátékosok:\n");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				String j_azonosito = elem.getAttribute("j_azonosito");
                String csapat_nev = elem.getAttribute("csapat_nev");
				Node node1 = elem.getElementsByTagName("j_nev").item(0);
				String text1 = node1.getTextContent();
                Node node2 = elem.getElementsByTagName("j_nemzetiseg").item(0);
				String text2 = node2.getTextContent();
                Node node3 = elem.getElementsByTagName("aktiv").item(0);
				String text3 = node3.getTextContent();
                Node node4 = elem.getElementsByTagName("j_rang").item(0);
				String text4 = node4.getTextContent();
                Node node5 = elem.getElementsByTagName("pozicio").item(0);
				String text5 = node5.getTextContent();
                Node node6 = elem.getElementsByTagName("karrier_kezdete").item(0);
				String text6 = node6.getTextContent();
                Node node7 = elem.getElementsByTagName("eletkor").item(0);
				String text7 = node7.getTextContent();
				
				System.out.println("Azonosito: " + j_azonosito);
                System.out.println("Csapat: " + csapat_nev);
                System.out.println("Név: " + text1);
				System.out.println("Nemzetiség:" + text2);
                System.out.println("Aktív: " + text3);
                System.out.println("Rang: " + text4);
                System.out.println("Pozíció: " + text5);
				System.out.println("Karrier kezdete:" + text6);
                System.out.println("Életkor:" + text7);
			}
		}
        System.out.println("");
	}

	public static void getCsapatok(Document doc) {
        NodeList nList = doc.getElementsByTagName("csapat");
		System.out.println("\nCsapatok:\n");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				String csapat_nev = elem.getAttribute("cs_nev");
				Node node1 = elem.getElementsByTagName("cs_nemzetiseg").item(0);
				String text1 = node1.getTextContent();
                Node node2 = elem.getElementsByTagName("cs_rang").item(0);
				String text2 = node2.getTextContent();
                Node node3 = elem.getElementsByTagName("regio").item(0);
				String text3 = node3.getTextContent();
				
				System.out.println("Csapat név: " + csapat_nev);
				System.out.println("Nemzetiség:" + text1);
                System.out.println("Rang:" + text2);
                System.out.println("Régió:" + text3);
			}
		}
        System.out.println("");
	}

	public static void getEdzok(Document doc) {
		NodeList nList = doc.getElementsByTagName("edzo");
		System.out.println("\nEdzok:\n");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				String azonosito = elem.getAttribute("e_azonosito");
                String csapat_nev = elem.getAttribute("csapat_nev");
				Node node1 = elem.getElementsByTagName("e_nev").item(0);
				String text1 = node1.getTextContent();
                Node node2 = elem.getElementsByTagName("miota").item(0);
				String text2 = node2.getTextContent();
                Node node3 = elem.getElementsByTagName("korabbi_csapat").item(0);
				String text3 = node3.getTextContent();
				
				System.out.println("Azonosító: " + azonosito);
                System.out.println("Csapat név: " + csapat_nev);
				System.out.println("Név:" + text1);
                System.out.println("Mióta edző:" + text2);
                System.out.println("Korábbi csapat:" + text3);
			}
		}
        System.out.println("");
	}

	public static void getEsemenyek(Document doc) {
		NodeList nList = doc.getElementsByTagName("esemeny");
		System.out.println("\nEsemények:\n");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			if (nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				String azonosito = elem.getAttribute("es_azonosito");
                String csapat_nev = elem.getAttribute("csapat_nev");
				Node node1 = elem.getElementsByTagName("es_nev").item(0);
				String text1 = node1.getTextContent();
                Node node2 = elem.getElementsByTagName("ev").item(0);
				String text2 = node2.getTextContent();
                Node node3 = elem.getElementsByTagName("nyeremeny").item(0);
				String text3 = node3.getTextContent();
                Node node4 = elem.getElementsByTagName("es_orszag").item(0);
				String text4 = node4.getTextContent();
                Node node5 = elem.getElementsByTagName("varos").item(0);
				String text5 = node5.getTextContent();
                Node node6 = elem.getElementsByTagName("hely").item(0);
				String text6 = node6.getTextContent();
				
				System.out.println("Azonosító: " + azonosito);
                System.out.println("Csapat név: " + csapat_nev);
				System.out.println("Esemény neve:" + text1);
                System.out.println("Év:" + text2);
                System.out.println("Nyeremény:" + text3);
                System.out.println("Ország:" + text4);
                System.out.println("Város:" + text5);
                System.out.println("Helyszín:" + text6);
			}
		}
        System.out.println("");
    }
}*/