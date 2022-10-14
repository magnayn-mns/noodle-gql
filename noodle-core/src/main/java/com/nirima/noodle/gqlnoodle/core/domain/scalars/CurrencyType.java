package com.nirima.noodle.gqlnoodle.core.domain.scalars;

import graphql.schema.*;

public class CurrencyType {
    public static final GraphQLScalarType CURRENCY_SCALAR_TYPE = GraphQLScalarType.newScalar()
            .name("Currency")
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
