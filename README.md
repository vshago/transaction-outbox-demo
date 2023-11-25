# transaction-outbox-demo

Start PostgreSQL:
```
docker run -d -p 5432:5432 --name db \
    -e POSTGRES_USER=admin \
    -e POSTGRES_PASSWORD=password \
    -e POSTGRES_DB=demo \
    postgres:12-alpine
```

Run service:
```
./gradlew bootRun
```

Make a request:
```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"productId":"10","quantity":"2"}' \
  http://localhost:8080/order
```