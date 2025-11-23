'use client';
import { useState, useEffect } from 'react';
import { InputText } from 'primereact/inputtext';
import { Dropdown } from 'primereact/dropdown';
import { InputNumber } from 'primereact/inputnumber';
import { Button } from 'primereact/button';

export default function EntityForm({ type, initial = {}, onCancel, onSave }) {
  const [form, setForm] = useState({ ...initial });

  useEffect(() => setForm({ ...initial }), [initial]);

  const update = (k, v) => setForm(p => ({ ...p, [k]: v }));

  const commonTypes = [
    { label: 'Gym', value: 'GYM' },
    { label: 'Library', value: 'LIBRARY' },
    { label: 'Laundry', value: 'LAUNDRY' }
  ];

  return (
    <div className="p-fluid">
      {type === 'building' && (
        <>
          <label>Name</label>
          <InputText value={form.name || ''} onChange={e => update('name', e.target.value)} />
          <label>Requested Temperature</label>
          <InputNumber value={form.requestedTemperature ?? 20} onValueChange={e => update('requestedTemperature', e.value)} showButtons />
        </>
      )}

      {type === 'apartment' && (
        <>
          <label>Owner Name</label>
          <InputText value={form.ownerName || ''} onChange={e => update('ownerName', e.target.value)} />
          <label>Apartment Number</label>
          <InputText value={form.apartmentNumber || ''} onChange={e => update('apartmentNumber', e.target.value)} />
          <label>Temperature</label>
          <InputNumber value={form.currentTemperature ?? 20} onValueChange={e => update('currentTemperature', e.value)} min={10} max={40} showButtons />
        </>
      )}

      {type === 'common' && (
        <>
          <label>Common Room Type</label>
          <Dropdown options={commonTypes} value={form.type || ''} onChange={e => update('type', e.value)} placeholder="Select type" />
          <label>Temperature</label>
          <InputNumber value={form.currentTemperature ?? 20} onValueChange={e => update('currentTemperature', e.value)} min={10} max={40} showButtons />
        </>
      )}

      <div className="flex justify-content-end gap-2 mt-3">
        <Button label="Cancel" severity="secondary" onClick={onCancel} />
        <Button label="Save" onClick={() => onSave(form)} />
      </div>
    </div>
  );
}
