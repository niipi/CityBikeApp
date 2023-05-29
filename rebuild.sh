#!/bin/sh
mvn clean package -DskipTests
cp target/citybikeapp-0.0.1-SNAPSHOT.jar src/main/docker
cd src/main/docker
docker compose build
docker compose up
