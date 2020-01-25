package com.eduardo.stocks;

import com.eduardo.stocks.controller.StockController;
import com.eduardo.stocks.model.Stock;
import com.eduardo.stocks.model.form.StockForm;
import com.eduardo.stocks.service.HistoricalService;
import com.eduardo.stocks.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(
    classes = {Application.class}
)
public class ConcurrentTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private HistoricalService historicalService;

    @Test
    public void addStocks() throws ExecutionException, InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean running = new AtomicBoolean();
        AtomicInteger overlaps = new AtomicInteger();
        int threads = 1000;
        ExecutorService service = Executors.newFixedThreadPool(threads);

        Collection<Future<Stock>> futures = new ArrayList<>(threads);
        for (int t = 0; t < threads; ++t) {
            final StockForm stockForm = new StockForm("name", BigDecimal.ZERO);
            futures.add(
                service.submit(
                    () -> {
                        latch.await();
                        if (running.get()) {
                            overlaps.incrementAndGet();
                        }
                        running.set(true);
                        Stock stock = stockService.addStock(stockForm);
                        running.set(false);
                        return stock;
                    }
                )
            );
        }

        latch.countDown();
        Set<Long> ids = new LinkedHashSet<>();

        for (Future<Stock> f : futures) {
            ids.add(f.get().getId());
        }

        System.out.println(overlaps.get());
        assertThat(ids.size(), equalTo(threads));
        assertThat(overlaps.get(), greaterThan(0));
    }

}
