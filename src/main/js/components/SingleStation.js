import React from "react";
import { Container } from "@mui/material";


function SingleStationPage(station) {

console.log("SingleStationPage sai propin ", station);

console.log(station.station.stationId)
console.log("Nyt aseman nimi on ", station.station.stationName);

    return (
        <Container>
        <div>
            <h3>This is the station page for {station.station.stationName}</h3>
            <p>Address: {station.station.stationAddress}</p>
        </div>
    </Container>
    );
}

export default SingleStationPage;