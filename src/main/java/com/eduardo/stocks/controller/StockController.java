package com.eduardo.stocks.controller;

import com.eduardo.stocks.model.Stock;
import com.eduardo.stocks.model.form.PriceForm;
import com.eduardo.stocks.model.form.StockForm;
import com.eduardo.stocks.service.HistoricalService;
import com.eduardo.stocks.service.StockService;
import com.eduardo.stocks.util.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private
    StockService stockService;

    @Autowired
    private
    HistoricalService historicalService;

    @RequestMapping(
        value = {"","/"},
        method = RequestMethod.GET,
        produces = "application/json")
    public List<Stock> getAll() {
        return stockService.getAll();
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = "application/json")
    public ResponseEntity<Stock> getStock(@PathVariable Long id) {
        return RestUtils.getResponseEntity(stockService.getById(id));
    }

    @RequestMapping(
        value = "/{id}/historical",
        method = RequestMethod.GET,
        produces = "application/json")
    public ResponseEntity<List<Stock>> getHistorical(@PathVariable Long id) {
        return RestUtils.getResponseEntity(historicalService.getPrices(id));
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.PUT,
        consumes = "application/json",
        produces = "application/json")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody PriceForm priceForm) {
        return RestUtils.getResponseEntity(stockService.updateStock(id, priceForm));
    }

    @RequestMapping(
        value = {"","/"},
        method = RequestMethod.POST,
        consumes = "application/json",
        produces = "application/json")
    public Stock addStock(@RequestBody StockForm stockForm) {
        return stockService.addStock(stockForm);
    }

}
