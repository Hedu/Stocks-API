package com.eduardo.stocks.factory;

import com.eduardo.stocks.model.StockBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface StockBuilderFactory {
    StockBuilder getBuilder();
}
