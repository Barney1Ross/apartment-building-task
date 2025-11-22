'use client';
import Link from 'next/link';

export default function BuildingCard({ building }) {
  return (
    <div className="p-card" style={{ padding: 16 }}>
      <h3>Building #{building.id}</h3>
      <p>Requested Temp: {building.requestedTemperature}</p>
      <p>Apartments: {building.apartments?.length ?? 0}</p>
      <p>Common Rooms: {building.commonRooms?.length ?? 0}</p>
      <Link href={`/buildings/${building.id}`}><button>Open</button></Link>
    </div>
  );
}
