'use client';

import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';

import { Menubar } from 'primereact/menubar';
import ToastProvider from '@/components/ToastProvider';
import { ConfirmDialog } from 'primereact/confirmdialog';
import { Tooltip } from 'primereact/tooltip';
import { initBuilding, initSampleBuildings } from '@/services/buildingApi';
import { sampleBuildings } from '@/utils/sampleBuildings';

export default function RootLayout({ children }) {

  const initializeDefault = async () => {
    try {
      await initBuilding();
      window.location.reload();
    } catch (err) {
      console.error(err);
      alert('Failed to initialize default building');
    }
  };

  const addSampleBuildings = async () => {
    try {
      await initSampleBuildings(sampleBuildings);
      window.location.reload();
    } catch (err) {
      console.error(err);
      alert('Failed to add sample buildings');
    }
  };

  const items = [
    { label: 'Buildings', icon: 'pi pi-home', url: '/buildings' },
    {
      label: 'Apartment Building App',
      disabled: false,
      className: 'font-bold px-4'
    },
    {
      label: 'Initialize Default',
      command: initializeDefault,
    },
    {
      label: 'Add Sample Buildings',
      command: addSampleBuildings
    }
  ];

  return (
    <html lang="en">
      <body className="">
        <ToastProvider />
        <ConfirmDialog />
        <Tooltip />
        <div className=' border-1 p-1' >
          <Menubar model={items} className="sticky top-0 z-10" />
        </div>
        <main className="p-3 overflow-auto" style={{ height: '80vh'}}>
          {children}
        </main>
      </body>
    </html>
  );
}
