# <p align="center">Event Driven</p>

## Even

<p>

Event is a fact related to a domain in order to represent states of a system. Events are immutable, it means they are
created once and never be changed, also stored as a chronological in an event storage.

</p>

## Event Sourcing

<p>

It is a pattern to store data as events. Indeed, different states of a transaction, will be stored in a time based
sequence. Event sourcing mostly is used for audition.

</p>

## Stream

Stream means all events related to a transaction. It includes full history of the changes.

## Event broker
* Transactional Outbox
* Database triggers
* Transaction log tailing
* Event portal
* Topics
* Event mesh
* Deferred execution
* Eventual consistency
* Choreography
* Command Query Responsibility Segregation


## Example
[Eventstore database](eventstore-example)


## Reference

[eventstore.com](https://www.eventstore.com/event-sourcing)

**<p align="center"> [Top](#event-driven) </p>**


