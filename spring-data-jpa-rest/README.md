## 启动工程
`mvn spring-boot:run`

## REST API

https://stackoverflow.com/questions/34583515/spring-jpa-rest-one-to-many

TODO: 一对多的关系没有成功建立。

```
curl -i -X POST -H "Content-Type:application/json" \
-d '{"address": "frodo@baggins.com"}' \
http://localhost:8080/emails

curl -i -X POST -H "Content-Type:application/json" \
-d '{"address": "frodo.baggins@gmail.com"}' \
http://localhost:8080/emails

curl -i -X POST -H "Content-Type:application/json" \
-d '{"firstName": "Frodo", "lastName": "Baggins", "emails":["http://localhost:8080/emails/1", "http://localhost:8080/emails/2"]}' \
http://localhost:8080/people
```

```
curl -i -X POST -H "Content-Type:application/json" \
-d '{  "firstName" : "Frodo",  "lastName" : "Baggins"}' \
http://localhost:8080/people

curl -i -X POST -H "Content-Type:application/json" \
-d '{"address": "frodo@baggins.com"}' \
http://localhost:8080/emails

curl -i -X POST -H "Content-Type:application/json" \
-d '{"address": "frodo.baggins@gmail.com"}' \
http://localhost:8080/emails

curl -i -X PATCH -H "Content-Type: text/uri-list" -d "http://localhost:8080/emails/1
http://localhost:8080/emails/2" http://localhost:8080/people/1/emails
```

## H2 database

Console: http://localhost:8080/h2-console/

JDBC URL: jdbc:h2:mem:testdb

User: sa

Password: 


