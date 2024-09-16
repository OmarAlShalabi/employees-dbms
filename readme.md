# Employees Database-Management-System

## Introduction:
This is a small service that covers the following:
* Create, Read, and Update operations on Employee.
* Create, and Read operations on Leave Requests.

## Code Structure:
### Architecture:
Hexagonal-Architecture has been used to model the problem into code in order to achieve the following:
* Create a change-tolerant application where infrastructure could be changed without affecting the domain, and application layers
* Increase testability by having separate technology-agnostic interfaces (so no need to mock any technology-specific construct like a Database connection in order to test a business flow)

### Layers:
The code has been separated into 3 main hexagons:
* **Domain Hexagon**: Core layer, includes domain entities, and exceptions, and is fully isolated from other layers.
* **Application Hexagon**: Orchestration layer on domain entities, communicates with external infrastructure by using output-ports.
* **Infrastructure Hexagon**: Technology-specific constructs like REST, Database, Etc. that maps domain entities to technology-specific entities.

### Config:
Extra config has been added to be able to set business rules, and server-related info.
#### TimeZone:
Setting time-zone for server can be set by overriding following in `application.yml`: 
```
server:
  port: 8082
  shutdown: graceful
  time-zone: "Asia/Ho_Chi_Minh" // change this one to a different ZoneId
  log-Data: true
```
#### Specifications:
some business rules has been put in `application-specs.yml` like:
* types of supported leaves.
* supported currencies.
* overlapping leave types.

## Setup:
* Java Version: 21
* Postgres Version: 16
* Adminer Version: Latest

### Steps:
* Either run docker compose file from IDE or by running
  * `docker compose up -d`
* Run Application from IDE or by running:
  * `mvn clean install`
  * `java -jar JAR_PATH`

## Tests:
This repo contains a couple of functional tests using *Cucumber* and *Test-Containers*
* Test can be found under test-classpath resources, under `features` directory.
* Run tests from IDE with a proper Cucumber Plugin.

## Future work:
* Add Translations to error messages.
* Add Change Log to Employee domain Entity.
* Add Input Adapters (REST, RPC, etc.) in order to expose the functionality to external users.
* Cover all edge cases in cucumber tests.
* Add `CheckStyle` or similar plugin to ensure consistent code-style for future work.
* Add `ArchUnit` tests to ensure the architecture stays consistent for future work.
* Experiment with Garbage Collection Modes (Concurrent, ZGC) to ensure optimal performance.
