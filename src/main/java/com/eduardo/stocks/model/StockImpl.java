package com.eduardo.stocks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;

public class StockImpl implements Stock  {

    private Long id;
    private String name;

    @JsonIgnore
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

    @JsonProperty("currentPrice")
    public String getFormattedPrice() {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        return format.format(currentPrice);
    }
}
