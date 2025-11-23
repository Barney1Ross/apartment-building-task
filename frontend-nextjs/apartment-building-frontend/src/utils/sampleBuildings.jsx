export const sampleBuildings = [
  {
    requestedTemperature: 23,
    apartments: [
      { ownerName: "Alice", apartmentNumber: "201", currentTemperature: 21 },
      { ownerName: "Bob", apartmentNumber: "202", currentTemperature: 25 },
    ],
    commonRooms: [
      { type: "GYM", currentTemperature: 22 },
      { type: "LIBRARY", currentTemperature: 24 },
    ],
  },
  {
    requestedTemperature: 26,
    apartments: [
      { ownerName: "Charlie", apartmentNumber: "301", currentTemperature: 28 },
      { ownerName: "Dave", apartmentNumber: "302", currentTemperature: 26 },
    ],
    commonRooms: [
      { type: "LAUNDRY", currentTemperature: 25 },
      { type: "GYM", currentTemperature: 27 },
    ],
  },
  {
    requestedTemperature: 24,
    apartments: [
      { ownerName: "Eve", apartmentNumber: "401", currentTemperature: 22 },
      { ownerName: "Frank", apartmentNumber: "402", currentTemperature: 24 },
    ],
    commonRooms: [
      { type: "LIBRARY", currentTemperature: 23 },
      { type: "GYM", currentTemperature: 24 },
    ],
  },
  {
    requestedTemperature: 25,
    apartments: [
      { ownerName: "Grace", apartmentNumber: "501", currentTemperature: 24 },
      { ownerName: "Hank", apartmentNumber: "502", currentTemperature: 25 },
    ],
    commonRooms: [
      { type: "LAUNDRY", currentTemperature: 23 },
      { type: "LIBRARY", currentTemperature: 26 },
    ],
  },
  {
    requestedTemperature: 22,
    apartments: [
      { ownerName: "Ivy", apartmentNumber: "601", currentTemperature: 21 },
      { ownerName: "Jack", apartmentNumber: "602", currentTemperature: 22 },
    ],
    commonRooms: [
      { type: "GYM", currentTemperature: 21 },
      { type: "LAUNDRY", currentTemperature: 22 },
    ],
  },
];
