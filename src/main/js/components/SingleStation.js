import React from "react";
import JourneyDataGrid from "./Journeys";
import { Box, Grid, Paper, Typography } from "@mui/material";
import Map from "./Map";

function SingleStationPage(station) {
    const selection = station.station;
    const coords = {
        // Longitude and latitude were mixed up by developer, to be fixed
        latitude: selection.longitude,
        longitude: selection.latitude
    };

    // Radio buttons for toggling returning and departing journeys to be added
    return (
            [<h2> {selection.stationName}</h2>,
            <Paper>
                <Grid container spacing={2}>
                    <Grid item xs={8}>
                        <Box p={3}>
                            <Typography align="justify">
                                Address: {selection.stationAddress}, {selection.stationCity}<br/>
                                City Bike Service Provider: {selection.serviceProvider}<br/>
                                Capacity: {selection.capacity}<br/>
                                <p><br/>
                                radio button: show departing journeys<br/>
                                radio button: show returning journeys
                                </p>
                            </Typography>
                        </Box>
                    </Grid>
                    <Grid item xs={4}>
                        <Box>
                            <Map coords={coords} display_name={selection.stationName}/>
                        </Box>
                    </Grid>
                </Grid>
            </Paper>,
            <JourneyDataGrid selectedStationId={selection.stationId}/>]
    );
}

export default SingleStationPage;