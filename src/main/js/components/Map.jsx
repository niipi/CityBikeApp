import React from "react";
import { renderToString } from 'react-dom/server';
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from 'leaflet';
import { SvgIcon } from "@mui/material";
import LocationOnOutlinedIcon from '@mui/icons-material/LocationOnOutlined';

export default function Map({ coords, display_name }) {
  const { latitude, longitude } = coords;

   const locationIcon = L.divIcon({
    html: renderToString(
      <SvgIcon viewBox="0 0 24 24" style={{ fontSize: 30 }}>
        <LocationOnOutlinedIcon />
      </SvgIcon>
    ),
    className: 'custom-marker-icon'
  });

  function MapView() {
    let map = useMap();
    map.setView([latitude, longitude], map.getZoom());
    //Sets geographical center and zoom for the view of the map
  }

  return (
    <MapContainer
      className="map"
      center={[latitude, longitude]}
      zoom={10}
      scrollWheelZoom={true}
    >
      <TileLayer
        attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> 
        contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Marker icon={locationIcon} position={[latitude, longitude]}>
        <Popup>{display_name}</Popup>
      </Marker>
      <MapView/>
    </MapContainer>
  );
}