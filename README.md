# Cotizar_Bitcoin

Cotizar_Bitcoin is a Java REST API done to get Bitcoin Prices using Spring WebFlux

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.bitcoin.rest_service.RestServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## REST API

### Get list of Bitcoins Prices

#### Request

`GET /getAllBitcoins`

    curl -i -H 'Accept: application/json' http://localhost:8080/getAllBitcoins

#### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    []

### Get Bitcoin Price filtered by timestamp

#### Request

`GET /getBitcoinByTimestamp/{timestamp}`

    curl -i -H 'Accept: application/json' http://localhost:8080/getBitcoinByTimestamp/{timestamp}

#### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    []

### Get Bitcoin Average Price and Max Value Percentage Difference between two Timestamps

#### Request

`GET /getBitcoinBetweenTimestamp/{timestampStart}/{timestampEnd}`

    curl -i -H 'Accept: application/json' http://localhost:8080/getBitcoinBetweenTimestamp/{timestampStart}/{timestampEnd}

#### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    []

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/FedericoUrones/cotizar_bitcoin/blob/main/LICENSE) file.