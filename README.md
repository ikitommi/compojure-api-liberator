# compojure-api-liberator-sample

Simple demo written in Euroclojure 2015 break to test how to integrate Compojure-api (without coercion) with backends like liberator.

Relates to:

- https://github.com/metosin/compojure-api/issues/121
- https://github.com/metosin/compojure-api/issues/116

## Usage

### Run the application locally

`lein ring server`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright © Tommi Reiman 2015.
