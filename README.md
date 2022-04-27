# Product Service Demo
## Goal
The purpose of this microservice is to execute some specific logic based on a series of steps (provided by another microservice called Journey Manager) and then according to the type of the step, this will be delivered to a frontend component via a publisher or a certain logic will be executed.
## How does it do it ?
First, this microservice will start an instance of a journey (a group of steps, a workflow), once the instance is running, this microservice will subscribe to a publisher provided by Journey Manager in order to get all the steps of the started journey.
Second, once a step is received, it will be analyzed to determine the type of the step, if the step is a SERVICE type, a business logic will be executed, if the step is a USER type, then it will be forwarded (via a publisher) to the frontend to request all the information asked by the step.  