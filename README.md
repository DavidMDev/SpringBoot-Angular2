# Spring-Boot / Angular2 Quickstart

### Commands

## Launching

- Run `./gradlew` in root directory to do all the install tasks and compiling
- Run  `ng build` in root directory to build the angular app
- Run `./gradlew build` in root directory to build the spring-boot application. Make sure to have done an `ng build` before or you won't have a front end
- Change the working directory to `build/libs/` and run `java -jar project-0.0.1-SNAPSHOT.jar` to launch the server

## Testing

- Run `./gradlew test` to run the Spring-boot JUnit tests
- Run `ng test` to run Angularjs 2 tests

### Live Reload

- Run `ng serve --proxy-config proxy.conf.json` in root directory. To change the port (default : 8080), edit the proxy.conf.json file.