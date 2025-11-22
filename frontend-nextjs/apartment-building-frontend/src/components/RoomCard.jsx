'use client';

export default function RoomCard({ room }) {
  const status = room.heatingEnabled ? 'Heating' : room.coolingEnabled ? 'Cooling' : 'Idle';
  return (
    <div className="p-card" style={{ padding: 12 }}>
      <h4>{room.ownerName ? `Apt ${room.apartmentNumber || room.id}` : room.type || `Room ${room.id}`}</h4>
      {room.ownerName && <p>Owner: {room.ownerName}</p>}
      <p>Temp: {room.currentTemperature?.toFixed(1)}</p>
      <p>Status: <strong>{status}</strong></p>
    </div>
  );
}
