# <p align="center">MVC Example</p>

## Prerequisites

* Java 17
* Maven 3

## Build

```bash
mvn clean package -DskipTests=true
```

## Test

```bash
mvn  test
```

## Run

In order to run desktop version

```bash
java -cp ./target/mvc-example.jar com.tutorial.article.Main "form"
```

In order to run console version

```bash
java -cp ./target/mvc-example.jar com.tutorial.article.Main "console"
```

**<p align="center">[Top](#MVC-Example)</p>**