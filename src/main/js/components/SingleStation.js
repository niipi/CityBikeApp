import React from "react";
import JourneyDataGrid from "./Journeys";


function SingleStationPage(station) {
    const selection = station.station;

    return (
            [<h2> {selection.stationName}</h2>,
            <p>Address: {selection.stationAddress}</p>,
            <JourneyDataGrid selectedStationId={selection.stationId}/>]
    );
}

export default SingleStationPage;