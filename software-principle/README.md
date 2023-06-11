# <p align="center">Software Principles</p>

## Hollywood Principle

<p align="justify">

The principle is based on **`Don't call us, we'll call you`** sentence. It is used for object-oriented design to reduce
dependencies between components. According to this principle higher-level components should take control of lower-level
components and call them according to the conditions.

</p>

<p align="justify">

This principle is often used in framework or libraries in the context of inversion of control (IoC) and dependency
injection (DI) also, in event-driven architecture.

</p>

### Tip

In order to apply Hollywood principle to RESTful API, use Richardson Maturity Model (RMM) and implementation of that for
different languages. (such as spring HATEOAS library of spring framework for Java)

### Advantage

* loose coupling
* more flexibility and extensibility
* more modularity and maintainability

## DRY

<p align="justify">

DRY is an abbreviation of **`Don't Repeat Yourself`**, therefore it means the codebase should not have duplicate
functionality. My impression of DRY as a simple word is encapsulation.

There are some solutions to achieve the goal based on SRP (Single Responsibility Principle) and OCP (Open Close
Principle).

</p>

### Tip

<p align="justify">

The ways of encapsulation are helpers, utility classes, concrete classes also, if the objects follow inheritance then it
is possible to use inheritance for encapsulation.

</p>

### Advantage

* reusability
* modularity
* use Configuration and Data-driven approaches

## YAGNI

<p align="justify">

YAGNI is abbreviation of **`You Ain’t Gonna Need It`**. It is a development principle behind extreme programming that
suggests not adding functionality unless it is necessary.

</p>

### Advantage

* keep the codebase as simple as possible
* keep codebase lean

## KISS

<p align="justify">

KISS stands for **`Keep It Simple, Stupid`** which reportedly coined by Clarence Leonard "Kelly" Johnson lead
engineer at the Lockheed Martin. It is a software principle that prefers simplicity over unnecessary complexity.

</p>

### Advantage

* clear and readable code
* minimal dependencies

## References

* [Richardson Model: Martin Fowler](https://martinfowler.com/articles/richardsonMaturityModel.html)
* [DRY: The Pragmatic Programmer](https://media.pragprog.com/titles/tpp20/dry.pdf)
* [YAGNI: Extreme programming installed](https://archive.org/details/extremeprogrammi00jeff/page/n289/mode/2up)
* [YAGNI: Martin Fowler](https://martinfowler.com/bliki/Yagni.html)
* [KISS: Wikipedia](https://en.wikipedia.org/wiki/KISS_principle#In_software_development)
* [KISS: Biographical Memoir](http://www.nasonline.org/publications/biographical-memoirs/memoir-pdfs/johnson-clarence.pdf)
* [ChatGPT](https://chat.openai.com/chat)

**<p align="center"> [Top](#software-principles) </p>**





