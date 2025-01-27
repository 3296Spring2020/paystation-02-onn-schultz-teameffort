# TeamEffort Lab 3
This is the code for a parking meter pay station.

The goal of this lab was two-fold :
1. practice TDD (test driven development) methodologies.
2. learn and practice using Git to collaborate with a team member and manage 
merge conflicts. 
## Design
![UML Diagram](https://github.com/3296Spring2020/paystation-02-onn-schultz-teameffort/raw/master/FlowDiagram.png)

## Requirements
The required deliverables for this project were of three types:
1. First we had to create a team on git
2. We had to use Junit to create 8 distinct testing methods that could be used to specific aspects of different methods.  The methods we were testing were the following :
- empty()
- cancel()
- buy() <br/>
We had to create and test each @Test method individually, and also all together to make sure nothing interfered with each other.
Once all test cases passed we then had to submit a link to our git repository where the source code and testing methods are saved.
3. Lastly we had to ensure that each team member contributed “ their fair share” as well as making sure that proper documentation was written. The documentation came in the form of git comments, as well as comments within the code itself.  This README.me also is a required form of documentation for the final submission. 

## Team

### Abraham Schultz
I implemented a test method for the empty method called Should ReportAfterMoneyEmptied.
This returns the amount of money inserted into the machine since the last time the empty method was called.
Next i created a global public variable called totalInMachine. In the buy method i added to this variable every time a purchase was completed.
I also added the shouldReportEmptyMachine testing method. This makes sure that the machine reports no money inside after empty is called.
I then added the testing method to test that a call to cancel returns a map containing one coin that was entered. The last two methods I added were for testing that call to cancel clears the hashmap of coins, and that the call to cancel does not contain a key for a coin not entered. Between each test, I made sure to run a Junit test on the method individually, as well as running all the tests at once to ensure that they worked together.


### Rathanank Anthony Onn

- Provided a basic UML class diagram style chart to help visualize the flow chart of the source code.
- Implemented a Map to represent coins for the case of: {no coin entered, five cents, ten cents, twenty-five cents,}.
- Created an initializer function named "init()" to create mapping for coins.
- Created sucessful test functions for the following cases: 
- Canceled entry does not add to the amount returned by empty.
- Call to cancel returns a map containing a mixture of coins entered. 
- Call to buy clears the map.
- Wrote documention in source code for clarity of logic.    

## Build Instructions
We must note that in order to build this project and run the tests using netbeans, one must use version 8.2 of netbeans or greater. You can also use the most recent version of intellij to run the Junit test.

for netbeans:
- click run, then click on set main project to payStation.
- then click run and select test project.

for intellij :
- either press shit ctrl f10 to run the single test method you are currently in.
- or press shit alt f10 then select test all

