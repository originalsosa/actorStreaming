Spark Streaming Template
=========================

Goal of this template is to showcase spark streaming with Akka actors being the source of the input stream.

### Features:
* Spark Streaming
* Akka Actors

## Requirements
* JDK 8 (e.g. [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html));

## To build "FatJar"
To build jar, call:
```
./sbt.sh assembly

## Run tests
To run tests, call:
```
./sbt.sh test
```