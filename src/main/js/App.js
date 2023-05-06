import './App.css';
import StationDataGrid from './components/Stations';
import BikeAppBar from './components/Appbar';
import SingleStationPage from './components/SingleStation';
import React from 'react';
import { Button } from '@mui/material';

function App() {
  
  const [selectedStation, setSelectedStation] = React.useState(null);
  
  function handleRowSelect(selectedStation) {
    console.log("App.js käsittelee klikkausta");
    setSelectedStation(selectedStation);
    console.log("Valittu asema on ", selectedStation);
    console.log("App.js toimittaa SingleStationPagelle aseman ", selectedStation.stationName, " tiedot");
  }

return (
  <div className="App">
    <header className="App-header">
      <BikeAppBar/>
      {(selectedStation ? (
        [
          <Button variant="contained"
          onClick={()=>{
            setSelectedStation(null);
          }}>Back to stations</Button>,
          <SingleStationPage station={selectedStation}/>
        ]
        ) : (
           <StationDataGrid onRowSelect={handleRowSelect}/>
        )
      )}
</header>
</div>
);
}

export default App;
