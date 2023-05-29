FROM openjdk:17-alpine
EXPOSE 8080
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
COPY . ./
RUN chmod +x /populate.sh
RUN apk add --no-cache postgresql-client
RUN apk add bash
CMD until PGPASSWORD=$POSTGRES_PASSWORD psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -c '\l'; do \
      echo "Waiting for the database to be ready..."; \
      sleep 1; \
    done \
    && bash /populate.sh \
    && java -jar application.jar