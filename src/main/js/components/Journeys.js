import React from 'react';
import { DataGrid } from '@mui/x-data-grid';


export default function JourneyDataGrid({returningOrDeparting, selectedStationId}) {
    const[ journey, setJourney ] = React.useState({totalPages: 0, journeys: []});
    const [ paginationModel, setPaginationModel ] = React.useState({
      pageSize: 10,
      page: 0,
    });

React.useEffect(()=> {
        fetch(`/journeys/all?${returningOrDeparting}StationId=${selectedStationId}&page=${paginationModel.page}`)
        .then((data) => data.json())
        .then((data) => setJourney(data))
},[returningOrDeparting, selectedStationId, paginationModel]);

const columns = [
  { field: 'journeyId', headerName: 'Journey ID', width: 100 },
  { field: 'departureStationName', headerName: 'Departure station', width: 150 },
  { field: 'returnStationName', headerName: 'Return station', width: 150 },
  { field: 'distance', headerName: 'Distance (m)', type: 'number', width: 100 },
  { field: 'duration', headerName: 'Duration (s)', type: 'number', width: 100},
];

  return (
    [
     <h3>Journeys</h3>,
      <DataGrid
        paginationMode="server"
        paginationModel={paginationModel}
        onPaginationModelChange={setPaginationModel}
        disableRowSelectionOnClick
        rowCount={journey.totalPages*10}
        getRowId={(journeys) => journeys.journeyId}
        rows={journey.journeys}
        columns={columns}
      />
    ]
  );
}