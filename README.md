# CityBikeApp
**Work In Progress**
This is a simple web application for viewing city bike journey information in the Helsinki capital area.

The purpose of this project is to achieve personal learning goals when it comes to building full-stack web applications and as a pre-assignment for the 2023 Solita Dev Academy application.

Station and journey datasets are available [here](https://github.com/solita/dev-academy-2023-exercise). The three CSVs containing journey information are stored in the "journeys" table of the database, and the city bike station dataset is stored in a table called "stations".

**Current progress**

Missing features include but are not limited to
- performance optimization
- displaying average distance of journeys for a single station
- displaying top 5 departure and return stations for a single station
- most tests

Unfinished features include but are not limited to
- everything to do with the user interface

Important to notice about commit history: project was rebased on May 12th due to a secret project.env file having eluded .gitignore limitations. Commit ebe2932 was originally made on April 11th.

## Technologies
Backend: Java, SpringBoot

Frontend: JavaScript, React

Database: PostgreSQL

This is a Dockerized application: only Docker, Java and Maven are required to run this project locally. The database is included in the Docker container and frontend dependencies are included in the Maven build.

SpringBoot was chosen due to personal interest in Java development. I had heard a lot of negative things about SpringBoot annotation in the past and was intrigued by the challenge. However, it seems most issues are easily googleable and documentation has been sufficient at the learning level I am currently at.

React was chosen due to its popularity and to get better acquainted with JavaScript syntax. In hindsight, it might have been more sensible to first go through Helsinki University full stack open MOOC for React basics and only then attempt this project. As it stands, this learning experience raised more questions than it answered.

# Installation
**Prerequisites**

Before installing the project, please make sure your local machine has the following dependencies installed:
- Java 20
- Maven 3.9.1
- Docker 23.0.5
- Docker Compose 2.17.3

**How to proceed**

1. Clone the project repository from GitHub.
2. In the root directory, open `config-overrides.js` and replace root directory address on line 31 with your project root directory.
3. Navigate to src/main/docker.
4. Create a `project.env` file with the editor of your choice and enter the following:
```
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/database
SPRING_DATASOURCE_USERNAME=[your username]
SPRING_DATASOURCE_PASSWORD=[your password]
SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Database configuration for PostgreSQL (running in container called "city-bike-db")
POSTGRES_USER=[your username, same as above]
POSTGRES_PASSWORD=[your password, same as above]
POSTGRES_DB=database
```

5. Copy all four dataset CSVs provided [here](https://github.com/solita/dev-academy-2023-exercise) to the src/main/docker folder. Next:

**6. UNIX systems:**

1. Run `chmod -x ./rebuild.sh`

2. Run `mv Helsingin_ja_Espoon_kaupunkipyöräasemat_avoin.csv stations.csv`

3. Run `./rebuild.sh` to build and launch the project.
 

**6. Windows:**

1. Run `rename "Helsingin_ja_Espoon_kaupunkipyöräasemat_avoin.csv" "stations.csv"` 

2. Run `rebuild.bat` to build and launch the project.
