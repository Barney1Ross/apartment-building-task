// apartmentApi.js
import axios from "axios";

const BASE = process.env.NEXT_PUBLIC_API_BASE || "http://localhost:8080/api/apartments";

export async function getApartmentById(id) {
    const res = await axios.get(`${BASE}/${id}`);
    return res?.data;
}

export async function updateApartment(id, payload) {
    const res = await axios.put(`${BASE}/${id}`, payload); 
    return res?.data;
}

export async function deleteApartment(id) {
    const res = await axios.delete(`${BASE}/${id}`);
    return res?.data;
}
