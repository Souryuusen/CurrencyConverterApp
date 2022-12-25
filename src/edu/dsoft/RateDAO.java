package edu.dsoft;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class RateDAO {

    private static final String BASE_NBP_URL = "http://api.nbp.pl/api/exchangerates/rates/c/";
    private static final String DATA_FORMAT = "/?format=xml";
    private static final String CURRENCY_LIST_URL = "http://api.nbp.pl/api/exchangerates/tables/c/";

    public static Document requestRateData(String currencyCode) {
        Document doc = null;

        try {
            //  Parsing Data URL Base On Given Currency Code i.e EUR
            URL daoURL = new URL(BASE_NBP_URL + currencyCode + DATA_FORMAT);
            //  Creating URL Connection For Accessing Current Exchange Rates
            HttpURLConnection daoConnection = (HttpURLConnection)daoURL.openConnection();
            daoConnection.addRequestProperty("Accept", "application/xml");
            //  Building New XML Document
            doc = internalDocumentParseRequest(daoConnection);
        } catch (MalformedURLException ex) {
            System.err.println("Niepoprawny adres URL!!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            System.err.println("Błąd konfiguracji Parsera!!");
            ex.printStackTrace();
        } catch (SAXException ex) {
            System.err.println("Błąd Simple Api XML!!");
            ex.printStackTrace();
        }
        return  doc;
    }

    public static Set<Rate> obtainCurrencyList() {
        HashSet<Rate> resultList = new HashSet<>();
        Document document = null;
        HttpURLConnection connection = null;

        try {
            // Creation Of URL Object For Obtaining Currency List
            URL dataURL = new URL(CURRENCY_LIST_URL);
            // Creation Of HTTP Connection Based On URL
            connection = (HttpURLConnection) dataURL.openConnection();
            connection.addRequestProperty("Accept", "application/xml");
            // Creation Of XML Document
            document = internalDocumentParseRequest(connection);
        } catch (MalformedURLException ex) {
            System.err.println("Niepoprawny URL listy obsługiwanych walut!");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("Błąd otwarcia komunikacji!!");
            ex.printStackTrace();
        } catch (ParserConfigurationException | SAXException ex) {
            System.err.println("Błąd parsowania danych!!");
            ex.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        if(document != null) {
            NodeList nodes = document.getElementsByTagName("Rate");
            for(int i = 0; i < nodes.getLength(); i++) {
                NodeList atr = nodes.item(i).getChildNodes();
                if(atr.getLength() == 4) {
                    String currencyName = atr.item(0).getTextContent().trim();
                    String currencyCode = atr.item(1).getTextContent().trim();
                    BigDecimal ask = BigDecimal.valueOf(Double.parseDouble(atr.item(3).getTextContent().trim()));
                    BigDecimal bid = BigDecimal.valueOf(Double.parseDouble(atr.item(2).getTextContent().trim()));
                    resultList.add(new Rate(currencyName, currencyCode, ask, bid));
                }
            }
        }
        return resultList;
    }

    private static Document internalDocumentParseRequest(HttpURLConnection connection) throws ParserConfigurationException, IOException, SAXException {
        // Return Object Creation
        Document result;
        // Parsing Of HttpURLConnection Data
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        result = documentBuilder.parse(connection.getInputStream());
        result.getDocumentElement().normalize();
        // End Of Method - Return Parsed Data
        return result;
    }

}
