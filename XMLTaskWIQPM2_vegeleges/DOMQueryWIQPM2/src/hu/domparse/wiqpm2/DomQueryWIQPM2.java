package hu.domparse.wiqpm2;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryWIQPM2 {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		try {
			//xml file megnyitasa
            File xmlf = new File("XMLWIQPM2.xml");

            //dokumentum létrehozása és normalizálása
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xmlf);
            document.getDocumentElement().normalize();

            //metodusok 
            lekerdezes1(document);
            lekerdezes2(document);
            lekerdezes3(document);
            lekerdezes4(document);
            lekerdezes5(document);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}    
	}

    public static void lekerdezes1(Document document) {
		try {
			System.out.println("\n1. A LEC régiót képviselő csapatok:\n");
            NodeList nList = document.getElementsByTagName("csapat");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    Node node1 = elem.getElementsByTagName("regio").item(0);
		    		String text1 = node1.getTextContent();
                    if("LEC".equals(text1)){
                        String csapat_nev = elem.getAttribute("cs_nev");
                        System.out.println("Csapat neve: " + csapat_nev);
                        System.out.println("");
                    }
                }
            }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        System.out.println("");
        System.out.println("");
	}

    public static void lekerdezes2(Document document) {
		try {
			System.out.println("\n2. Azon játékosok nevei és csapata, akinek az életkora 23 vagy kevesebb:\n");
            NodeList nList = document.getElementsByTagName("jatekos");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    Node node1 = elem.getElementsByTagName("eletkor").item(0);
		    		String text1 = node1.getTextContent();
                    if(Integer.valueOf(text1) <= 23){
                        Node node2 = elem.getElementsByTagName("j_nev").item(0);
		    		    String text2 = node2.getTextContent();
                        String csapat_nev = elem.getAttribute("csapat_nev");
                        System.out.println("Játékos neve: " + text2);
                        System.out.println("Csapat neve: " + csapat_nev);
                        System.out.println("");
                    }
                }
            }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        System.out.println("");
        System.out.println("");
	}

    public static void lekerdezes3(Document document) {
		try {
			System.out.println("\n3. Azon csapatok neve és rangja, amelyek megnyertek egy 100.000-nál nagyobb díjazású versenyt, illetve az esemény neve:\n");
            NodeList nList = document.getElementsByTagName("esemeny");
            NodeList nList2 = document.getElementsByTagName("csapat");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    Node nyeremeny = elem.getElementsByTagName("nyeremeny").item(0);
		    		String text1 = nyeremeny.getTextContent();
                    System.out.println(text1);
                    if(Integer.valueOf(text1) >= 100000){
                        String csapat_nev = elem.getAttribute("csapat_nev");
                        Node esemeny_neve = elem.getElementsByTagName("es_nev").item(0);
		    		    String text2 = esemeny_neve.getTextContent();
                        for(int j = 0; j < nList2.getLength(); j++){
                            Node nNode2 = nList2.item(j);
                            if (nNode2.getNodeType()==Node.ELEMENT_NODE) {
                                Element elem2 = (Element) nNode2;
                                if(csapat_nev.equals(elem2.getAttribute("cs_nev"))){
                                    Node rang = elem2.getElementsByTagName("cs_rang").item(0);
                                    String text3 = rang.getTextContent();
                                    System.out.println("Esemény: " + text2);
                                    System.out.println("Csapat neve: " + csapat_nev);
                                    System.out.println("Rang: " + text3);
                                    System.out.println("");
                                }
                            }
                        }
                    }
                }
            }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        System.out.println("");
        System.out.println("");
	}

    public static void lekerdezes4(Document document) {
		try {
			System.out.println("\n4. T1 csapat által nyert események:\n");
            NodeList nList = document.getElementsByTagName("esemeny");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    String csapat_nev = elem.getAttribute("csapat_nev");
                    if("T1".equals(csapat_nev)){
                        Node esemeny_neve = elem.getElementsByTagName("es_nev").item(0);
		    		    String text1 = esemeny_neve.getTextContent();
                        System.out.println("Esemény neve: " + text1);
                        System.out.println("");
                    }
                }
            }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        System.out.println("");
        System.out.println("");
	}

    public static void lekerdezes5(Document document) {
		try {
			System.out.println("\n5. A G2 csapat játékosainak adatai:\n");
            NodeList nList = document.getElementsByTagName("jatekos");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;
                    String csapat_nev = elem.getAttribute("csapat_nev");
                    if("G2".equals(csapat_nev)){
                        String azonosito = elem.getAttribute("j_azonosito");
                        System.out.println("Azonosító: " + azonosito);
                        System.out.println("Csapat neve: " + csapat_nev);
                        NodeList nList2 = nNode.getChildNodes();
                        for(int j = 1; j < nList2.getLength(); j++){
                            Node nNode2 = nList2.item(j);
                            if (nNode2.getNodeType()==Node.ELEMENT_NODE) {
                                Element elem2 = (Element) nNode2;
                                String text2 = elem2.getTextContent();
                                System.out.print(elem2.getNodeName() + ": " + text2 + ", ");
                            }
                        }
                        System.out.println("");
                    }
                }
            }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
        System.out.println("");
        System.out.println("");
	}
}