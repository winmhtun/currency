# Getting started
This repository is a basic spring boot application which showcases use of controller, service, repository, model architecture with little application of currency exchange usecases.
# Before you run
- import data to your local mysqldb using `currency.sql` located under `src/main/resources`
- edit mysql connection at application.properties and make sure you have started mysql service
# Starting the project
- run `./mvnw install` to start downloading required dependencies . This will run the test as well. If you would like to run tests alone, you can type `./mvnw test`
- run `./mvnw spring-boot:run` to start running the project
# Documentation
+ After project has started running, you can visit at swagger site to start trying apis. `your-local-path-to-application:portNumber/swagger-ui.html`
  - This is the example link `http://localhost:5000/swagger-ui.html`


