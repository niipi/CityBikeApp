import React from "react";
import JourneyDataGrid from "./Journeys";
import { Box, Paper, Typography } from "@mui/material";


function SingleStationPage(station) {
    const selection = station.station;

    return (
            [<h2> {selection.stationName}</h2>,
            <Paper>
                <Box p={3}>
                    <Typography align="justify">
                        Address: {selection.stationAddress}, {selection.stationCity}<br/>
                        City Bike Service Provider: {selection.serviceProvider}<br/>
                        Capacity: {selection.capacity}<br/>
                    </Typography>
                </Box>
            </Paper>,
            <JourneyDataGrid selectedStationId={selection.stationId}/>]
    );
}

export default SingleStationPage;