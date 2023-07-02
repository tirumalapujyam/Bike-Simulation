# Bike driving simulation task

#### Reference to the requirement

CodingChallenge-BikeSimulation.pdf document attached to the root folder of the project.  

#### COMMANDS

- PLACE <X>,<Y>,<FACING_DIRECTION>  
  ex: PLACE 1,2,EAST
- FORWARD
- TURN_RIGHT
- TURN_LEFT
- GPS_REPORT
- EXIT to exit the app or finish execution.

#### Execution instructions

- JDK/JRE 8 or above. The default is 11, change the settings at pom.xml file
  <maven.compiler.source>11</maven.compiler.source>  
  <maven.compiler.target>11</maven.compiler.target>
- Application.java class is the entrance of the application runtime and main method
- User input supplied in one of the two following ways 

  ##### Providing the input

  - User can place all the commands in input.txt file at application's resource folder and run the application 
  - OR
  - delete input.txt file or delete the content of the input.txt file and run the application to manually enter the commands at command interaction tool.

  ##### Application running steps

  - To build application use MAVEN: **mvn clean install**
  - The above command creates a binary jar file into *target* folder of the project.  
    Example: BikeDriving-1.0-SNAPSHOT.jar
  - we need to run the above jar to work/test the application and the command for that is  
    java -jar target/BikeDriving-1.0-SNAPSHOT.jar
  - Output example:  
    PLACE 2,2,EAST  
    FORWARD  
    TURN_RIGHT 
    GPS_REPORT  
    (3,2), SOUTH


THANK YOU

Author: Tirumala

