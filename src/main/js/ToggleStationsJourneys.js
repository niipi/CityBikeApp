import { useState } from 'react';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import JourneyDataGrid from './Journeys';
import StationDataGrid from './Stations';

export default function ToggleButtons() {
  const [selectedPage, setPage] = useState('stations');

  const handlePage = (event, value) => {
    setPage(value);
  };

  return (
    <div>
        <ToggleButtonGroup
          value={selectedPage}
          exclusive
          onChange={handlePage}
          aria-label="toggle-stations-journeys"
        >
          <ToggleButton value="stations" aria-label="stations data grid">
            Stations
          </ToggleButton>
          <ToggleButton value="journeys" aria-label="journeys data grid">
            Journeys
          </ToggleButton>
        </ToggleButtonGroup>

        { selectedPage === 'stations' && <StationDataGrid/> }
        { selectedPage === 'journeys' && <JourneyDataGrid/> }
    </div>
  );
}