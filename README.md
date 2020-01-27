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

### Abraham Schultz
// describe contributions here
testing

### Rathanank Anthony Onn

- Provided a basic UML class diagram style chart to help visualize the flow chart of the source code.
- Implemented a Map to represent coins for the case of: {no coin entered, five cents, ten cents, twenty-five cents,}.
- Created an initializer function named "init()" to create mapping for coins.
- Created sucessful test functions for the following cases: 
    Canceled entry does not add to the amount returned by empty.
    Call to cancel returns a map containing a mixture of coins entered. 
    Call to buy clears the map.
- Wrote documention in source code for clarity of logic.    

## Testing

