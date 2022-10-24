package com.nirima.noodle.gqlnoodle.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.nirima.noodle.gqlnoodle.core.domain.Entity;
public class Basket extends Entity {
    public List<BasketLine> contents = new ArrayList<>();

    public Quote currentQuote = new Quote("quote-1");

    public Basket(List<BasketLine> contents) {
        this.contents = contents;
    }

    public Basket(String id) {
        super(id);
    }

    public void addLine(String id, Product p, double v) {
        contents.add( new BasketLine(this, id, p, v) );
    }
}
