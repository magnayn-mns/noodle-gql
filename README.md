# GraphQL Noodling

Noodling around with GraphQL federation.

NBB: THIS IS ALL FAKE DEMO DATA THAT ISN'T CONSISTENT, and the schemas written here shouldn't be considered as accurate as to what anything ought to look like!

## Run it:
Some federation-able GraphQL things, spring boot + apollo

Individual Domains
- noodle-basket 
- noodle-price
- noodle-range

Run each of these in the IDE (e.g: IntelliJ)

- noodle-federation
```npm start``` once the above are up and running (maybe ```npm install``` first)

## What you can do:

You how have 3 separate 'domains' running their own GraphQL.

### Basket
Go to http://localhost:8080/playground to see a 'basket'. You can run a query like

```graphql
query{
  	basket(id:"basket-1") {
    
    contents {
      quantity
     
      product {
        id
      }
    }
     currentQuote {
        id
      }
    }
}
```

Note how you can get the quantities and IDs of products in your bag - and the ID of the currently active 'quote', but very little else.

### Range (ought to be 'product' really)

Go to http://localhost:8082/playground to see a product. Take one of the IDs from the basket, and have a look:

```graphql
query {
  product(id:"product-3") {
    name, colour, GTIN
  }
}
```

It can tell you a lot about what something is, but nothing about what it costs..

### Price

Go to http://localhost:8081/playground to see a quote

You can take the ID of the quote from the 1st step and use it here (quote-):

```graphql
query{
    quote(id:"quote-1") {
        id
        creationDate
        currency
        lineItems {
            id,

            nowUnitPrice
            product {
                id
            }
        }
        totals {
            grandTotal
        }
    }
}
```

See how you can find the prices of line items now, but price can't describe what the things are, and you have to know the ID of the quote to get this information.


# Federation
Start the federation server in noodle-federation if you haven't already. Note how little actual code is in there.

You can either navigate to http://localhost:4000/graphql which will access via Apollo's own tooling - or, simply take one of your playgrounds pages and change the URL at the top to 'http://localhost:4000/graphql'.

Now, we can execute a query such as this:

```graphql
query {
  product(id:"product-3") {
    name, colour, GTIN,
    listPrice {
      markedPrice
    }
  }
}
```

We can get all the product details, including the price, even though the product domain is unaware that there's even such a _concept_ as 'list price'.

We can do this:
```graphql
query{
  	basket(id:"basket-1") {
    
    contents {
      quantity
     
      product {
        id
        name
        listPrice{
          markedPrice
        }
      }
    }
    
  }
}
```

Our basket can magically now tell us the name of the products, and their "list price" (NB: not what the customer might pay - the quote price is something different).

The basket may now chain-through to get the quote, and that quote can also now contain product names (The caller neither knows-nor-cares about quote IDs):

```graphql
query{
    basket(id:"basket-1") {
    
     currentQuote {
        id
      	creationDate
      
      lineItems {
        product{
          name
          colour
        }
        quantity {
          numberOfUnits
        }
      }
    }
  }
}
```


# Where the magic happens

What's going on is apollo is producing a 'supergraph' which is the union of all of the sub-graphs that have been defined, along with the knowledge about which supplier provides that information.

This is why it is important that the entity names (e.g: Product, Quote, Price) are consistent. You will find 3 definitions for 'product':

### Product Domain
The product domain says "I can tell you all sorts of attributes about products, and I can join them up if you tell me an ID"

```graphql
type Product @key(fields: "id"){
    id: ID!
    name: String
    colour: String
    GTIN: String
}
```

### Basket Domain
Basket domain: "I can't tell you terribly much about products other than I have to refer to them and they have IDs becuse they're an entity" :

```graphql
type Product @key(fields: "id") {
    id: ID!
}
```
### Price Domain
Price domain: "I also don't know much about products - but I do maintain a list price for them, so I can tell you about that":
```graphql
type Product @key(fields: "id") {
    id: ID!

    listPrice: Price
}
```
### Supergraph

Thus when you're doing this:

```graphql
query {
    product(id:"product-3") {
        name, colour, GTIN,
        listPrice {
            markedPrice
        }
    }
}
```

Product domain is called (by apollo), and returns what it knows, then price domain is called with "Please give me product('product-3') list price", which it then stitches together.

## N+1
In the case of, say, basket joining up with product name, a naive implementation would be "call product N times by ID to get each product name". Hands up if you've seen this happen in REST APIs..

Fortunately, it doesn't do that - it bunches them up as a "here's some entities and paths to resolve - you figure it out". (This boils down to your endpoint being supplied with a set of (Type, ID, scope) tuples to resolve)

In the implementation below, you can see it's doing a rather stupid "loop through each one in turn" - a more sensible approach (for something
backed by an actual database) would be "batch fetch all of these items in an IN clause".



## Implementation

The federation action appears to occur in this bit (it's likely different in micronaut but it'll be similar) :

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

There's a bunch of other pieces that are likely implementation specific.
