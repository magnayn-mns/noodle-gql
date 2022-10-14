package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;

public class Product extends Entity
{
    public Price listPrice = new Price();

    public Product() {
    }

    public Product(String id) {
        super(id);
    }
}
