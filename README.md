# Spring Boot RabbitMQ

This is just a sandbox for playing around with RabbitMQ via Spring Boot.

## Sending Test Data

### Immediately

```
curl -X POST -H "Content-Type: text/plain" -d "test" "http://localhost:8080/data"
```

### Delayed

```
curl -X POST -H "Content-Type: text/plain" -d "test" "http://localhost:8080/data?delay=5000"
```