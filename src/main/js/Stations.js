import { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';


export default function StationDataGrid() {
    const[ station, setStation ] = useState({totalPages: 0, stations: []});
    const [ page, setPage ] = useState(0);

useEffect(()=> {
        fetch('http://localhost:8080/stations/all')
        .then((data) => data.json())
        .then((data) => setStation(data))
},[page]);

console.log(station)

const columns = [
  { field: 'stationId', headerName: 'ID', width: 70 },
  { field: 'stationName', headerName: 'Name', width: 130 },
  { field: 'stationAddress', headerName: 'Address', width: 130 },
  { field: 'stationCity', headerName: 'City', width: 70 },
  { field: 'serviceProvider', headerName: 'Service provider', width: 130},
  {
    field: 'capacity',
    headerName: 'Capacity',
    type: 'number',
    width: 90,
  },
];

  return (
    <div style={{ height: 400, width: '100%' }}>
     <h1>City Bike Stations</h1>
      <DataGrid
        getRowId={(stations) => stations.stationId}
        rows={station.stations}
        columns={columns}
        page={page}
        pageSize={5}
        rowsPerPageOptions={[10]}
        pagination
        onPageChange={(newPage) => setPage(newPage)}
      />
    </div>
  );
}