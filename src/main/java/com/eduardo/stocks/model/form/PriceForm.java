package com.eduardo.stocks.model.form;

import java.math.BigDecimal;

public class PriceForm {
    private BigDecimal currentPrice;

    public PriceForm() {

    }

    public PriceForm(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
}
