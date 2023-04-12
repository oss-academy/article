# <p align="center">MVP Example</p>

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
java -cp ./target/mvp-example.jar com.tutorial.mvp.Main "desktop"
```

In order to run console version

```bash
java -cp ./target/mvp-example.jar com.tutorial.mvp.Main "console"
```

**<p align="center">[Top](#MVP-Example)</p>**