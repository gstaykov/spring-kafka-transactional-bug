## What this project needs to run:
1. Kafka on localhost:9092
2. MySQL on localhost:3306

## Quick environment config with docker
For more easy setup you can use the `docker-compose.yml` file in this project and just run:
```
docker-compose up
```

This will start:
- kafka on port 9092, 9093
- kafka-ui on port 9999
- mysql on port 3306  (username: root & password: root)

## How to reproduce the issue:
1. Start the project
2. Hit this endpoint many times (>10). Doesn't need to be asynchronous.
```
'http://localhost:8080/test' 
```
OR 
```
curl -X 'GET' \
  'http://localhost:8080/test' \
  -H 'accept: */*'
```

- Expected result: is no records to be found in DB and Kafka. Everything should be rollback.
- Actual result: there are some messages in kafka.