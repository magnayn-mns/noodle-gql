package com.nirima.noodle.gqlnoodle.graphql;

import com.nirima.noodle.gqlnoodle.domain.Product;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class QueryController {

    Map<String, Product> productMap;

    public QueryController() {
        var l = Arrays.asList(
                new Product("product-1", "Bee Cushion", "Teal", "0290340"),
                new Product("product-2", "Straight Fit Super Stretch Performance Jeans", "Grey", "T171389M"),
        new Product("product-3", "Cotton Rich Corduroy Wide Leg Trousers", "Red", "T831239"),
                new Product("product-4", "Set of 2 Velvet Dining Chairs", "Loft Velvet, Grey", "T654801B"),
                new Product("product-5", "The Connoisseur’s Choice Red Wine Gift Box", "", "00777643")
        );

        productMap = l.stream().collect(Collectors.toMap(e -> e.id, e -> e));
    }


    @QueryMapping
    public Product product(@Argument String id) {
        return productMap.get(id);
    }

    @QueryMapping
    public Iterable<Product> products() {
        return productMap.values();
    }


}
