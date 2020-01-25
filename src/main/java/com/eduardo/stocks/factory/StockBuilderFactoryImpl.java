package com.eduardo.stocks.factory;

import com.eduardo.stocks.model.StockBuilder;
import org.springframework.stereotype.Service;

@Service
public class StockBuilderFactoryImpl implements StockBuilderFactory{

    @Override
    public StockBuilder getBuilder() {
        return new StockBuilder();
    }
}
