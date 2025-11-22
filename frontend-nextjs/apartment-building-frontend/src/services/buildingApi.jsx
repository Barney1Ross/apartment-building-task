import axios from 'axios';
const BASE = process.env.NEXT_PUBLIC_API_BASE || 'http://localhost:8080/api/buildings';

/*  data initialization in case of volatile db  */

// export async function initBuilding() {
//     const res = await axios.post(`${BASE}/initialize`);
//     return res.data;
// }

export async function getBuildings() {
    const res = await axios.get(`${BASE}`);
    return res.data;
}

export async function getBuildingById(id) {
    const res = await axios.get(`${BASE}/${id}`);
    return res.data;
}


export async function updateBuildingTemperature(id, payload) {
    const res = await axios.put(`${BASE}/${id}/temperature`, null,
        {
            params:
            {
                requestedTemperature: payload.requestedTemperature
            }
        });
    return res.data;
}


export async function getBuildingApartments(buildingId) {
    const res = await axios.get(`${BASE}/${buildingId}/apartments`);
    return res?.data;
}

export async function addApartmentToBuilding(buildingId, apartment) {
    const res = await axios.post(`${BASE}/${buildingId}/apartments`, apartment);
    return res?.data;
}

export async function removeApartmentFromBuilding(buildingId, apartmentId) {
    const res = await axios.delete(`${BASE}/${buildingId}/apartments/${apartmentId}`);
    return res?.data;
}

export async function getBuildingCommonRooms(buildingId) {
    const res = await axios.get(`${BASE}/${buildingId}/common-rooms`);
    return res?.data;
}

export async function addCommonRoomToBuilding(buildingId, room) {
    const res = await axios.post(`${BASE}/${buildingId}/common-rooms`, room);
    return res?.data;
}

export async function removeCommonRoomFromBuilding(buildingId, roomId) {
    const res = await axios.delete(`${BASE}/${buildingId}/common-rooms/${roomId}`);
    return res?.data;
}