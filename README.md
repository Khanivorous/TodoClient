# Application Overview
This is a simple spring client application. It uses RestTemplate to do a client call to the https://jsonplaceholder.typicode.com endpoint
and returns the Todo object at a given id.

This project is designed to showcase a few simple ways of testing Spring applications that use RestTemplate.

## Running the application
If you want to run the app using docker, first, build the application jar using `gradle bootJar`, then
run the [docker-compose](docker-compose.yml) file with `docker compose up` can be used to run locally and play around with.

You can access the swagger ui at http://localhost:8080/swagger-ui/index.html where you can do simple get requests.

## Tests

You will notice the tests that spin up the actual application use two different property files with 2 different urls set. This is to demo how we can write various tests for various environments easily within spring.

- [TodoIntegrationTest](src/test/java/com/khanivorous/todo/TodoIntegrationTest.java)
  - This spins up the application and tests without any mocks
  - Takes the url properties from [application-integration-test.properties](src/test/resources/application-integration-test.properties)
- [TodoApplicationTests](src/test/java/com/khanivorous/todo/TodoApplicationTests.java)
  - This spins up the application and mocks the rest response it receives using springs `MockRestServiceServer` 
  - Takes the url properties from [application-test.properties](src/test/resources/application-test.properties)
- [TodoClientImplMockServerTest](src/test/java/com/khanivorous/todo/TodoClientImplMockServerTest.java)
  - This is a unit test of the TodoClientImpl class. The application does _not_ spin up
  - This uses springs `MockRestServiceServer` to mock the rest response from the RestTemplate call
- [TodoClientImplUnitTest](src/test/java/com/khanivorous/todo/TodoClientImplUnitTest.java)
  - This is a unit test of the TodoClientImpl class. The application does _not_ spin up
  - This just uses Mockito to Mock the response from the RestTemplate call
