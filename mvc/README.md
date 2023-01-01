# <p align="center">MVC Pattern</p>

## MVC

MVC is a software architectural pattern. It is included three component named, Model, View and Controller. So that,
users can interact with the Controller and send data to Model component with getting help from View, then View will get
the updates from model directly. The MVC uses for Desktop and Web application. Also, It has two variations named
Model-View-Presenter (MVP) and Model-View-ViewModel (MVVM)

### Model

It is a component included data, logic and rules of the data. Indeed, Model is a data model to represent the
knowledge of domain..

### View

This component is responsible to present the data and get input data from user. Sometimes View is named as a UI
component. The View has no knowledge about the Controller. View should receive the updated model from Model directly
and, it is included the same model, composite of models or a simpler version of a model. To get updated models there are
two solutions as follows.

* Pass the view and action to Controller and, update the view inside the Controller after performing the action
* Use Observation pattern to get notification from Model directly

### Controller

It is a component to apply user request on Model so that it receive data and action from user with getting help from
View, then perform the action on Model. Controller is included two types of action as follows.

* Selection
* Command

The Controller can be included View.

### Advantage

* Separation of concerns (SoC)
* Decoupling
* Testability
* Reusability

<p align="center">

<img src="image/mvc.png" width="500" height="300" />

</p>

## MVP

MVP has three components, Model, View and Presentation. Indeed, Model and View are the same as Model and View in MVC and
Presentation is different component.

### Presentation

It is a component responsible to get input and action from View and, perform the action on Model, also getting models 
from the Model to provide it for View.

<p align="center">
<img src="image/mvp.png" width="500" height="300" />
</p>

## MVVM

### View Model

## Example

[mvc example](./mvc-example)

## References

* [PARC](http://wayback.archive-it.org/10370/20180425071111/http://folk.uio.no/trygver/themes/mvc/mvc-index.html)
* [Trygve M. H. Reenskaug](https://folk.universitetetioslo.no/trygver)
* [Martin Fowler](https://martinfowler.com/eaaDev/uiArchs.html)
* [Potel](http://www.wildcrest.com/Potel/Portfolio/mvp.pdf)

**<p align="center"> [Top](#MVC-Pattern) </p>**


