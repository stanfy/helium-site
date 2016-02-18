Server for testing Hellium features
-----------------------------------

**Development:**

To run development server execute `./gradlew appengineRun` . 

This will start server on *localhost:8080* .

 
**Testing**

By default when you run `gw checkAPiBehaviour` it executes all tests on *localhost:8080* endpoint.

To use CloudEngine endpoint you should pass a parameter `endpoint`. 

For example, `gw chApiBeh -Pendpoint=http://.....` 


**Deploy**

To upload and deploy new server intance on CloudEngine run 

`./gradlew appengineUpdate`