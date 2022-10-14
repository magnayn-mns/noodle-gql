package com.nirima.noodle.gqlnoodle.core.domain.scalars;

import graphql.schema.*;

public class MoneyType {
    public static final GraphQLScalarType MONEY_SCALAR_TYPE = GraphQLScalarType.newScalar()
            .name("Money")
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
