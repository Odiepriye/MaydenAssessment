Hours committed: 6
Stories completed: 9 (all except 10)
Application Type: Terminal java application
Stack: Java

Application: Healthy eating app
Requirements: 
- keeps track of what you need
- what needs to be purchased
- budget constraints

Prerequisites:
- This app is built as a Java application that runs through the terminal.
- Maven needs to be installed.
- mvn test is neeced to run the unit tests.
- To run the application there are 2 executables one for UNIX(Mac/Linux) and one for Windows environments (start.bat and start.sh)


Assumptions/Deciscions:
- I assumed crossing an item is different to removing an item and crossing an uncrossing an item is just that.
- I didn't fully implement the email feature and used mailto: instead due to the time requirement and trying to make a simple readable solution.
- I used Maven as the build tool for this application as it gave me more flexibility for testing with JUnit and the possible expansion for it to be an executable jar (but that exceeded the time limit).
- I added my personal research on how to build a mailto: client in Java and testing in the hours committed.
- Testing was also encapsulated in the time taken for this project.
- Writing this .md was also included in the hours commited even though it did not take that long

Explanation:
- The application logic lives in Application.java
- Grocery.java is the class responsible for creating Grocery objects living in the lst/Application, and I assumed it was immutable.
- Email handles the mailto logic
- DataStore handles persitence regardless if the terminal session/cmd is ended, it persists it by reading and writing to a txt file in the data directory called shopping-list.txt.
- Main handles the applications interface with the user.
- I wrote 14 unit tests for good test coverage of core logic and it can be ran using mvn test.

If you made it this far I hope you enjoy playing with this as it was actually fun to build :)