package com.eduardo.stocks.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Stock {
    Long getId();
    String getName();
    BigDecimal getCurrentPrice();
    LocalDateTime getLastUpdate();
}
