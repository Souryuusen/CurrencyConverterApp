package edu.dsoft;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateDAO {

    private static final String BASE_NBP_URL = "http://api.nbp.pl/api/exchangerates/rates/c/";
    private static final String DATA_FORMAT = "/?format=xml";

    public static Document requestRateData(String currencyCode) {
        Document doc = null;

        try {
            //  Parsing Data URL Base On Given Currency Code i.e EUR
            URL daoURL = new URL(BASE_NBP_URL + currencyCode + DATA_FORMAT);
            //  Creating URL Connection For Accessing Current Exchange Rates
            HttpURLConnection daoConnection = (HttpURLConnection)daoURL.openConnection();
            daoConnection.addRequestProperty("Accept", "application/xml");
            //  Building New XML Document
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(daoConnection.getInputStream());
            doc.getDocumentElement().normalize();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }
        return  doc;
    }

}
