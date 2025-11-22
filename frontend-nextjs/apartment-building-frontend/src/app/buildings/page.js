'use client';
import { useEffect, useState } from 'react';
import { getBuildings } from '../../services/buildingApi';
import BuildingCard from '../../components/BuildingCard';

export default function BuildingsPage() {
  const [buildings, setBuildings] = useState([]);

  useEffect(() => {
    fetchBuildings();
  }, []);

  async function fetchBuildings() {
    try {
      const res = await getBuildings();
      console.log(res)
      setBuildings(res || []);
    } catch (e) { console.error(e); }
  }

  return (
    <div className="p-grid">
      {buildings.length === 0 && <p>No buildings yet. Click init on backend to create one.</p>}
      {buildings.map(b => (
        <div key={b.id} className="p-col-12 p-md-6 p-lg-4">
          <BuildingCard building={b} />
        </div>
      ))}
    </div>
  );
}
