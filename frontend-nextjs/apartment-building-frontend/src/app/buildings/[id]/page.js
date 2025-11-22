'use client';
import React, { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { getBuildingById, updateBuildingTemperature } from '../../../services/buildingApi';
import RoomCard from '../../../components/RoomCard';

export default function BuildingDetail({ params }) {
  const { id } = React.use(params);
  const [building, setBuilding] = useState(null);
  const [temp, setTemp] = useState('');
  const router = useRouter();

  useEffect(() => { fetchBuilding(); }, []);

  async function fetchBuilding() {
    const b = await getBuildingById(id);
    setBuilding(b);
    setTemp(b?.requestedTemperature ?? '');
  }

  async function onUpdateTemp() {
    await updateBuildingTemperature(id, { requestedTemperature: parseFloat(temp) });
    fetchBuilding();
  }

  return (
    <div>
      {!building ? <p>Loading...</p> : (
        <>
          <h2>Building #{building.id}</h2>
          <div style={{ marginBottom: 12 }}>
            <label>Requested Temp: </label>
            <input type="number" value={temp} onChange={e => setTemp(e.target.value)} />
            <button onClick={onUpdateTemp}>Set</button>
          </div>

          <h3>Apartments</h3>
          <div className="p-grid">
            {building.apartments?.map(a => (
              <div key={a.id} className="p-col-12 p-md-6 p-lg-4">
                <RoomCard room={a} />
              </div>
            ))}
          </div>

          <h3>Common Rooms</h3>
          <div className="p-grid">
            {building.commonRooms?.map(r => (
              <div key={r.id} className="p-col-12 p-md-6 p-lg-4">
                <RoomCard room={r} />
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
}
