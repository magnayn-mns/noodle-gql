package com.nirima.noodle.gqlnoodle.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Basket {
    public UUID id = UUID.randomUUID();
    public List<BasketLine> contents = new ArrayList<>();
    public Basket(UUID id) {
        this.id = id;
    }

    public void addLine(Product p, double v) {
        contents.add( new BasketLine(p, v) );
    }
}
