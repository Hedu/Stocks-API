package com.eduardo.stocks.service;

import com.eduardo.stocks.model.Stock;

import java.util.List;

public interface HistoricalService {
    List<Stock> getPrices(Long id);

    void addPrice(Stock stock);
}
