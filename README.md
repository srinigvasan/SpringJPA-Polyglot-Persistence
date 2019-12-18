# Reviews API
Supports operations for writing reviews and listing reviews for a product but with no sorting or filtering.

### Prerequisites
MySQL needs to be installed and configured. Instructions provided separately.

### Getting Started
* Configure the MySQL Datasource in application.properties.
* Add Flyway scripts in src/main/resources/db/migration.
* Define JPA Entities and relationships.
* Define Spring Data JPA Repositories.
* Add tests for JPA Repositories.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)


###  Install instructions.

a) create new database before starting springboot application

# update the below properties in application.properties with your database details , username , password.

       * spring.datasource.url=jdbc:mysql://localhost:3306/reviewapi
       * spring.datasource.username=root
       * spring.datasource.password=pwd

#update the below property for mangoDB connection string

      spring.data.mongodb.uri=mongodb://course3:course3@localhost:27017/course3

# Run the mvn springboot project as below

  mvn spring-boot:run
