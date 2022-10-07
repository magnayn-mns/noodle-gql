package com.nirima.noodle.gqlnoodle.domain;

import java.util.UUID;

public class Product {
    public UUID id = UUID.randomUUID();

    public String name;
    public String colour;
    public String GTIN;

    public Product(UUID id) {
        this.id = id;
    }

    public Product(UUID id, String name, String colour, String GTIN) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.GTIN = GTIN;
    }

    public UUID getId() {
        return id;
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
