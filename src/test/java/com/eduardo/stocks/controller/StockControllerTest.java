package com.eduardo.stocks.controller;

import com.eduardo.stocks.Application;
import com.eduardo.stocks.model.Stock;
import com.eduardo.stocks.model.form.PriceForm;
import com.eduardo.stocks.model.form.StockForm;
import com.eduardo.stocks.service.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = {Application.class, StockController.class}
)
@AutoConfigureMockMvc
public class StockControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StockService stockService;

    @Test
    public void createStock() throws Exception {
        String name = "name";
        BigDecimal price = BigDecimal.valueOf(7);

        mvc.perform(post("/api/stocks")
            .content(createStockFormJson(name, price))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.currentPrice", is("7,00")))
            .andReturn();
    }

    @Test
    public void getOne() throws Exception {
        //Given I have a stock
        String name = "name";
        BigDecimal price = BigDecimal.valueOf(15);
        Stock stock = stockService.addStock(new StockForm(name, price));

        // When I use the get API method, I get this element
        mvc.perform(get("/api/stocks/" + stock.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.currentPrice", is("15,00")))
            .andReturn();

        // When I use the get API method with a different id, I get a 404
        mvc.perform(get("/api/stocks/-1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void getAll() throws Exception {
        // Given I didn't add elements, When I query the get all API, I get no elements
        mvc.perform(get("/api/stocks/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        //Given I have multiple stocks
        for (int i = 0; i < 54; ++i) {
            stockService.addStock(new StockForm("name", BigDecimal.ZERO));
        }

        // When I query the get all API, I get all the elements
        mvc.perform(get("/api/stocks/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(54)))
                .andReturn();
    }

    @Test
    public void update() throws Exception {

        // Given I didn't add elements, When I try to update an element, I get a 404
        mvc.perform(put("/api/stocks/0")
                .content(createPriceFormJson(BigDecimal.ZERO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        //Given I add an element
        Stock stock = stockService.addStock(new StockForm("name", BigDecimal.ZERO));

        // When I update it, I get the new price
        mvc.perform(put("/api/stocks/" + stock.getId())
                .content(createPriceFormJson(BigDecimal.TEN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("name")))
                .andExpect(jsonPath("$.currentPrice", is("10,00")))
                .andReturn();
    }

    private String createStockFormJson(String name, BigDecimal price) throws JsonProcessingException {
        StockForm stockForm = new StockForm(name, price);

        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(stockForm);
    }

    private String createPriceFormJson(BigDecimal price) throws JsonProcessingException {
        PriceForm priceForm = new PriceForm(price);

        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(priceForm);
    }


}

