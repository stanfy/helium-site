Server for testing Hellium features
-----------------------------------


**Development:**

To run and test local server:

1. Uncomment endpoint in api specification file.
2. Run gradle command with `./gradlew appengineRun`
3. Run Helium tests with `./gradlew checkApiBeh`


**Deploy:**

To upload and deploy new server intance on CloudEngine run 

`./gradlew appengineUpdate`