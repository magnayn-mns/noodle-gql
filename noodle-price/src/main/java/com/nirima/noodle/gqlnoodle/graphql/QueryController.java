package com.nirima.noodle.gqlnoodle.graphql;

import com.nirima.noodle.gqlnoodle.domain.Price;
import com.nirima.noodle.gqlnoodle.domain.Product;
import com.nirima.noodle.gqlnoodle.domain.Unit;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class QueryController {

    public QueryController() {

    }


    @QueryMapping
    Price price(@Argument UUID productId) {
        Price p = new Price();
        p.amount = 123.23;
        p.uom = Unit.EA;
        return p;
    }


}
