package edu.dsoft;

import org.w3c.dom.Document;

import java.math.BigDecimal;

public class Rate {

    String currencyName;
    String currencyCode;

    BigDecimal askValue;
    BigDecimal bidValue;

    public Rate() {
        currencyName = "";
        currencyCode = "";

        askValue = BigDecimal.valueOf(0);
        bidValue = BigDecimal.valueOf(0);
    }

    public Rate(String currencyName, String currencyCode, BigDecimal askValue, BigDecimal bidValue) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.askValue = askValue;
        this.bidValue = bidValue;
    }

    public Rate(String currencyCode) {
        if(!currencyCode.equalsIgnoreCase("PLN")) {
            try {
                parseXMLDocument(RateDAO.requestRateData(currencyCode));
            } catch (IllegalArgumentException ex) {
                System.out.println("Błąd pobrania danych dla waluty o kodzie:\t" + currencyCode);
            }
        } else {
            this.currencyName = "Polski Złoty";
            this.currencyCode = "PLN";
            this.askValue = BigDecimal.valueOf(1.0000);
            this.bidValue = BigDecimal.valueOf(1.0000);
        }
    }


    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getAskValue() {
        return askValue;
    }

    public BigDecimal getBidValue() {
        return bidValue;
    }

    private void parseXMLDocument(Document doc) throws IllegalArgumentException{
        if(doc != null) {
            String readName = doc.getElementsByTagName("Currency").item(0).getTextContent();
            String readCode = doc.getElementsByTagName("Code").item(0).getTextContent();
            double readAsk = Double.parseDouble(doc.getElementsByTagName("Ask").item(0).getTextContent());
            double readBid = Double.parseDouble(doc.getElementsByTagName("Bid").item(0).getTextContent());

            if(readName != null && readCode != null && readAsk != 0 && readBid != 0) {
                this.currencyName = readName;
                this.currencyCode = readCode;
                this.askValue = BigDecimal.valueOf(readAsk);
                this.bidValue = BigDecimal.valueOf(readBid);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return currencyName.toUpperCase() + " " + currencyCode.toUpperCase() + "\n\tAsk:\t" + askValue + "\n\tBid:\t" + bidValue ;
    }
}
