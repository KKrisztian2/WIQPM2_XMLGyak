package hu.domparse.wiqpm2;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomWriteWIQPM2 {
	public static void main(String[] args) {
	    try {
                File xmlf= new File("XMLWIQPM2.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document document = dBuilder.parse(xmlf);
                document.getDocumentElement().normalize();

                //Fa struktúra kiírása
                printNode(document.getDocumentElement(), 0); 
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Bekezdések hozzáadása
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5"); // Indentálási mélység

                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File("XMLWIQPM21.xml"));
                transformer.transform(source, result);
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