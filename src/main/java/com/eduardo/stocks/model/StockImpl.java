package com.eduardo.stocks.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockImpl implements Stock  {

    private Long id;
    private String name;
    private BigDecimal currentPrice;
    private LocalDateTime lastUpdate;

    public StockImpl(Long id, String name, BigDecimal currentPrice, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }
}
