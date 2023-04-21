import './App.css';
import StationDataGrid from './Stations.js';
import JourneyDataGrid from './Journeys';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <JourneyDataGrid/>
      </header>
    </div>
  );
}

export default App;
