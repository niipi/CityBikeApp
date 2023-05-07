import React from 'react';
import { DataGrid } from '@mui/x-data-grid';


export default function StationDataGrid({onRowSelect}) {
    const[ station, setStation ] = React.useState({totalPages: 0, stations: []});
    const [ paginationModel, setPaginationModel ] = React.useState({
      pageSize: 10,
      page: 0,
    }); 

React.useEffect(()=> {
        fetch(`http://localhost:8080/stations/all?page=${paginationModel.page}`)
        .then((data) => data.json())
        .then((data) => setStation(data))
},[paginationModel]);

function handleRowClick(selectedStation) {
  onRowSelect(selectedStation);
}

const columns = [
  { field: 'stationId', headerName: 'ID', width: 70 },
  { field: 'stationName', headerName: 'Name', width: 150 },
  { field: 'stationAddress', headerName: 'Address', width: 200 },
  { field: 'stationCity', headerName: 'City', width: 100 },
  { field: 'serviceProvider', headerName: 'Service provider', width: 130},
  {
    field: 'capacity',
    headerName: 'Capacity',
    type: 'number',
    width: 90,
  },
];


  return (
    <div className='Content'>
     <h1>City Bike Stations</h1>
     <p>Select a station to view its details</p>
      <DataGrid
        paginationMode="server"
        paginationModel={paginationModel}
        onPaginationModelChange={setPaginationModel}
        disableMultipleRowSelection={true}
        onRowClick={(params) => handleRowClick(params.row)}
        rowCount={station.totalPages*10}
        getRowId={(stations) => stations.stationId}
        rows={station.stations}
        columns={columns}
      />
  
    </div>
  );
}