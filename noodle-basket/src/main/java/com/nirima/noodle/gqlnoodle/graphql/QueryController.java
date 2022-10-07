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

    Map<UUID, Basket> basketMap = new HashMap<>();

    @QueryMapping
    public Basket basket(@Argument UUID id) {
        return basketMap.get(id);
    }
    public QueryController() {

        var b = new Basket(UUID.fromString("53AB031D-65B3-4C69-B77A-F2E920554498"));

        b.addLine(new Product(UUID.fromString("6B40E5CF-47A2-4CC6-9FF0-4A2B77E88677")), 1.0);
        b.addLine(new Product(UUID.fromString("E2640D3D-F430-48F7-B5B4-1D6486C8D267")), 1.0);
        b.addLine(new Product(UUID.fromString("A020EC78-FDD5-4D9A-BA67-368EA0299944")), 1.0);
        b.addLine(new Product(UUID.fromString("6D4162A5-A92D-486F-8E6F-519FD044036D")), 1.0);
        b.addLine(new Product(UUID.fromString("7F4101AB-A47B-4F0E-A90A-894605FB9E0F")), 1.0);
        b.addLine(new Product(UUID.fromString("B8D7D4BD-6519-4D5F-A1BE-A05B2B7E5587")), 1.0);
        b.addLine(new Product(UUID.fromString("B55E2FF0-95FC-415A-8CDF-3D1FEBE9FD2E")), 1.0);


        basketMap.put(b.id, b);
    }


}
