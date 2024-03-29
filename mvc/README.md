# <p align="center">MVC Pattern</p>

## MVC

MVC is a software architectural pattern. It includes three components named Model, View, and Controller. So that users
can interact with the Controller and send input to it with the help of View. Then View will get the updates directly
from Model. The MVC is generally used for desktop and web applications. Also, it has two variations named
Model-View-Presenter (MVP) and Model-View-ViewModel (MVVM).

### Model

A Model is a component that includes data, logic, and rules for the data. Indeed, it is a data model to represent the
knowledge of a domain.

### View

This component is responsible for presenting the data and getting input data from the users. Sometimes, View is named as
a UI component. The View has no knowledge about the Controller. View should receive the updated data directly from the
Model. View includes the same model as Model component, a composite of models, or a simpler version of a model. To get
updated models, there are two solutions, as follows:

* Use a reference of View in the Controller
* Use Observation pattern to get notification from Model directly

### Controller

It is a component that applies user requests to the Model so that it receives data and actions from the user with the
help of View and then performs the action on the Model. Controller includes two types of action as follows:

* Selection (an action to read data)
* Command (an action to manipulate data)

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

Model-View-Presenter (MVP) is a software architectural pattern used in developing graphical user interface (GUI)
applications. It's a variation of the Model-View-Controller (MVC) pattern and aims to provide a clear separation of
concerns between the presentation logic and the business logic.

MVP has three components: model, view, and presentation. Indeed, Model and View are the same as the Model and View in
MVC, Presentation is a different component.

### Presentation

It is a component responsible for getting inputs and actions from the View and performing the actions on Model. Also
getting updated models from the Model to provide them for View.

<p align="center">
<img src="image/mvp.png" width="500" height="300" />
</p>

## MVVM

Model-View-ViewModel (MVVM) is a software architectural pattern used in the development of modern user interfaces. It's
a variation of the Model-View-Controller (MVC) pattern and is widely used in developing applications with a graphical
user interface (GUI). MVVM is also referred to as "Model-View-Binder".

MVVM has three components: Model, View, and ViewModel. Indeed, Model and View are the same as the Model and View in MVC
and ViewModel is a different component. Also, the View communicates with the ViewModel through data binding, but has no
direct access to the Model.

### View Model

It's the intermediary component between the Model and the View. The ViewModel exposes the data from the Model in a
format that can be easily consumed by the View. It also handles the user interactions from the View and updates the
Model accordingly.

<p align="center">
<img src="image/mvvm.png" width="500" height="300" />
</p>

## Example

* [MVC example](./mvc-example)
* [MVP example](./mvp-example)
* [MVVM example](./mvvm-example)

## References

* [MVC: PARC](http://wayback.archive-it.org/10370/20180425071111/http://folk.uio.no/trygver/themes/mvc/mvc-index.html)
* [MVC: Trygve M. H. Reenskaug](https://folk.universitetetioslo.no/trygver)
* [MVP: Martin Fowler](https://martinfowler.com/eaaDev/uiArchs.html)
* [MVP: Potel](http://www.wildcrest.com/Potel/Portfolio/mvp.pdf)
* [MVVM: Microsoft (xamarin)](https://learn.microsoft.com/en-us/xamarin/xamarin-forms/enterprise-application-patterns/mvvm)
* [MVVM: Microsoft (dotnet)](https://learn.microsoft.com/en-us/dotnet/architecture/maui/mvvm)
* [ChatGPT](https://chat.openai.com/chat)

**<p align="center"> [Top](#mvc-pattern) </p>**


