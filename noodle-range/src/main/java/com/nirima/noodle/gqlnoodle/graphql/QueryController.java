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

    Map<UUID, Product> productMap;

    public QueryController() {
        var l = Arrays.asList(
                new Product(UUID.fromString("6B40E5CF-47A2-4CC6-9FF0-4A2B77E88677"),
                        "Bee Cushion", "Teal", "0290340")
        );

        productMap = l.stream().collect(Collectors.toMap(e -> e.id, e -> e));
    }

//    @QueryMapping
//    public Product product() {
//        return productMap.get(UUID.fromString("53AB031D-65B3-4C69-B77A-F2E920554498"));
//    }

    @QueryMapping
    public Product product(@Argument UUID id) {
        return productMap.get(id);
    }

    @QueryMapping
    public Iterable<Product> products() {
        return productMap.values();
    }


//    public Iterable<Object> _entities(Iterable<Object> representation) {
//        return new ArrayList<>();
//    }
//
//    public Service _service() {
//        return new Service();
//    }
}
