'use client';
import Image from 'next/image';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { getTemperatureStatus } from '@/utils/handlers';

export default function EntityCard({ type, data, image, onEdit, onRemove, onDelete }) {
    const title = type === 'building'
        ? `Building #${data.id}`
        : data.ownerName ? `Apt ${data.apartmentNumber || data.id}` : data.type || `Room ${data.id}`;

    const subtitle = type === 'building'
        ? `Requested: ${data.requestedTemperature ?? '-'}°C`
        : `Temp: ${Number(data.currentTemperature || 0).toFixed(1)}°C`;

    return (
        <Card title={title} subTitle={subtitle} className="p-mb-3 shadow-6" >
            {image && (
                <div style={{ width: '100%', height: 140, position: 'relative', marginBottom: 8 }}>
                    <Image src={image} alt="img" fill style={{ objectFit: 'cover', borderRadius: 6 }} />
                </div>
            )}

            <div>
                {type === 'building' ? (
                    <>
                        <div>Apartments: {data.apartments?.length ?? 0}</div>
                        <div>Common Rooms: {data.commonRooms?.length ?? 0}</div>
                    </>
                ) : (
                    <>
                        {data.ownerName && <div>Owner: {data.ownerName}</div>}
                        {data.apartmentNumber && <div>Number: {data.apartmentNumber}</div>}
                        {data.type && <div>Type: {data.type}</div>}
                        {data.currentTemperature !== undefined && (
                            <p className="text-sm">
                                Status: {data.heatingEnabled ? "Heating" : data.coolingEnabled ? "Cooling" : "Idle"}
                            </p>
                        )}
                    </>
                )}
            </div>

            <div className="flex justify-content-end gap-2" style={{ marginTop: 12 }}>
                {onEdit && <Button icon="pi pi-pencil" className="p-button-rounded p-button-sm" onClick={() => onEdit(data)} data-pr-tooltip="Edit" />}
                {onRemove && <Button icon="pi pi-link" className="p-button-rounded p-button-sm p-button-warning" onClick={() => onRemove(data)} data-pr-tooltip="Remove from building" />}
                {onDelete && <Button icon="pi pi-trash" className="p-button-rounded p-button-sm p-button-danger" onClick={() => onDelete(data)} data-pr-tooltip="Delete" />}
            </div>
        </Card>
    );
}
