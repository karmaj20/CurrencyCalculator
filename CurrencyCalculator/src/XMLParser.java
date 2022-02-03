import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class XMLParser {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    private final String FILENAME = "eurofxref-daily.xml";

    public Map<String, Double> getAllElements() throws ParserConfigurationException, IOException, SAXException {
        NodeList laptopList = getDocument();
        Map<String, Double> mapCurrencyRate = new HashMap<>();
        for (int i = 0; i < laptopList.getLength(); i++) {
            Node laptop = laptopList.item(i);

            if (laptop.getNodeType() == Node.ELEMENT_NODE) {
                Element laptopElement = (Element) laptop;
                if (((Element) laptop).hasAttribute("currency") && ((Element) laptop).hasAttribute("rate")) {
                    String currency = laptopElement.getAttribute("currency");
                    double rate = Double.parseDouble(laptopElement.getAttribute("rate"));
                    mapCurrencyRate.put(currency, rate);
                }
            }
        }
        return mapCurrencyRate;
    }

    public ArrayList<String> getCurrencies() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<String> currenciesList = new ArrayList<>();
        Map<String, Double> map = getAllElements();
        map.forEach((key, value) -> currenciesList.add(key));
        return currenciesList;
    }

    private NodeList getDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = this.factory.newDocumentBuilder();

        // Get Document
        Document document = builder.parse(new File(FILENAME));

        // Normalize the xml structure
        document.getDocumentElement().normalize();
        return document.getElementsByTagName("Cube");
    }
}
