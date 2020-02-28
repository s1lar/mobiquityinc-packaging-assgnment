# Assignment: Package Challenge

This is challenge for Mobiquity by Igor Sila.

Thi challenge was solved by Java 11, maven and JUnit 5.

## Introduction

You want to send your friend a package with different things.
Each thing you put inside the package has such parameters as index number, weight and cost. The
package has a weight limit. Your goal is to determine which things to put into the package so that the
total weight is less than or equal to the package limit and the total cost is as large as possible.
You would prefer to send a package which weighs less in case there is more than one package with the
same price.

## Input Sample

```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9)(6,46.34,€48)
8 : (1,15.3,€34)
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52)(6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36)(6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
```

## Output Sample

```
4
- 
2,7 
8,9
```

## Constraints for the system
1. The test file will be in UTF-8 format.
2. Max weight that a package can take is ≤ 100
3. There might be up to 15 items you need to choose from
4. Max weight and cost of an item is ≤ 100

## Constraints for the developing
1. Apply best practices for software design & development and document your approach
2. Put comments into source files


### System requirements

First of all, the following tools need to be installed:
* JDK 11
* Maven

#### Installing

To install project, type in the root of assignment in the terminal:
```
mvn clean install
```
#### Run the application
After successful installation, execute next command from console:
```
mvn exec:java -Dexec.mainClass="com.mobiquity.Main" -Dexec.args="***yourfilepath***"
```
Where ***yourfilepath*** is the absolute path to the file with test cases

#### Run the tests
To run tests type:
```
mvn test
```

##### Author
Igor Sila