# Spring-Boot / Angular2 Quickstart

## About

The project was made using AngularJS 2.4.4 and Spring-boot 1.4.3. I used angular-cli, which can be found [here](https://github.com/angular/angular-cli) to generate the Angular 2 front-end of the application and spring initializr, which can be found [here](http://start.spring.io/) to generate the back-end spring-boot of the application

## Requirements

- npm and nodejs, you can install nodejs and npm [here](https://nodejs.org/)
- Java SDK, which you can find [here](https://www.oracle.com/downloads/index.html)

### Optional

- the AngularJS command line interface, [here](https://github.com/angular/angular-cli)
- Gradle, [here](https://gradle.org/)

## Project file structure

- Exactly the same as Spring-boot or AngularJS 2, except the files for each application are mixed
- Folder `src/` contains the sources AngularJS app (folders and files in `src/main/` and `src/test/` excluded)
- Folder `src/main/` contains the sources for the Spring-boot app
- Folder `src/test/` contains the sources for the Spring-boot app JUnit testing

- The compiled AngularJS 2 javascript files are put into the folder `src/main/resources/static/`. Once the Spring-boot server is launched they are available at `http://localhost:8080`.

### Example modules
- There are 2 example modules in the project, "To do list" and "Hello world", each example has a Spring-boot controller for the back-end and its own folder for the front-end.
- The hello world module allows you to enter a name and submit, to which the server replies with a message.
- The to do list module allows you to create/delete/view elements from a to do list which is stored in the back-end.

## Commands

### Launching

- Run `./gradlew` in root directory to do all the install tasks and compiling
- Run  `ng build` in root directory to build the angular app
- Run `./gradlew build` in root directory to build the spring-boot application. Make sure to have done an `ng build` before or you won't have a front end
- Change the working directory to `build/libs/` and run `java -jar project-0.0.1-SNAPSHOT.jar` to launch the server
- Once the project is launched, go to `http://localhost:8080` in your web browser and test the app

### CORS error with chrome
- Make a shortcut to chrome web browser on the desktop, edit the properties of the shortcut and under "target" add the following `--disable-web-security --user-data-dir="c:/chromeUserData"`

### Testing

- Run `./gradlew test` to run the Spring-boot JUnit tests
- Run `ng test` to run Angularjs 2 tests

### Live Reload

- Run `ng serve --proxy-config proxy.conf.json` in root directory. To change the port (default : 8080), edit the proxy.conf.json file.
