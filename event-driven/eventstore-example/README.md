# <p align="center">Event Driven Example</p>

## Prerequisites

* [Java 17](https://www.oracle.com/de/java/technologies/downloads/)
* [Maven 3](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Eventstore Database](https://www.eventstore.com/)

## Build

```bash
mvn clean package -DskipTests=true
```

## Test

```bash
mvn test
```

## Dashboard

When the eventstore is running it is possible to access its dashboard by browsing the URL for localhost
[http://localhost:2113](http://localhost:2113).

**<p align="center"> [Top](#event-driven-example) </p>**


