package com.nirima.noodle.gqlnoodle.graphql;

import com.nirima.noodle.gqlnoodle.domain.*;
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
    public Map<String, Quote> quoteMap = new HashMap<>();
    public QueryController() {
        var q123 = new Quote("quote-1");


        q123.addLineItem( new LineItem("product-1", 1));
        q123.addLineItem( new LineItem("product-2", 2));
        q123.addLineItem( new LineItem("product-3", 5));
        q123.addLineItem( new LineItem("product-4", 1));
        q123.addLineItem( new LineItem("product-5", 1));

        quoteMap.put(q123.id, q123);
    }


    @QueryMapping
    public Quote quote(@Argument String id) {

        return quoteMap.get(id);
    }


}
