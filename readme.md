# Some playground for Spring Boot and RabbitMQ

### Prerequisites

- Java 1.8
- RabbitMQ installed (default installation)

### Running

```bash
./mvnw spring-boot:run
```


### Endpoints

Welcome message:
```
GET http://localhost:8080/
```
```bash
curl http://localhost:8080/
```
Last message:
```
GET http://localhost:8080/read
```
```bash
curl http://localhost:8080/read
```

Send message:
```
POST http://localhost:8080/queue
```
```bash
curl -X POST -d "id=x" http://localhost:8080/queue
```

Send 10000 messages:
```bash
GET http://localhost:8080/sendmore
```
```bash
curl http://localhost:8080/sendmore
```

### Notes

Spring queue listener is enabled by default, so `/read` would return "no more messages". 
All sent messages will apear in output application console. 