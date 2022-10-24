package com.nirima.noodle.gqlnoodle.graphql;

import com.nirima.noodle.gqlnoodle.domain.Basket;
import com.nirima.noodle.gqlnoodle.domain.Product;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class QueryController {

    Map<String, Basket> basketMap = new HashMap<>();

    @QueryMapping
    public Basket basket(@Argument String id) {
        return basketMap.get(id);
    }
    public QueryController() {

        var b = new Basket("basket-1");

        b.addLine("1", new Product("product-1"), 1.0);
        b.addLine("2", new Product("product-2"), 2.0);
        b.addLine("3", new Product("product-3"), 5.0);
        b.addLine("4", new Product("product-4"), 1.0);
        b.addLine( "5", new Product("product-5"), 1.0);

        basketMap.put(b.id, b);
    }


}
