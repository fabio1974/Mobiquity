# Assignment: Package Challenge



## Aproaching the problem

From the [problem statement](Packaging%20Challenge.pdf), it is clear that the last item in each package could be, for example, the solution. So it looks necessary to go through all the combinations of available items to find the best solution, with the highest cost, a total weight below the package limit, and obey the other established restrictions.

The idea was then to find all item combinations taking into account their indices only, to make processing lighter. Once each combination is found, the package calculation is done, and if it is 'better' than the last one, it takes its place.

So to improve time complexity, which is O(n²) for the algorithm I implemented and explained [here](RECURSIVE_COMBINATION.md), I decided not to store the combinations and loop them later. The moment I find the combination, a notification message is sent to an Observer who will do the job of checking if the solution is the best for the package. This was the reason for using the Observer Pattern: not over loop the combinations and save processing.


## Available Scripts

This is a java lib gradle project, compiled using java version 11.0.10. 
It uses jUnit 5.8.1 for tests, and its code is stored as a public GitHub repository.

To clone the repository with a SSH key, you can run:

#### `git clone git@github.com:fabio1974/Mobiquity.git`

Then you must run gradle commands from the project directory. To clean and build the java resources, in a linux environment you can run:

#### `./gradlew clean build`

There are units and functional tests. To run them you can use:

#### `./gradlew test`

To install the lib in your maven local, and use it as a dependence, you need run:

#### `./gradlew publishToMavenLocal`

Then, to use the lib, add it to your java project dependencies:

Maven:

```xml

<dependency>
    <groupId>com.mobiquity</groupId>
    <artifactId>implementation</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

```

Gradle:

`implementation group:'com.mobiquity', name:'implementation', version:'1.0-SNAPSHOT'` 
