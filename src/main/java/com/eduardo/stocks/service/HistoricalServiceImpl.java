package com.eduardo.stocks.service;

import com.eduardo.stocks.model.Stock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HistoricalServiceImpl implements HistoricalService{

    private ConcurrentHashMap<Long, List<Stock>> historicalStocks = new ConcurrentHashMap<>();

    @Override
    public List<Stock> getPrices(Long id) {
        return historicalStocks.get(id);
    }

    @Override
    public void addPrice(Stock stock) {
        Long id = stock.getId();
        if (historicalStocks.containsKey(id)) {
            historicalStocks.get(id).add(stock);
        }
        else {
            List<Stock> stocks= new ArrayList<>();
            stocks.add(stock);
            historicalStocks.put(id, stocks);
        }
    }
}
