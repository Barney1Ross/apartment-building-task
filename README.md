Apartment Building Task – Full Stack Implementation

This repository contains the complete implementation of the Apartment
Building Task. All mandatory requirements have been completed along with
a few additional enhancements to improve the overall functionality.

Project Overview

The project includes:

- Backend built using Java and Spring Boot (Mavenas the build tool) 
- Frontend built using Next.js (React) 
- CRUD operations for all required entities 
- Polling mechanism added to display live room temperature and heating/cooling status 
- Reusable components created on the frontend 
- Dummy images added to improve UI -

Git branching followed (dev-backend and dev-frontend) and merged into
the main branch through pull requests

Ports
-   Backend runs on port 8080
-   Frontend runs on port 3001

Running the Project

Backend (Spring Boot): 
1. Navigate to the backend folder 
2. Run: 
   mvn clean install
   mvn spring-boot:run
   
   Or run the packaged JAR:
   java -jar apartmentbuilding-0.0.1-SNAPSHOT.jar
   
3. Backend will start on http://localhost:8080

Frontend (Next.js): 
1. Navigate to the frontend folder 
2. Install dependencies: npm install 
3. Start the project: npm run dev 
4. Frontend will start on http://localhost:3001

Notes:
Screenshots and additional documentation can be added later if required.
