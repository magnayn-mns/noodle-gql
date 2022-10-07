package com.nirima.noodle.gqlnoodle.domain;

public class BasketLine {
    public Product product;
    public double quantity;

    public BasketLine(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
