package com.nirima.noodle.gqlnoodle.core.domain.scalars;

import graphql.schema.*;

public class ScalarType {
    public static final GraphQLScalarType BASKET_ID_TYPE = GraphQLScalarType.newScalar()
            .name("BasketID")
            .coercing(new Coercing() {
                @Override
                public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
                    return dataFetcherResult.toString();
                }

                @Override
                public Object parseValue(Object input) throws CoercingParseValueException {
                    return null;
                }

                @Override
                public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                    return null;
                }
            })
            .build();

    public static final GraphQLScalarType PRICE_ID_TYPE = GraphQLScalarType.newScalar()
            .name("PriceID")
            .coercing(new Coercing() {
                @Override
                public Object serialize(Object dataFetcherResult) throws CoercingSerializeException {
                    return dataFetcherResult.toString();
                }

                @Override
                public Object parseValue(Object input) throws CoercingParseValueException {
                    return null;
                }

                @Override
                public Object parseLiteral(Object input) throws CoercingParseLiteralException {
                    return null;
                }
            })
            .build();
}
