package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;

import java.util.UUID;

public class Product extends Entity {

    public String name;
    public String colour;
    public String GTIN;

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, String colour, String GTIN) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.GTIN = GTIN;
    }


    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public String getGTIN() {
        return GTIN;
    }
}
