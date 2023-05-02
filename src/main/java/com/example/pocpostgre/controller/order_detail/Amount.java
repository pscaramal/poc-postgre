package com.example.pocpostgre.controller.order_detail;

import java.math.BigDecimal;

public class Amount {
    private BigDecimal amountLocalCurrency;

    private String currency;

    public BigDecimal getAmountLocalCurrency() {
        return amountLocalCurrency;
    }

    public void setAmountLocalCurrency(BigDecimal amountLocalCurrency) {
        this.amountLocalCurrency = amountLocalCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
