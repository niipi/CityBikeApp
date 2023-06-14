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
- Docker 23.0.5
- Docker Compose 2.17.3

**How to proceed**

1. Clone the project repository from GitHub.
2. Navigate to the root folder.
3. Run `docker compose up`

The database credentials included in `src/main/docker/project.env` are for testing purposes only. Please replace them with your own credentials if you plan to deploy this project.