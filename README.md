Smart Campus API (5COSC022W Coursework)
## 1. API Overview
This project implements a RESTful API for managing Rooms, Sensors, and Sensor Readings using JAX-RS (Java EE 8).
The system supports:
•	Room creation and deletion
•	Sensor registration and linking to rooms
•	Filtering sensors by type
•	Managing historical sensor readings
•	Sub-resource handling for readings
•	Structured error handling
•	Request and response logging
The API uses in-memory data structures (HashMap, ArrayList) and does not use any database, as required by the coursework.
## 2. Tech Stack
•	Java EE 8 (javax.ws.rs)
•	Jersey (JAX-RS implementation)
•	Apache Tomcat 9
•	Maven (WAR project)
•	In-memory storage (HashMap, ArrayList)

## 3. Build and Run Instructions
1.	Open project in NetBeans
2.	Right-click project → Clean and Build
3.	Run on Apache Tomcat
4.	Open:
http://localhost:8080/SmartCampusAPI/api/v1

## 4. Sample curl Commands
### 1. Discovery Endpoint
curl http://localhost:8080/SmartCampusAPI/api/v1
### 2. Create Room
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/rooms \
-H "Content-Type: application/json" \
-d "{\"id\":\"R1\",\"name\":\"Lab\",\"capacity\":30}"
### 3. Get Rooms
curl http://localhost:8080/SmartCampusAPI/api/v1/rooms
### 4. Create Sensor
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors \
-H "Content-Type: application/json" \
-d "{\"id\":\"S1\",\"type\":\"temperature\",\"status\":\"ACTIVE\",\"currentValue\":25.0,\"roomId\":\"R1\"}"
### 5. Filter Sensors
curl "http://localhost:8080/SmartCampusAPI/api/v1/sensors?type=temperature"
### 6. Add Sensor Reading
curl -X POST http://localhost:8080/SmartCampusAPI/api/v1/sensors/S1/readings \
-H "Content-Type: application/json" \
-d "{\"value\":26.5,\"timestamp\":1710000000}"


## 5. Report Answers
### Part 1
#### Lifecycle of JAX-RS Resources
The resource class used in this JAX-RS project follows the request-based lifecycle. This means that every incoming request generates an instance of the resource. The lifecycle benefits thread safety since no instance will share any data. The project relies on in-memory storage such as the HashMap in static form to persist data across requests. 
#### HATEOAS (Hypermedia)
HATEOAS helps the API to direct client navigation through hyperlinks provided in the responses. For example, this project has endpoints that can be used by the client to discover other resources such as room and sensor resources.
### Part 2
#### IDs vs Objects
While sending only the IDs saves data space and bandwidth, an extra request would have to be made by the client to fetch the object. Sending the whole object, on the other hand, increases data traffic but makes it easier to communicate with the client. 
#### DELETE Request Idempotence
DELETE requests are idempotent, since each successive request leaves the system in the exact same final state. The initial DELETE request deletes the resource, while every other attempt results in a 404 response.
### Part 3
#### @Consumes(MediaType.APPLICATION_JSON)
This will make sure that only JSON data is accepted by the API. In case of any other data, JAX-RS will give an HTTP 415 unsupported media type error.
#### QueryParam and PathParam
Query parameters work well when searching as they are not mandatory. Path parameters are for resource identification.
### Part 4
#### Locator for Sub-Resources
This pattern ensures that each nested resource resides in its own class. This way, one avoids having large classes and depicts hierarchical structures such as sensors and their readings clearly.
#### Updating Current Value
With each new reading, the currentValue of the sensor is modified to represent the latest reading.
### Part 5
#### HTTP 422 vs HTTP 404
HTTP 422 is suitable for the scenario where the request is good but the data is invalid (e.g., no such roomId). HTTP 404 is applicable when the resource that was sought out does not exist.
#### Security Vulnerabilities with Stack Trace Information
The exposure of stack trace information will show internal details like the names of classes and file paths, which attackers can abuse. This is mitigated using a global exception mapper that returns sanitized results.
#### Logging Filters
The use of JAX-RS filters makes it possible to log all incoming and outgoing requests. It helps avoid redundant logging and provides uniformity.
