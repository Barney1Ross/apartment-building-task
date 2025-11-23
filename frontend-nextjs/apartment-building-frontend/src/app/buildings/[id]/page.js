'use client';
import React, { useEffect, useState, useRef } from 'react';
import { InputNumber } from 'primereact/inputnumber';
import { Button } from 'primereact/button';
import { confirmDialog } from 'primereact/confirmdialog';

import EntityCard from '@/components/EntityCard';
import EntityModal from '@/components/EntityModal';
import EntityForm from '@/components/EntityForm';
import { pick, apartmentImages, commonImages } from '@/utils/randomImages';
import { callWithToast, showSuccess, showError, unlinkThenDelete } from '@/utils/handlers';

import {
  getBuildingById,
  updateBuildingTemperature,
  addApartmentToBuilding,
  addCommonRoomToBuilding,
  removeApartmentFromBuilding,
  removeCommonRoomFromBuilding
} from '@/services/buildingApi';

import { updateApartment, deleteApartment } from '@/services/apartmentApi';
import { updateCommonRoom, deleteCommonRoom } from '@/services/commonRoomApi';

export default function BuildingDetail({ params }) {
  const { id } = React.use(params);
  const [building, setBuilding] = useState(null);
  const [temp, setTemp] = useState(20);

  const [apartmentImagesMap, setApartmentImagesMap] = useState({});
  const [commonImagesMap, setCommonImagesMap] = useState({});
  const [modalVisible, setModalVisible] = useState(false);
  const [modalType, setModalType] = useState(null); // 'apartment'|'common'
  const [modalMode, setModalMode] = useState('create'); // 'create'|'edit'
  const [editData, setEditData] = useState({});

  useEffect(() => { load(); }, []);

  useEffect(() => {
    const fetchData = async () => {
      const res = await getBuildingById(id);
      setBuilding(res);
    };

    fetchData(); // initial fetch

    const interval = setInterval(fetchData, 2000); // poll every 2 seconds
    return () => clearInterval(interval);
  }, [id]);

  async function load() {
    const b = await getBuildingById(id);
    setBuilding(b);
    setTemp(b?.requestedTemperature ?? 20);
  }


  useEffect(() => {
    if (!building) return;

    const newAptImages = {};
    building.apartments?.forEach(a => {
      if (!apartmentImagesMap[a.id]) {
        newAptImages[a.id] = pick(apartmentImages);
      }
    });
    setApartmentImagesMap(prev => ({ ...prev, ...newAptImages }));

    const newCommonImages = {};
    building.commonRooms?.forEach(r => {
      if (!commonImagesMap[r.id]) {
        newCommonImages[r.id] = pick(commonImages);
      }
    });
    setCommonImagesMap(prev => ({ ...prev, ...newCommonImages }));
  }, [building]);



  const wrapped = (fn, msg) => callWithToast(fn, msg);

  async function onSetTemp() {
    await wrapped(updateBuildingTemperature, 'Requested temperature updated')(id, { requestedTemperature: temp });
    load();
  }

  function openCreate(t) {
    setModalType(t);
    setModalMode('create');
    setEditData(t === 'apartment' ? { ownerName: '', apartmentNumber: '', currentTemperature: 20 } : { type: 'GYM', currentTemperature: 20 });
    setModalVisible(true);
  }

  function openEdit(item, t) {
    setModalType(t);
    setModalMode('edit');
    setEditData(item);
    setModalVisible(true);
  }

  async function onSave(form) {
    if (modalMode === 'create' && modalType === 'apartment') {
      await wrapped(addApartmentToBuilding, 'Apartment added')(id, form);
    } else if (modalMode === 'create' && modalType === 'common') {
      await wrapped(addCommonRoomToBuilding, 'Common room added')(id, form);
    } else if (modalMode === 'edit' && modalType === 'apartment') {
      await wrapped(updateApartment, 'Apartment updated')(form.id, form);
    } else if (modalMode === 'edit' && modalType === 'common') {
      await wrapped(updateCommonRoom, 'Common room updated')(form.id, form);
    }
    setModalVisible(false);
    load();
  }

  function confirmRemove(item, type) {
    confirmDialog({
      message: 'Remove from building (keeps entity)?',
      header: 'Confirm',
      icon: 'pi pi-info-circle',
      accept: async () => {
        if (type === 'apartment') {
          await wrapped(removeApartmentFromBuilding, 'Removed from building')(id, item.id);
        } else {
          await wrapped(removeCommonRoomFromBuilding, 'Removed from building')(id, item.id);
        }
        load();
      }
    });
  }

  function confirmDelete(item, type) {
    confirmDialog({
      message: 'Delete permanently (will unlink first then delete)?',
      header: 'Confirm Delete',
      icon: 'pi pi-exclamation-triangle',
      accept: async () => {
        try {
          if (type === 'apartment') {
            // unlink then delete
            await unlinkThenDelete({
              unlinkFn: removeApartmentFromBuilding,
              deleteFn: deleteApartment,
              unlinkArgs: [id, item.id],
              deleteArgs: [item.id]
            });
          } else {
            await unlinkThenDelete({
              unlinkFn: removeCommonRoomFromBuilding,
              deleteFn: deleteCommonRoom,
              unlinkArgs: [id, item.id],
              deleteArgs: [item.id]
            });
          }
          showSuccess('Deleted successfully');
        } catch (err) {
          showError('Delete failed');
        }
        load();
      }
    });
  }

  if (!building) return <p>Loading...</p>;

  return (
    <div className="p-1">
      <div className="flex justify-content-between align-items-center mb-3">
        <div>
          <h2 className="m-0">Building #{building.id}</h2>
          <div>Requested: {building.requestedTemperature}°C</div>
        </div>

        <div className="flex gap-2">
          <InputNumber value={temp} onValueChange={(e) => setTemp(e.value)} showButtons min={10} max={40} />
          <Button label="Set" onClick={onSetTemp} />
        </div>
      </div>

      <div className="mb-2">
        <Button label="Add Apartment" icon="pi pi-plus" onClick={() => openCreate('apartment')} className="mr-2" />
        <Button label="Add Common Room" icon="pi pi-plus" severity="secondary" onClick={() => openCreate('common')} />
      </div>

      <h3>Apartments</h3>
      <div className="grid h-30rem overflow-auto">
        {building.apartments?.map(a => (
          <div key={a.id} className="col-12 md:col-6 lg:col-4">
            <EntityCard
              type="apartment"
              data={a}
              image={apartmentImagesMap[a.id]} // stable now
              onEdit={() => openEdit(a, 'apartment')}
              onRemove={() => confirmRemove(a, 'apartment')}
              onDelete={() => confirmDelete(a, 'apartment')}
            />
          </div>
        ))}
      </div>

      <h3 className="mt-4">Common Rooms</h3>
      <div className="grid">
        {building.commonRooms?.map(r => (
          <div key={r.id} className="col-12 md:col-6 lg:col-4">
            <EntityCard
              type="common"
              data={r}
              image={commonImagesMap[r.id]}
              onEdit={() => openEdit(r, 'common')}
              onRemove={() => confirmRemove(r, 'common')}
              onDelete={() => confirmDelete(r, 'common')}
            />
          </div>
        ))}
      </div>

      <EntityModal visible={modalVisible} header={modalMode === 'edit' ? 'Edit' : 'Add'} onHide={() => setModalVisible(false)}>
        <EntityForm type={modalType === 'apartment' ? 'apartment' : 'common'} initial={editData} onCancel={() => setModalVisible(false)} onSave={onSave} />
      </EntityModal>
    </div>
  );
}
