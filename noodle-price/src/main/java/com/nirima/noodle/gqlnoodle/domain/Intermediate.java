package com.nirima.noodle.gqlnoodle.domain;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.ArrayList;
import java.util.List;

public class Intermediate {
    @QueryMapping
    public List<Pricing> getListPrice(@Argument ListPriceFilter param) {
        var l = new ArrayList<Pricing>();

        return l;
    }

}
