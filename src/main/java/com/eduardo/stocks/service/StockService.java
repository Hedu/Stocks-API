package com.eduardo.stocks.service;

import com.eduardo.stocks.model.Stock;
import com.eduardo.stocks.model.form.PriceForm;
import com.eduardo.stocks.model.form.StockForm;

import java.util.List;

public interface StockService {

    List<Stock> getAll();

    Stock getById(Long id);

    Stock updateStock(Long id, PriceForm priceForm);

    Stock addStock(StockForm stockForm);
}
