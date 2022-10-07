Some federation-able GraphQL things, spring boot + apollo

Individual Domains
- noodle-basket 
- noodle-price
- noodle-range

Run each of these in the IDE

- noodle-federation
'npm start' once the above are up and running

The federation action appears to occur in this bit:

```
public GraphQlSourceBuilderCustomizer federationTransform() {
        DataFetcher entityDataFetcher = env -> {
            List<Map<String, Object>> representations = env.getArgument(_Entity.argumentName);
            return representations.stream()
                    .map(representation -> {
                        if (PRODUCT_TYPE.equals(representation.get("__typename"))) {
                            return queryController.product(UUID.fromString((String) representation.get("id")));
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
```
Obviously that implementation is a bit stupid (the n+1 issue) but the whole context is indeed passed in 'env' so much better than that can be achieved!
