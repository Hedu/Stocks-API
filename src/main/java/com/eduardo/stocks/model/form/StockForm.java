package com.eduardo.stocks.model.form;

import java.math.BigDecimal;

public class StockForm {
    private String name;
    private BigDecimal currentPrice;

    public StockForm(String name, BigDecimal currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

}
