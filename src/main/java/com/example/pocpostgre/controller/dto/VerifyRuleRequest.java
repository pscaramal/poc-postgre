package com.example.pocpostgre.controller.dto;

import com.example.pocpostgre.controller.order_detail.Merchant;
import com.example.pocpostgre.controller.order_detail.Order;
import com.example.pocpostgre.controller.order_detail.OrderDetails;
import com.example.pocpostgre.controller.order_detail.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyRuleRequest {

    private Order order;
    private Merchant merchant;
    private List<PaymentMethod> paymentMethods;
    private OrderDetails orderDetails;
    private String segment;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }
}
