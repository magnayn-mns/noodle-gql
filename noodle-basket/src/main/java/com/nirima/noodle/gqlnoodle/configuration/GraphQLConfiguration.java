package com.nirima.noodle.gqlnoodle.configuration;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.CurrencyType;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.DirectionalMoneyType;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.MoneyType;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.ScalarType;
import com.nirima.noodle.gqlnoodle.domain.Product;
import graphql.Assert;
import graphql.GraphQLContext;
import graphql.schema.*;
import io.honeycomb.opentelemetry.sdk.trace.spanprocessors.BaggageSpanProcessor;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import io.honeycomb.opentelemetry.OpenTelemetryConfiguration;
@Configuration
public class GraphQLConfiguration {
    public static final String PRODUCT_TYPE = "Product";

    public static final GraphQLScalarType BINGO_TYPE = GraphQLScalarType.newScalar()
            .name("Bingo")
            .description("A custom scalar that handles emails")
            .withDirective(GraphQLDirective.newDirective()
                    .name("conformsRegex")
                    .argument(GraphQLArgument.newArgument()
                            .name("regex").type(GraphQLTypeReference.typeRef("string"))
                            .build())
                    .build())
            .coercing(new Coercing() {
                @Override
                public Object serialize(Object dataFetcherResult) {
                    return dataFetcherResult.toString();
                }

                public Object serialize(Object dataFetcherResult, GraphQLContext graphQLContext, Locale locale) throws CoercingSerializeException {
                    Assert.assertNotNull(dataFetcherResult);
                    Assert.assertNotNull(graphQLContext);

                    return this.serialize(dataFetcherResult);
                }

                @Override
                public Object parseValue(Object input) {
                    return "eh";
                }

                @Override
                public Object parseLiteral(Object input) {
                    return "";
                }
            })
            .build();

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder ->
                wiringBuilder.scalar(ScalarType.BASKET_ID_TYPE)
                        .scalar(BINGO_TYPE);
    }
    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        DataFetcher entityDataFetcher = env -> {
            List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
            return representations.stream()
                    .map(representation -> {
                        if (PRODUCT_TYPE.equals(representation.get("__typename"))) {
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