import React from "react";
import JourneyDataGrid from "./Journeys";
import { Box, Grid, Paper, Typography, Radio, RadioGroup, FormControlLabel, FormControl, FormLabel } from "@mui/material";
import Map from "./Map";

function SingleStationPage(station) {
    const selection = station.station;

    // Props for Map component
    const coords = {
        // Longitude and latitude were mixed up by developer, to be fixed
        latitude: selection.longitude,
        longitude: selection.latitude
    };

    // Fetch journey counts from backend
    const [departureJourneyCount, setDepartureJourneyCount] = React.useState(0);
    const [returnJourneyCount, setReturnJourneyCount] = React.useState(0);

    React.useEffect(() => {
        fetch(`http://localhost:8080/journeys/count?departureStationId=${selection.stationId}`)
        .then((data) => data.json())
        .then((data) => setDepartureJourneyCount(data));
    }, [selection.stationId]);

    React.useEffect(() => {
        fetch(`http://localhost:8080/journeys/count?returnStationId=${selection.stationId}`)
        .then((data) => data.json())
        .then((data) => setReturnJourneyCount(data));
    }, [selection.stationId]);

    // Event handler for toggling between returning and departing journeys of the station being viewed
    const [value, setValue] = React.useState("departure"); // Set to departing journeys by default
    const handleChange = (event) => {
        setValue(event.target.value);
    }

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
                                Departing journeys: {departureJourneyCount.countJourneys}<br/>
                                Returning journeys: {returnJourneyCount.countJourneys}<br/>
                                <p><br/>
                                <FormControl>
                                    <FormLabel id="demo-controlled-radio-buttons-group">Toggle journey direction</FormLabel>
                                    <RadioGroup
                                        row
                                        aria-labelledby="demo-controlled-radio-buttons-group"
                                        name="controlled-radio-buttons-group"
                                        value={value}
                                        onChange={handleChange}
                                    >
                                        <FormControlLabel value="departure" control={<Radio />} label="Departing" />
                                        <FormControlLabel value="return" control={<Radio />} label="Returning" />
                                    </RadioGroup>
                                </FormControl>
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
            <JourneyDataGrid returningOrDeparting={value} selectedStationId={selection.stationId}/>]
    );
}

export default SingleStationPage;