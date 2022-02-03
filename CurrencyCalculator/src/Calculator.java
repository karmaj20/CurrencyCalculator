import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

public class Calculator {
    private final double euros;
    private final String curr;
    XMLParser xmlParser = new XMLParser();

    public Calculator(double euros, String curr) {
        this.euros = euros;
        this.curr = curr;
    }

    public double euroCalculator(double euros, String currency) throws ParserConfigurationException, IOException, SAXException {
        double result = 0.0;
        Map<String, Double> mapCurrencyRate = this.xmlParser.getAllElements();
        for (Map.Entry<String, Double> entry : mapCurrencyRate.entrySet()) {
            if (entry.getKey().equals(currency)) {
                result = entry.getValue() * euros;
            }
        }
        if (result == 0.0) {
            throw new NoSuchElementException("No such currency found, try again");
        }

        return result;
    }

    public double getEuros() {
        return euros;
    }

    public String getCurr() {
        return curr;
    }
}
