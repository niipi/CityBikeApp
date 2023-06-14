# This is a two-stage build to reduce image size. First stage is building the project.
FROM docker.io/maven:3.9.2-eclipse-temurin-17-alpine as build
RUN mkdir -p {app/src,app/static} \
    && apk update \
    && apk add --no-cache postgresql-client bash libgcc libstdc++
COPY src/ /app/src
COPY static/ /app/static
COPY pom.xml package.json config-overrides.js /app/
RUN cd /app/ \
    && mvn clean package \
    && mv ./target/*.jar ./target/application.jar

# We copy only the necessary elements for deployment. The rest of the build stage is discarded in the final image.
FROM docker.io/openjdk:17-alpine
EXPOSE 8080
COPY --from=build /app/target/application.jar /
COPY --from=build /app/src/main/docker/populate.sh /
RUN chmod +x /populate.sh && apk add --no-cache postgresql-client bash curl
CMD until PGPASSWORD=$POSTGRES_PASSWORD psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -c '\l'; do \
      echo "Waiting for the database to be ready..."; \
      sleep 2; \
    done \
    && bash /populate.sh \
    && echo -e "\n*************************************************\n*                                               *\n*    City Bike Application started.             *\n*    Open in browser: http://localhost:8080     *\n*                                               *\n*************************************************\n" \
    && java -jar /application.jar