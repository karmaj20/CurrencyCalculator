import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class CalculatorDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        XMLParser xmlParser = new XMLParser();
        ArrayList<String> currencies = xmlParser.getCurrencies();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Available currencies: ");
        currencies.forEach(value -> System.out.print(value + " "));
        System.out.println();
        System.out.println("What currency would you like to recalculate from euros ?");
        String currency = scanner.nextLine().toUpperCase(Locale.ROOT);
        System.out.println("How many euros would you recalculate into other currency ?");
        double euros = scanner.nextDouble();
        Calculator calc = new Calculator(euros, currency);
        double result = calc.euroCalculator(euros, currency);
        System.out.printf("%.2f EURO is: %.2f %S", euros, result, currency);
    }
}
