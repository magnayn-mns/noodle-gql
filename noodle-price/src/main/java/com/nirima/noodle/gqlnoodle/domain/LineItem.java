package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;

public class LineItem extends Entity {
    public String markedPrice = "9.99";
    public String nowUnitPrice = "9.99";
    public String  priceBeforeConditionalDiscounts = "9.99";
    public String  priceBeforeDiscounts= "9.99";
    public String  wasUnitPrice = "9.99";

    public Product  product = new Product();


    public Quantity quantity = new Quantity();




    public LineItem() {
    }

    public LineItem(String productId, int q) {
        product.id = productId;
        quantity.numberOfUnits = "" + q;
    }

    public LineItem(String id) {
        super(id);
    }
}
