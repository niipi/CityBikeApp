import React from "react";


function SingleStationPage(station) {
    const selection = station.station;

    return (
            [<h3>This is the station page for {selection.stationName}</h3>,
            <p>Address: {selection.stationAddress}</p>]
    );
}

export default SingleStationPage;