Description
===========
This project is a simple example about How to build GRpc and Http API with Spring.

This is a set of 2 small services that work together to deliver a sequence
of prime numbers up to a given number

## Proxy-service (gRPC Client)
* The `proxy-service` exposes a HTTP endpoint over REST responding to GET /prime/<number>
that continuously streams all prime numbers up to a given <number>
e.g. /prime/17 should return 2,3,5,7,11,13,17

## Prime-number-server (gRPC Server)
* The `prime-number-server` does the actual Prime number calculation - it serves responses continuously over gRPC and uses proper abstractions to communicate failure


Usage
===
### Start by generating stub and server interfaces from .proto file
```
mvn install
```
### Run gRPC Server: 
```
java -jar grpc-server/target/app.jar
```

### From another terminal, run gRPC Client: 
```
java -jar grpc-client/target/app.jar
```

## Requirements

* Java 8 or later
* Default Rest Api Endpoint: [http://localhost:8000/prime/](http://localhost:8000/prime)

* Optionally configure the server port in your `application.yml/properties`. Default port is `6565`.

```
 grpc:
    port: 6565
```
