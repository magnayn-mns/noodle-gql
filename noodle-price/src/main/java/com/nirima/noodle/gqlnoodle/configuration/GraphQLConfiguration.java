package com.nirima.noodle.gqlnoodle.configuration;

import com.apollographql.federation.graphqljava.Federation;
import com.apollographql.federation.graphqljava._Entity;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.CurrencyType;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.DirectionalMoneyType;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.MoneyType;
import com.nirima.noodle.gqlnoodle.core.domain.scalars.ScalarType;
import com.nirima.noodle.gqlnoodle.domain.BasketLineToPriceLine;
import com.nirima.noodle.gqlnoodle.domain.Price;
import com.nirima.noodle.gqlnoodle.domain.Product;

import com.nirima.noodle.gqlnoodle.domain.Quote;
import com.nirima.noodle.gqlnoodle.graphql.QueryController;
import graphql.scalars.ExtendedScalars;
import graphql.schema.DataFetcher;
import graphql.schema.TypeResolver;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GraphQLConfiguration {

    @Resource
    QueryController queryController;

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.DateTime)
                .scalar(CurrencyType.CURRENCY_SCALAR_TYPE)
                .scalar(MoneyType.MONEY_SCALAR_TYPE)
                .scalar(DirectionalMoneyType.TYPE)
                .scalar(ScalarType.PRICE_ID_TYPE);
    }
    @Bean
    public GraphQlSourceBuilderCustomizer federationTransform() {
        DataFetcher entityDataFetcher = env -> {
            List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
            return representations.stream()
                    .map(representation -> {
                        if ("Quote".equals(representation.get("__typename"))) {
                          return queryController.quote((String)representation.get("id"));
                        }
                        if ("Product".equals(representation.get("__typename"))) {
                            // Any old product - should really look it up
                            return new Product();
                        }
                        if("BasketLineToPriceLine".equals(representation.get("__typename"))) {
                            BasketLineToPriceLine basketLineToPriceLine = new BasketLineToPriceLine();
                            basketLineToPriceLine.lineId =  (String)representation.get("lineId");
                            basketLineToPriceLine.quoteId = (String)representation.get("quoteId");

                            var quote = queryController.quote(basketLineToPriceLine.quoteId);
                            basketLineToPriceLine.lineItem = quote.lineItems.get(Integer.parseInt(basketLineToPriceLine.lineId)-1);
                            return basketLineToPriceLine;
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        };
        TypeResolver entityTypeResolver = env -> {
            final Object src = env.getObject();
            if (src instanceof Product) {
                return env.getSchema()
                        .getObjectType("Product");
            }
            if (src instanceof Quote) {
                return env.getSchema()
                        .getObjectType("Quote");
            }
            if (src instanceof BasketLineToPriceLine) {
                return env.getSchema()
                        .getObjectType("BasketLineToPriceLine");
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