package com.eduardo.stocks.model.form;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockForm {
    private String name;
    private BigDecimal currentPrice;

    public String getName() {
        return name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

}
