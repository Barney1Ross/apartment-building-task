'use client';
import { Dialog } from 'primereact/dialog';

export default function EntityModal({ visible, header, onHide, children }) {
  return (
    <Dialog header={header} visible={visible} onHide={onHide} modal style={{ width: '32rem' }}>
      {children}
    </Dialog>
  );
}
