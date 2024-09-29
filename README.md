# wiremock-demo

Read https://medium.com/@boddupally.anji/mocking-in-test-automation-ecc0a06ff4d0 for more details.

# Local Development

- Cd into wiremock-demo folder

- Start mysql database from mysql container using the below command

```
docker run \
  --name anji \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=wiremock-demo \
  -e MYSQL_DATABASE=student \
  -v $(pwd)/src/test/resources/testdata/student_test_data.sql:/docker-entrypoint-initdb.d/student_test_data.sql \
  -d mysql:latestdocker run \
  --name anji \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=wiremock-demo \
  -e MYSQL_DATABASE=student \
  -v $(pwd)/src/test/resources/testdata/student_test_data.sql:/docker-entrypoint-initdb.d/student_test_data.sql \
  -d mysql:latest
  
```

- Run below commands to start the app

```
	mvn clean install -DskipTests
	mvn spring-boot:run

 
```

- App will start on port 8080

- Launch to see the APIs

```
http://localhost:8080/swagger-ui/index.html

```

