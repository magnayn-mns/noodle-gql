package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.ArrayList;
import java.util.List;

public class Product extends Entity
{
    public Intermediate intermediate = new Intermediate();

    public Product() {
    }

    public Product(String id) {
        super(id);
    }


    public String getTest() {
        return "Bibble";
    }
    public List<Pricing> listPrice(ListPriceFilter param) {
        var l = new ArrayList<Pricing>();

        return l;
    }

    @QueryMapping
    public List<Pricing> getListPrice(@Argument ListPriceFilter param) {
        var l = new ArrayList<Pricing>();

        return l;
    }

    public List<Pricing> getListPrice(@Argument DataFetchingEnvironment dfe) {
        var l = new ArrayList<Pricing>();
        var value = dfe.getArgument("param");
        return l;
    }

    public List<Pricing> getListPrice() {
        var l = new ArrayList<Pricing>();

        return l;
    }
}
