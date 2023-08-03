# User-Management-System

Helidon MP application that uses the dbclient API with MySQL database.

## Build and run


This example requires a MySQL database, start it using docker:

```
docker run --rm --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=pokemon -e MYSQL_USER=user -e MYSQL_PASSWORD=password  mysql:5.7
```

With JDK17+
```bash
mvn package
java -jar target/User-Management-System.jar
```

## Exercise the application
```
curl -X GET http://localhost:8080/simple-greet
{"message":"Hello World!"}
```

