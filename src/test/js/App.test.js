import { cleanup, render, screen, fireEvent } from '@testing-library/react';
import App from '../../../src/main/js/App.js';


afterEach(() => {
  cleanup();
});

test('Renders header with text "Helsinki City Bike Information"', () => {
  render(<App />);
  const header = screen.getByText(/Helsinki City Bike Information/i);
  expect(header).toBeTruthy();
});

// The following does not work, still trying to figure out how to access DataGrid rows with fireEvent.click
 test('Selecting a station from DataGrid renders SingleStation component', () => {
  render(<App />) ;
  const singleStationPage = screen.findByRole(/SingleStationPage/i)
  const hanasaariStationRow = screen.getByTitle('501');
  fireEvent.click(hanasaariStationRow);
  expect(singleStationPage).toBeInTheDocument();
});