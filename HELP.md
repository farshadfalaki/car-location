# Getting Started
You can run service by running install.sh

When App starts for first time DataInit reads Polygons data from provided Url and persists in Mongodb.
While using Polygons data, they will be cached. 

Endpoint for getting cars and polygons data are provided in Swagger :
http://localhost:8080/swagger-ui.html

There is job scheduled to run each 30 seconds CarDataFetch and upsert cars' data in Mongodb.  
For upserting I assumed that id of cars don't change, 
maybe vin was a better unique key, but it seems value of vin is changing.

Application is developed using spring reactor in order to avoid using blocking code.

If we run more than one instances,scheduler will be run on each node, to avoid this I've used shedLock.
 
