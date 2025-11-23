export const buildingImages = ['/images/building1.jpg','/images/building2.jpg', '/images/building3.jpg'];
export const apartmentImages = ['/images/apt1.jpg','/images/apt2.jpg','/images/apt3.jpg','/images/apt4.jpg'];
export const commonImages = ['/images/common1.jpg','/images/common2.jpg','/images/common3.jpg'];

export function pick(arr) {
  if (!arr || arr.length === 0) return null;
  return arr[Math.floor(Math.random() * arr.length)];
}
