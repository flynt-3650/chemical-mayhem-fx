# Chemical Mayhem FX

**General Project Description**:
Chemical Mayhem FX is an innovative application that simplifies learning chemistry through an interactive Periodic Table of Elements. By clicking on any element, you can view key information about it, making the learning process more intuitive and effective.

Additionally, the app includes a smart calculator that allows you to compute the atomic mass of compounds, determine the oxidation states of chemical compounds, and identify the class of a chemical compound.

The application also features a visually appealing design. Several themes and a general design with a user-friendly interface will be developed, making it pleasant to use.

For maximum convenience, users can choose how to display and interact with the application—either through the console or a GUI interface. This makes Chemical Mayhem FX flexible and convenient for any user preference.

## Build and Run

**To build the application, use the following commands with Maven Wrapper:**

```bash
./mvnw clean install
java -jar target/ChemicalMayhemFX-1.0-shaded.jar
```

**To run without building:**

```bash
./mvnw clean javafx:run
```

Note that you will need Java 17 or later, set in the system variable JAVA_HOME.

## Containerization

**To containerize the project with Docker and run the container, enter:**

```bash
docker build -t cmf .
docker run -it cmf
```

## Dependencies and Plugins

1. **`org.openjfx:javafx-controls:13`**  
   Purpose: Adds support for JavaFX control elements such as buttons, text fields, and other graphical components.

2. **`org.openjfx:javafx-fxml:13`**  
   Purpose: Allows the use of FXML for describing JavaFX graphical interfaces, providing a separation of view and logic.

3. **`com.google.guava:guava:33.1.0-jre`**  
   Purpose: A utility library from Google, providing a set of extensions for working with collections, concurrency, strings, and other convenient methods.

4. **`org.apache.maven.plugins:maven-shade-plugin:3.2.4`**  
   Purpose: Creates a "fat jar"—a single JAR file containing all dependencies. Used for simplifying deployment. The configuration specifies `mainClass`, which defines the entry point for running the application.

5. **`org.apache.maven.plugins:maven-compiler-plugin:3.8.0`**  
   Purpose: Compiles Java source code. Specifies using JDK version 17 for compilation.

6. **`org.openjfx:javafx-maven-plugin:0.0.6`**  
   Purpose: Allows running JavaFX applications through Maven with the command `mvn javafx:run`. The configuration specifies `mainClass` to launch the main application class.
