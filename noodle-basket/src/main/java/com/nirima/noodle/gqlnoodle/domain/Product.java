package com.nirima.noodle.gqlnoodle.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    public UUID id = UUID.randomUUID();

    public Product(UUID id) {
        this.id = id;
    }
}