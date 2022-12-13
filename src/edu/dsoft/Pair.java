package edu.dsoft;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Pair {

    Rate firstCurrency;
    Rate secondCurrency;

    String pairName;

    BigDecimal pairExchangeRateAsk;
    BigDecimal pairExchangeRateBid;

    public Pair() {
        this.firstCurrency = null;
        this.secondCurrency = null;
        this.pairName = "";
        this.pairExchangeRateAsk = null;
        this.pairExchangeRateBid = null;
    }

    public Pair(Rate firstCurrency, Rate secondCurrency) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;

        pairName = this.firstCurrency.getCurrencyCode().toUpperCase() + "/" + this.secondCurrency.getCurrencyCode();

        calculateExchangeRate(this.firstCurrency, this.secondCurrency);

    }

    private void calculateExchangeRate(Rate firstRate, Rate secondRate) {
        // Exchange Pair Ask Value Calculation - Temporary Calculation 1 PLN = X Currency
        BigDecimal tempAsk1 = BigDecimal.valueOf(1.0000).divide(firstRate.getAskValue(), MathContext.DECIMAL128);
        BigDecimal tempAsk2 = BigDecimal.valueOf(1.0000).divide(secondRate.getAskValue(), MathContext.DECIMAL128);
        // Exchange Pair Ask Value Calculation - 1 First Currency = Y Second Currency
        this.pairExchangeRateAsk = tempAsk2.divide(tempAsk1, 10, RoundingMode.HALF_EVEN);
        // Exchange Pair Bid Value Calculation - Temporary Calculation 1 PLN = X Currency
        BigDecimal tempAsk3 = BigDecimal.valueOf(1.0000).divide(firstRate.getBidValue(), MathContext.DECIMAL128);
        BigDecimal tempAsk4 = BigDecimal.valueOf(1.0000).divide(secondRate.getBidValue(), MathContext.DECIMAL128);
        // Exchange Pair Bid Value Calculation - 1 First Currency = Y Second Currency
        this.pairExchangeRateBid = tempAsk4.divide(tempAsk3, 10, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return this.pairName + "\n\tAsk:\t" + this.pairExchangeRateAsk + "\n\tBid:\t" + this.pairExchangeRateBid;
    }
}
