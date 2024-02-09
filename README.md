This project describes the setup of the UI infrastructure for students implementation project of the game: code-names

UI Mocks can be found in mocks folder and can be viewed using https://excalidraw.com/

## Getting Started
The project assumes you are using Java 8, and that Maven is installed on your machine.

Download project and run `mvn clean install` 

The main jar is located at `ui/target/CodeNamesApp.jar`
In order to execute it, simply `java -jar CodeNamesApp.jar` 

When the app is bootstrap, it first searches for the UI API implementation (supposed to be supplied by the students).

In case the implementation was found the app will go up.
In case no implementation was found the app will collapse.

By default, it will scan all jars in the working directory. 
If needed you can supply path to folder that holds the implementation jar by adding VM argument called `apiImplFolder`. 
For that case the execution command becomes:
`java -jar -DapiImplFolder=<path to folder> CodeNamesApp.jar`

