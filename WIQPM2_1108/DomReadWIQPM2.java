package domWIQPM21108;

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

public class DomRead{

	public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
		
		File f = new File("WIQPM2_kurzusfelvetel.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document d = dBuilder.parse(f);
		
		d.getDocumentElement().normalize();

		NodeList hallgato = d.getElementsByTagName("hallgato");
		Node hnode = hallgato.item(0);
		System.out.println("\nElement: " + nnode.getNodeName());
		if (nnode.getNodeType() == Node.ELEMENT_NODE)
		{
			Element elem = (Element) nnode;
			String id = elem.getAttribute("id");

			Node node1 = elem.getElementsByTagName("hnev").item(0);
			String hnev = node1.getTextContent();

			Node node2 = elem.getElementsByTagName("szulev").item(0);
			String szulev = node1.getTextContent();

			Node node3 = elem.getElementsByTagName("szak").item(0);
			String szak = node1.getTextContent();
			String evfolyam = node3.getAttribute("evf");
		}

		System.out.println("hallgato neve: " +hnev);
		System.out.println("szuletesi ev: " + szulev);
		System.out.println("szak: " +szak);
		System.out.println("evfolyam: " +evfolyam);
		
		
		NodeList lList = d.getElementsByTagName("kurzus");
		
		for (int i = 0; i < nlist.getLength(); i++) {
			
			Node nnode = nlist.item(i);
			
			System.out.println("\nElement: " + nnode.getNodeName());
			
			if (nnode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element elem = (Element) nnode;
				String id = elem.getAttribute("id");
                String nyelv = elem.getAttribute("nyelv");
                String jovahagyas = elem.getAttribute("jovahagyas");
				
				Node node1 = elem.getElementsByTagName("kurzusnev").item(0);
				String kurzusnev = node1.getTextContent();
				
				Node node2 = elem.getElementsByTagName("kredit").item(0);
				String kredit = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("hely").item(0);
				String hely = node3.getTextContent();
				
				Node node4 = elem.getElementsByTagName("idopont").item(0);
				String idopont = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("oktato").item(0);
				String oktato = node4.getTextContent();

                Node node6 = elem.getElementsByTagName("oraado").item(0);
				String oraado = node4.getTextContent();
				
				System.out.println("id: " + id);
                System.out.println("nyelv: " + nyelv);
                System.out.println("jovahagyas: " + jovahagyas);

				System.out.println("kurzusnev: " +kurzusnev);
				System.out.println("kredit: " + kredit);
				System.out.println("hely: " +hely);
				System.out.println("idopont: " +idopont);
                System.out.println("oktato: " +oktato);
                System.out.println("oraado: " +oraado);
			}
		}
	}

}