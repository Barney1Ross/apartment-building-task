// commonRoomApi.js
import axios from "axios";

const BASE = process.env.NEXT_PUBLIC_API_BASE || "http://localhost:8080/api/common-rooms";

export async function getCommonRoomById(id) {
    const res = await axios.get(`${BASE}/${id}`);
    return res?.data;
}

export async function updateCommonRoom(id, payload) {
    const res = await axios.put(`${BASE}/${id}`, payload);
    return res?.data;
}

export async function deleteCommonRoom(id) {
    const res = await axios.delete(`${BASE}/${id}`);
    return res?.data;
}
