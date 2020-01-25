package com.eduardo.stocks.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockBuilder {

    private Long id;
    private String name;
    private BigDecimal currentPrice;
    private LocalDateTime lastUpdate;

    public StockBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public StockBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StockBuilder setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public StockBuilder setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public Stock build() {
        return new StockImpl(id, name, currentPrice, lastUpdate);
    }
}
