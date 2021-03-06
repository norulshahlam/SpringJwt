# Spring Security with JWT - Version 2 - Generate JWT

### Use Case

  this will be in 3 parts:
  1. setup roles & secure service with basic auth
  2. generate jwt token
  3. verify jwt token
  4. refresh token

    A user will have basic details like name, username, id, password.
    at the same time it will also have roles / multiple roles.

        
    +----+-----------------------+----------+----------+
    | id | name                  | password | username |
    +----+-----------------------+----------+----------+
    |  5 | John Travolta         | 1234     | john     |
    |  6 | Will Smith            | 1234     | will     |
    |  7 | Jim Carry             | 1234     | jim      |
    |  8 | Arnold Schwarzenegger | 1234     | arnold   |
    +----+-----------------------+----------+----------+


    +----+------------------+
    | id | name             |
    +----+------------------+
    |  1 | ROLE_USER        |
    |  2 | ROLE_MANAGER     |
    |  3 | ROLE_ADMIN       |
    |  4 | ROLE_SUPER_ADMIN |
    +----+------------------+

        
    +---------+----------+
    | user_id | roles_id |
    +---------+----------+
    |       5 |        1 |
    |       6 |        2 |
    |       7 |        3 |
    |       8 |        4 |
    |       8 |        3 |
    |       8 |        1 |
    +---------+----------+

    The ouput will be something like this:

    {
    "id": 8,
    "name": "Arnold Schwarzenegger",
    "username": "arnold",
    "password": "1234",
    "roles": [
      {
        "id": 4,
        "name": "ROLE_SUPER_ADMIN"
      },
      {
        "id": 3,
        "name": "ROLE_ADMIN"
      },
      {
        "id": 1,
        "name": "ROLE_USER"
      }
    ]
  }

### setup docker for db

    docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=mydb --env MYSQL_PASSWORD=root --env MYSQL_USER=admin --name localhost --publish 3306:3306 mysql:8.0


### create project with dependencies

    web
    jpa
    mysql connector
    commons-lang
    lombok
    jaxb-api
    security
    jjwt

### Entity, Repo, Service, Controller.

  User & Role entity

### add load schema & data in db on Spring start

    Use CommandLineRunner to add initial data into db


### configure datasource

    spring.datasource.url=jdbc:mysql://localhost:3306/mydb?useLegacyDatetimeCode=false&serverTimezone=UTC
    spring.datasource.username=admin
    spring.datasource.password=root
    server.port=9091
        
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.properties.hibernate.format.sql=true
    spring.jpa.hibernate.ddl-auto=create
    spring.sql.init.mode=always

### configure servlet context

    server.servlet.context-path=/jwt

### test initial data in browser /api/getUsers

  On any browser, go to: localhost:8081/api/users

  since we use spring security dependency, it will secure by default. on runtime, it will generate default password.  use 'user' as your default username. if success, u will be able to see all users

### test initial data in db

    `run mysql in cli using docker`
    docker exec -it localhost bash

    `connect to mysql`
	mysql -u admin -proot
	
    `test`
	use mydb;
	show tables;
    select * from user;
    select * from role;
    select * from user_roles;

    `stop & remove all running proceses`
	docker rm $(docker ps -a -q) -f






