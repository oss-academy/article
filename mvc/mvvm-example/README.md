# <p align="center">MVVM Example</p>

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
java -cp ./target/mvvm-example.jar com.tutorial.mvvm.Main "desktop"
```

In order to run console version

```bash
java -cp ./target/mvvm-example.jar com.tutorial.mvvm.Main "console"
```

In order to run multi view

```bash
java -cp ./target/mvvm-example.jar com.tutorial.mvvm.Main "all"
```

**<p align="center">[Top](#MVVM-Example)</p>**