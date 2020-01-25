package com.eduardo.stocks.service;

import com.eduardo.stocks.factory.StockBuilderFactory;
import com.eduardo.stocks.model.Stock;
import com.eduardo.stocks.model.form.PriceForm;
import com.eduardo.stocks.model.form.StockForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockBuilderFactory stockBuilderFactory;

    @Autowired
    private HistoricalService historicalService;

    private ConcurrentHashMap<Long, Stock> stocks = new ConcurrentHashMap<>();
    private Long lastId = -1L;

    @Override
    public List<Stock> getAll() {
        return new ArrayList<>(stocks.values());
    }

    @Override
    public Stock getById(Long id) {
        return stocks.get(id);
    }

    @Override
    public Stock updateStock(Long id, PriceForm priceForm) {
        if (stocks.containsKey(id)) {
            Stock stock = stocks.get(id);
            historicalService.addPrice(stock);
            Stock newStock = stockBuilderFactory.getBuilder()
                .setId(id)
                .setName(stock.getName())
                .setCurrentPrice(priceForm.getCurrentPrice())
                .setLastUpdate(LocalDateTime.now())
                .build();
            stocks.put(id, newStock);
            return newStock;
        }
        return null;
    }

    @Override
    public Stock addStock(StockForm stockForm) {
        Long newId;
        synchronized(this) {
            newId = ++lastId;
        }
        Stock newStock = stockBuilderFactory.getBuilder()
            .setId(newId)
            .setName(stockForm.getName())
            .setCurrentPrice(stockForm.getCurrentPrice())
            .setLastUpdate(LocalDateTime.now())
            .build();

        stocks.put(newId, newStock);
        return newStock;
    }
}
