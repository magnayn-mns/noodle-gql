package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;

public class BasketLine extends Entity {
    public Basket basket;
    public Product product;
    public double quantity;

    public BasketLine(Basket b, String id, Product product, double quantity) {
        super(id);
        this.basket = b;
        this.product = product;
        this.quantity = quantity;
    }

    public BasketLineToPriceLine getPricingInformation() {
        return new BasketLineToPriceLine(basket.currentQuote.id, id);
    }
}
