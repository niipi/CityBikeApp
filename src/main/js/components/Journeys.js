import React from 'react';
import { DataGrid } from '@mui/x-data-grid';


export default function JourneyDataGrid() {
    const[ journey, setJourney ] = React.useState({totalPages: 0, journeys: []});
    const [ paginationModel, setPaginationModel ] = React.useState({
      pageSize: 10,
      page: 0,
    });


React.useEffect(()=> {
        fetch(`http://localhost:8080/journeys/all?page=${paginationModel.page}`)
        .then((data) => data.json())
        .then((data) => setJourney(data))
},[paginationModel]);

console.log(journey)
console.log(journey.journeys);

const columns = [
  { field: 'journeyId', headerName: 'ID', width: 70 },
  { field: 'departureStationName', headerName: 'Departure station', width: 150 },
  { field: 'returnStationName', headerName: 'Return station', width: 150 },
  { field: 'distance', headerName: 'Distance (m)', type: 'number', width: 100 },
  { field: 'duration', headerName: 'Duration (s)', type: 'number', width: 100},
];

  return (
    <div style={{ height: 500, width: "60%" }}>
     <h1>City Bike Journeys</h1>
      <DataGrid
        paginationMode="server"
        paginationModel={paginationModel}
        onPaginationModelChange={setPaginationModel}
        rowCount={journey.totalPages*10}
        getRowId={(journeys) => journeys.journeyId}
        rows={journey.journeys}
        columns={columns}
      />
    </div>
  );
}