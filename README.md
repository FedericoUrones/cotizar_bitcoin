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
## Side note

When talking about `timestamp` in the parameters, we're talking seconds timestamp. It can be taken from here
- [TimeStamp Converter](https://www.unixtimestamp.com/)
- [List of Bitcoins Prices service](#get-list-of-bitcoins-prices)

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

    [
        {
            "curr1": "BTC",
            "curr2": "USD",
            "lprice": 18586.8,
            "timeStampSeconds": 1607551523
        },
        {
            "curr1": "BTC",
            "curr2": "USD",
            "lprice": 18586.8,
            "timeStampSeconds": 1607551532
        },
        {
            "curr1": "BTC",
            "curr2": "USD",
            "lprice": 18586.8,
            "timeStampSeconds": 1607551542
        }
    ]

#### Description

Retrieves all Bitcoins Prices stored in the application.

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

    {
        "curr1": "BTC",
        "curr2": "USD",
        "lprice": 18586.8,
        "timeStampSeconds": 1607551523
    }

#### Description

Filters Bitcoins Prices based on input parameter: timestamp (in seconds). Application gets a new Bitcoin Price every 10 seconds, so if you want a Bitcoin Price in a timestamp, application will try to filter Bitcoin Prices in a range of 10 seconds.

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

    {
        "average": 18579.54375,
        "percentBetweenAverageAndMax": "0%"
    }

#### Description

Filters Bitcoins Prices based on two input parameters: timestampStart and timestampEnd (in seconds). Application will filter all Bitcoin Pricess between those two timestamps and return following values:
- average: Average Bitcoin Price between timestamps
- percentBetweenAverageAndMax: Percentage difference between Max Bitcoin Price (all series) and average Bitcoin Price (mentioned above)

## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/FedericoUrones/cotizar_bitcoin/blob/main/LICENSE) file.