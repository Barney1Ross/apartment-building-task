'use client';
import { useEffect, useState } from 'react';
import Link from 'next/link';
import { getBuildings } from '@/services/buildingApi';
import EntityCard from '@/components/EntityCard';
import { pick, buildingImages } from '@/utils/randomImages';

export default function BuildingsPage() {
  const [list, setList] = useState([]);
  const [imagesMap, setImagesMap] = useState({});

  useEffect(() => { fetchAll(); }, []);

  async function fetchAll() {
    const res = await getBuildings();
    setList(res || []);

    // assign stable images for new buildings
    const newMap = {};
    (res || []).forEach(b => {
      if (!imagesMap[b.id]) {
        newMap[b.id] = pick(buildingImages);
      }
    });
    setImagesMap(prev => ({ ...prev, ...newMap }));
  }

  return (
    <div className="grid h-auto">
      {list.map(b => (
        <div key={b.id} className="col-12 md:col-6 lg:col-4">
          <Link href={`/buildings/${b.id}`}>
            <EntityCard
              type="building"
              data={b}
              image={imagesMap[b.id]} // stable image
            />
          </Link>
        </div>
      ))}
    </div>
  );
}
