# Assignment: Package Challenge



## Aproaching the problem

From the problem statement, it is clear that the last item in each package could be, for example, the solution. So it looks necessary to go through all the combinations of available items to find the best solution, with the highest cost, a total weight below the package limit, and obey the other established restrictions.

The idea was then to find all item combinations taking into account their indices only, to make processing lighter. Once each combination is found, the package calculation is done and if is 'better' than the last one, it takes its place.

So to improve time complexity, which is O(n²) for the algorithm I implemented and explained [here](RECURSIVE_COMBINATION.md), I decided not to store the combinations and loop them later. The moment I find the combination, a notification message is sent to an Observer who will do the job of checking if the solution is the best for the package. This was the reason for using the Observer Pattern: not over loop the combinations and save processing.


## Available Scripts

This is a java gradle project, compiled using java version 11.0.10, 
and jUnit 5.8.1 and stored as a public GitHub repository.

To clone the repository, you can run:

#### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.\
You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!
