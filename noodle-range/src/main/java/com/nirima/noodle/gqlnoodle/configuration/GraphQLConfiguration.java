package com.nirima.noodle.gqlnoodle.configuration;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;

import com.nirima.noodle.gqlnoodle.domain.Product;
import com.nirima.noodle.gqlnoodle.graphql.QueryController;
import graphql.schema.DataFetcher;
import graphql.schema.TypeResolver;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfiguration {
    public static final String PRODUCT_TYPE = "Product";

    @Resource
    QueryController queryController;

    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        DataFetcher entityDataFetcher = env -> {
            List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
            return representations.stream()
                    .map(representation -> {
                        if (PRODUCT_TYPE.equals(representation.get("__typename"))) {
                            return queryController.product((String)representation.get("id"));
                            //return new Product(UUID.fromString((String) representation.get("id")));
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        };
        TypeResolver entityTypeResolver = env -> {
            final Object src = env.getObject();
            if (src instanceof Product) {
                return env.getSchema()
                        .getObjectType(PRODUCT_TYPE);
            }
            return null;
        };

        return builder -> {
            builder. schemaFactory((registry, wiring) ->
                    Federation.transform(registry, wiring)
                            .fetchEntities(entityDataFetcher)
                            .resolveEntityType(entityTypeResolver)
                            .build()
            );
        };
    }
}