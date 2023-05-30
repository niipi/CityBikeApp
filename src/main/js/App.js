import './App.css';
import StationDataGrid from './components/Stations';
import SingleStationPage from './components/SingleStation';
import React from 'react';
import { Button } from '@mui/material';

function App() {
  
  const [selectedStation, setSelectedStation] = React.useState(null);
  
  function handleRowSelect(selectedStation) {
    setSelectedStation(selectedStation);
  }

return (
  <div className="App">
    <header className="App-header">
      <h1>Helsinki City Bike Information</h1>
      <div className='Content'>
      {(selectedStation ? (
        [ <Button variant="contained"
          onClick={()=>{
            setSelectedStation(null);
          }}>Back to stations</Button>,
          <SingleStationPage station={selectedStation}/>
        ]
        ) : (
           <StationDataGrid onRowSelect={handleRowSelect}/>
        )
      )}
      </div>
</header>
</div>
);
}

export default App;
