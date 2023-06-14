#!/bin/bash

export PGPASSWORD=$POSTGRES_PASSWORD

# If database is empty, it is populated from csv files
if [ "$(psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -tAc "\dt" | tr -d '\n')" = "" ]; then
  echo "No tables found. Populating database..."
  echo "Downloading data files..."
  curl https://dev.hsl.fi/citybikes/od-trips-2021/2021-05.csv -L -o 01.csv https://dev.hsl.fi/citybikes/od-trips-2021/2021-06.csv -L -o 02.csv https://dev.hsl.fi/citybikes/od-trips-2021/2021-07.csv -L -o 03.csv
  curl https://opendata.arcgis.com/datasets/726277c507ef4914b0aec3cbcfcbfafc_0.csv -o stations.csv
  echo "All files downloaded successfully."
  JOURNEY_CSV_FILES="01.csv 02.csv 03.csv"
  STATION_CSV_FILE="stations.csv"
  # Create and populate journeys
  psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -c "CREATE TABLE journeys (journey_id SERIAL PRIMARY KEY, departure TIMESTAMP WITHOUT TIME ZONE, return TIMESTAMP WITHOUT TIME ZONE, departure_station_id BIGINT, departure_station_name VARCHAR(255), return_station_id BIGINT, return_station_name VARCHAR(255), distance NUMERIC, duration NUMERIC);"
  echo "Table 'journeys' was created."
  echo "Reading CSV files..."
  for file in $JOURNEY_CSV_FILES
  do
    echo "Reading from CSV $file. Please be patient..."
    psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -c "\COPY journeys(departure, return, departure_station_id, departure_station_name, return_station_id, return_station_name, distance, duration) FROM '$file' DELIMITER ',' CSV HEADER WHERE duration > 10 AND distance > 10;"
  done
  echo "Populated 'journeys' table."

  # Create and populate stations
  psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -c "CREATE TABLE stations (id BIGINT, station_id SERIAL PRIMARY KEY, nimi VARCHAR(255), namn VARCHAR(255), name VARCHAR(255), osoite VARCHAR(255), adress VARCHAR(255), kaupunki VARCHAR(50), stad VARCHAR(50), operaattori VARCHAR(50), kapasiteetti NUMERIC, x NUMERIC, y NUMERIC);"
  echo "Table 'stations' was created."
  echo "Reading CSV file $STATION_CSV_FILE. Please be patient..."
  psql -h db -U $POSTGRES_USER -d $POSTGRES_DB -c "\COPY stations(id, station_id, nimi, namn, name, osoite, adress, kaupunki, stad, operaattori, kapasiteetti, x, y) FROM '$STATION_CSV_FILE' DELIMITER ',' CSV HEADER;"
  echo "Populated 'stations' table."
else
  echo "Database is not empty. Skipping population."
fi