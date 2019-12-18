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



###Setup Enable auth in mongodb
* start mongo deamon with out authentication
brew services start mongodb-community@4.2

* add user for course3 database
use admin
db.createUser(
  {
    user: "myUserAdmin",
    pwd: passwordPrompt(), // or cleartext password
    roles: [ { role: "userAdmin", db: "course3" },{ role: "readWrite", db: "course3" }]
  }
)
* start mongo deamon with auth enabled
  mongod --auth --port 27017 --dbpath /usr/local/var/mongodb


* connect with new User added
      mongo --port 27017  --authenticationDatabase "course3" -u "course3" -pcourse3



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
