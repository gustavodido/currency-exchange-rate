## Currency exchange rate service

#### Application

##### Running 

Clone the repository, build the application:

`./gradlew build`

It will also run all unit and integration tests. Also you can start the Spring Boot application:

`./gradlew bootRun`


##### Tech Stack 

|Technology			| Objective				|
|-------------------|-----------------------|
|Java 8				| Programming language	|
|Gradle				| Build tool 			|
|Lombok				| Library to reduce boiler plate code |
|JUnit				| Testing library |
|Mockito			| Mocking library |
|AssertJ			| Assertion library|
|Spring Boot 		| Application framework |


#### Approach

I have based the entire solution on two main goals: *code quality*, *testability* and *simplicity*.

##### Testability 

All classes (except the infrastructure) are covered by unit testing. Also, there are two integration tests, one to test the RESTFul Api and another one to test the scheduled task workflow. 

##### Simplicity 

In order to keep the solution as simple as possible:

1. The database model is simple as possible. For example, the exchange rate timestamp is the primary key and the solution is using a simple in-memory H2 database.
2. There is no logging or monitoring frameworks in place.
3. The integration tests are running together with unit test. There is not another task/source set for them.
4. There is not a circuit breaker implementation for the external api.
5. Spring events have been used to show a little bit of reactive programming. It could be replaced by a message broker.

_If any of the items above are necessary, please let me know._

#### Design

##### RESTful Api

The service implementatino is very simple with HATEOAS support, it is just delegating the work to the domain queries. Also, there is a controller advice to handle business eceptions.

##### Domain

Inside the domain package thera are all business logic related to the application itself.

###### Command & Query Object Pattern 

The very first thing to notice is that I am not using the traditional Service-Repository pattern. In my 14 years of experience, I have seen (and wrongly designed) applications with this pattern that resulted in:

1. Repositories with business logic.
3. Services without business logic, just with wrappers methods for repositories.
4. Services with hundred (if not thousand) of lines of code.
5. Business logic duplication between services.
6. Poor testability and complex dependency graph.

Therefore, I rather introduce the concept of commands and queries in my applications:

- Command: an action that will change the state of my application or perform any kind of calculation based on several inputs and give a result.
- Query: an action that will query the state of my application.

Every command or query is a really small piece of business logic that can be individually tested, read and understood.

_It does not have the same goal as the CQRS pattern._

##### Scheduling

Inside the _scheduling_ package there is the task that will consistently get the latest exchange rate. In order to show a little bit of reactive programming, the task is not calling the command to save the rate directly, it is triggering an event that will be listened by the command, simulating a loosely coupled Pub/Sub approach.

##### Configuration

It is possible to configure the application in the _yml_ files with spring profiles.








