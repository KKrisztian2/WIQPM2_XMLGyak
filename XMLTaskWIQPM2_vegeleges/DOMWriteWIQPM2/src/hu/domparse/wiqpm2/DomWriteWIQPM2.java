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
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DomWriteWIQPM2 {
	public static void main(String[] args) {
	    try {
                /*File xmlf= new File("XMLWIQPM2.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document document = dBuilder.parse(xmlf);
                document.getDocumentElement().normalize();*/
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.newDocument();

				fill(document);

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
		Integer s = 0;
		if (node.getNodeType() == Node.ELEMENT_NODE) 
		{
			Element element = (Element) node;
			NamedNodeMap attributes = element.getAttributes();
			if(attributes.getLength() == 0){
				if(node.getChildNodes().getLength() > 1){
					System.out.println(indent + "<" + node.getNodeName() + ">");
					s = 1;
				}
				else{
					System.out.print(indent + "<" + node.getNodeName() + ">");
				}
			}
			else{
				if(node.getChildNodes().getLength() > 1){
					s = 1;
				}
				System.out.print(indent + "<" + node.getNodeName() + " ");
				for (int i = 0; i < attributes.getLength(); i++) {
					Node attribute = attributes.item(i);
					System.out.print(attribute + " ");
				}
				if(node.getChildNodes().getLength() > 1){
					System.out.println(">");
				}
				else{
					System.out.print(">");
				}
			}
			NodeList nodeL = node.getChildNodes();
			for (int i = 0; i < nodeL.getLength(); i++) 
			{
				printNode(nodeL.item(i), depth + 1);
			}
			if(s == 1){
				System.out.print(indent + "</" + node.getNodeName() + ">");
				System.out.println();
			}
			else{
				System.out.print(" </" + node.getNodeName() + ">");
				System.out.println();
			}
		} 
		else if (node.getNodeType() == Node.TEXT_NODE && !node.getTextContent().trim().isEmpty()) 
		{
			System.out.print(" " +node.getTextContent().trim());
		}
	}

	private static void fill(Document doc) {
    	
        Element rootElement = doc.createElement("LoL_WIQPM2");
        rootElement.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttribute("xs:noNamespaceSchemaLocation", "XMLSchemaWIQPM2.xsd");
        doc.appendChild(rootElement);

		Element szponzorok = doc.createElement("szponzorok");
        addSzponzorok(doc, szponzorok, "Monster Energy", "Energiaital", "USA", "Rodney Sacks", null, null);
        addSzponzorok(doc, szponzorok, "SteelSeries", "Gaming", "Norvégia", "Ehtisham Rabbani", "Tino Soelberg", "Oluf Riddersholm");
        addSzponzorok(doc, szponzorok, "Twitch", "Streamingszolgáltatás", "USA", "Twitch Interactive", null, null);
		rootElement.appendChild(szponzorok);

		Element szp_csapatok = doc.createElement("szp_csapatok");
        addSzp_csapatok(doc, szp_csapatok, "1", "Monster Energy", "Fnatic", "2017");
        addSzp_csapatok(doc, szp_csapatok, "2", "SteelSeries", "Team Liquid", "2018");
		addSzp_csapatok(doc, szp_csapatok, "3", "Twitch", "PSG Talon", "2020");
		rootElement.appendChild(szp_csapatok);

		Element jatekosok = doc.createElement("jatekosok");
        addJatekosok(doc, jatekosok, "1", "Fnatic", "Humanoid", "cseh", "Igen", "62", "Mid", "2016", "23");
		addJatekosok(doc, jatekosok, "2", "Fnatic", "Razork", "spanyol", "Igen", "167", "Jungle", "2016", "23");
		addJatekosok(doc, jatekosok, "3", "Team Liquid", "CoreJJ", "dél koreai", "Igen", "69", "Support", "2013", "29");
		rootElement.appendChild(jatekosok);

		Element csapatok = doc.createElement("csapatok");
        addCsapatok(doc, csapatok, "Fnatic", "Anglia", "14", "EMEA");
        addCsapatok(doc, csapatok, "Team Liquid", "Hollandia", "15", "NA");
		addCsapatok(doc, csapatok, "PSG Talon", "Kína", "22", "PCS");
		rootElement.appendChild(csapatok);

		Element edzok = doc.createElement("edzok");
        addEdzok(doc, edzok, "1", "Fnatic", "Nightshare", "2023", "Immortals");
        addEdzok(doc, edzok, "2", "Team Liquid", "Spawn", "2023", "TL Challengers");
		addEdzok(doc, edzok, "3", "PSG Talon", "CorGi", "2021", "EDward Gaming");
		rootElement.appendChild(edzok);

		Element esemenyek = doc.createElement("esemenyek");
        addEsemenyek(doc, esemenyek, "1", "Fnatic", "Rift Rivals 2018 NA-EU", "2018", "20000", "US", "Los Angeles", "Riot North American Studios");
        addEsemenyek(doc, esemenyek, "2", "Fnatic", "EU LCS 2015 Summer Playoffs", "2015", "50000", "Svédország", "Stockholm", "Hovet Arena");
        addEsemenyek(doc, esemenyek, "3", "Team Liquid", " LCS 2022 Lock In", "2022", "150000", "US", "Los Angeles", "LCS Studio");
		rootElement.appendChild(esemenyek);
    }

	private static void addSzponzorok(Document doc, Element parentElement, String sz_nev, String tipus, String sz_orszag, String tulajdonos1, String tulajdonos2, String tulajdonos3) {
		Element szponzorE = doc.createElement("szponzor");
        szponzorE.setAttribute("sz_nev", sz_nev);

		Element tipusE = createElement(doc, "tipus", tipus);
		szponzorE.appendChild(tipusE);
		Element sz_orszagE = createElement(doc, "sz_orszag", sz_orszag);
		szponzorE.appendChild(sz_orszagE);

        if (tulajdonos1 != null && !tulajdonos1.isEmpty()) {
            Element tulajdonos1E = createElement(doc, "tulajdonos", tulajdonos1);
            szponzorE.appendChild(tulajdonos1E);
        }
        if (tulajdonos2 != null && !tulajdonos2.isEmpty()) {
            Element tulajdonos2E = createElement(doc, "tulajdonos", tulajdonos2);
            szponzorE.appendChild(tulajdonos2E);
        }
		if (tulajdonos3 != null && !tulajdonos3.isEmpty()) {
            Element tulajdonos3E = createElement(doc, "tulajdonos", tulajdonos3);
            szponzorE.appendChild(tulajdonos3E);
        }
        parentElement.appendChild(szponzorE);
    }

	private static void addSzp_csapatok(Document doc, Element parentElement, String szp_csapat_azonosito, String szp_nev, String csapat_nev, String miota_szp) {
		Element szponzoralt_csE = doc.createElement("szp_csapat");
        szponzoralt_csE.setAttribute("szp_csapat_azonosito", szp_csapat_azonosito);
		szponzoralt_csE.setAttribute("szp_nev", szp_nev);
		szponzoralt_csE.setAttribute("csapat_nev", csapat_nev);

		Element miota_szpE = createElement(doc, "miota_szp", miota_szp);
		szponzoralt_csE.appendChild(miota_szpE);

        parentElement.appendChild(szponzoralt_csE);
    }

	private static void addJatekosok(Document doc, Element parentElement, String j_azonosito, String csapat_nev, String j_nev, String j_nemzetiseg, String aktiv, String j_rang, String pozicio, String karrier_kezdete, String eletkor) {
		Element jatekosE = doc.createElement("jatekos");
        jatekosE.setAttribute("j_azonosito", j_azonosito);
		jatekosE.setAttribute("csapat_nev", csapat_nev);

		Element j_nevE = createElement(doc, "j_nev", j_nev);
		jatekosE.appendChild(j_nevE);
		Element j_nemzetisegE = createElement(doc, "j_nemzetiseg", j_nemzetiseg);
		jatekosE.appendChild(j_nemzetisegE);
		Element aktivE = createElement(doc, "aktiv", aktiv);
		jatekosE.appendChild(aktivE);
		Element j_rangE = createElement(doc, "j_rang", j_rang);
		jatekosE.appendChild(j_rangE);
		Element pozicioE = createElement(doc, "pozicio", pozicio);
		jatekosE.appendChild(pozicioE);
		Element karrier_kezdeteE = createElement(doc, "karrier_kezdete", karrier_kezdete);
		jatekosE.appendChild(karrier_kezdeteE);
		Element eletkorE = createElement(doc, "eletkor", eletkor);
		jatekosE.appendChild(eletkorE);

        parentElement.appendChild(jatekosE);
    }

	private static void addCsapatok(Document doc, Element parentElement, String cs_nev, String cs_nemzetiseg, String cs_rang, String regio) {
		Element csapatE = doc.createElement("csapat");
        csapatE.setAttribute("cs_nev", cs_nev);

		Element cs_nemzetisegE = createElement(doc, "cs_nemzetiseg", cs_nemzetiseg);
		csapatE.appendChild(cs_nemzetisegE);
		Element cs_rangE = createElement(doc, "cs_rang", cs_rang);
		csapatE.appendChild(cs_rangE);
		Element regioE = createElement(doc, "regio", regio);
		csapatE.appendChild(regioE);

        parentElement.appendChild(csapatE);
    }

	private static void addEdzok(Document doc, Element parentElement, String e_azonosito, String csapat_nev, String e_nev, String miota, String korabbi_csapat) {
		Element edzoE = doc.createElement("edzo");
        edzoE.setAttribute("e_azonosito", e_azonosito);
		edzoE.setAttribute("csapat_nev", csapat_nev);

		Element e_nevE = createElement(doc, "e_nev", e_nev);
		edzoE.appendChild(e_nevE);
		Element miotaE = createElement(doc, "miota", miota);
		edzoE.appendChild(miotaE);
		Element korabbi_csapatE = createElement(doc, "korabbi_csapat", korabbi_csapat);
		edzoE.appendChild(korabbi_csapatE);

        parentElement.appendChild(edzoE);
    }

	private static void addEsemenyek(Document doc, Element parentElement, String es_azonosito, String csapat_nev, String es_nev, String ev, String nyeremeny, String es_orszag, String varos, String hely) {
		Element esemenyE = doc.createElement("esemeny");
        esemenyE.setAttribute("es_azonosito", es_azonosito);
		esemenyE.setAttribute("csapat_nev", csapat_nev);

		Element es_nevE = createElement(doc, "es_nev", es_nev);
		esemenyE.appendChild(es_nevE);
		Element evE = createElement(doc, "ev", ev);
		esemenyE.appendChild(evE);
		Element nyeremenyE = createElement(doc, "nyeremeny", nyeremeny);
		esemenyE.appendChild(nyeremenyE);

		Element helyszinE = doc.createElement("helyszin");
		Element es_orszagE = createElement(doc, "es_orszag", es_orszag);
		helyszinE.appendChild(es_orszagE);
		Element varosE = createElement(doc, "varos", varos);
		helyszinE.appendChild(varosE);
		Element helyE = createElement(doc, "hely", hely);
		helyszinE.appendChild(helyE);
		esemenyE.appendChild(helyszinE);

        parentElement.appendChild(esemenyE);
    }

	private static Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

}