# unist_software_engineering

For group project CSE 364, Software Programming

## 1. Project description

### Workflow

The way of using github is same as prior milestones. We made issues and branches of some topics and
Pull Requests to repository before merge it. We separated the project to two teams;team1 for part1 &
part2 and team2 for unit test. After making the part1 and part2, team2 reviews that and edit to proper way.
Then, team2 makes unit test and team1 write input error handling and README.md file.
Each teams reviews other teams' outcomes.

### Project file tree

```
├── data     contain .dat files
├── src
|   ├── main
|   |   ├── Java
|   |   |    ├── com
|   |   |    |   |       Main.java                         Main class
|   |   |    |   ├── rest
|   |   |    |   |       UserRecommend.java                Value Object for UserRecoomendController
|   |   |    |   |       UserRecommendController.java      Handle milestone 3 task with four args
|   |   |    |   |       MovieRecommend.java               Value Object for MovieRecoomendController
|   |   |    |   |       MovieRecommendController.java     Handle milestone 3 task with two args
|   |   |    |   └──     CustomErrorController.java        Handle milestone 3 error case
|   |   |    └── factory
|   |   |        |       Factory.java                      Abstract class for all factory java files
|   |   |        |       TwoArgsFactory.java               Make print and validity for milestone 1 result
|   |   |        |       ThreeArgsFactory.java             Make print and validity for milestone 2 result
|   |   |        |       FourArgsFactory.java              Make print and validity for milestone 2 result
|   |   |        |       MovieRecommendFactory.java        Make print and validity for milestone 3 result
|   |   |        ├── link
|   |   |        |       LinkUserAndRating.java            Link imdb url with movie rating list
|   |   |        ├── rating
|   |   |        |       RatingUtils.java                  Utility functions for rating package
|   |   |        |       RatingWithTwoArgs.java            Make rating output with two arguments (milestone 1)
|   |   |        |       RatingMoreThanTwoArgs.java        Make rating output with more than two arguments (milestone 2 & 3)
|   |   |        |       RecommendMovieAbstract.java       Abstract class for MovieTitleRecommend.java
|   |   |        |       MovieTitleRecommend.java          Make rating output with two arguments(milestone 3)
|   |   |        ├── user
|   |   |        |       Load.java                         Abstract class for user package
|   |   |        |       LoadTwoArgs.java                  Make appropriate movielist with two arguments (milestone 1)
|   |   |        |       LoadMoreThanTwoArgs.java          Make appropriate movielist with more than two arguments (milestone 2 & 3)
|   |   |        └──     LoadMovieTitle.java               Make appropriate movielist with two argus (mil
|   |   |
|   |   |
|   |   └── resources
|   |         └──        application.properties            Properties for spring framework
|   |
|   └── test       Test files to test methods of classes
|
├── pom.xml        Including informations about this project
├── README.md
└── .gitignore
```

### Brief explanation of recommendation algorithm

The basic concept is, finds the users who rated high score for the entered movie, then
recommends the movies that the selected users watched a lot and rated well.

In `LoadMovieTitle.java`, creates a userList of users who rated the input movie at least 3 points.
With that userList, in `MovieTitleRecommend.java`, find movies what the selected users rated more than
3 points and record the number of rating for each movie. At this point, weights are applied using
similarities in genres, the more genres that match, the more weights. Then rank the records to generate movieList
of limit\*2 sizes(`ArrayList<Integer> topDoubleLimit`).  
After that, rank the selected movies by average rating to create a final movieList that size of limit.  
If the size of final movieList is smaller than limit(when movie has very rare genre), it adds other movies with high
views and ratings to the back of list until limit.

## 2. How to run program?

### Environments

- ubuntu 20.04
- VIM - Vi IMproved 8.1
- git version 2.25.1
- openjdk 11.0.10
- Apache Maven 3.6.3
- Springframe 4.3.20
- Jackson 2.11.4

### Install and Execute

```
git clone https://github.com/samsara-ku/unist_software_engineering.git
cd unist_software_engineering
mvn package
java -jar target/cse364-project-1.0-SNAPSHOT.jar
```

## 3. Roles of each member

20171013_YunhoeKu : Refactoring milestone2 codes to keep clean. Make part 1 of milestone 3 task.  
20171021_GeonUKim : Make part 2 of milestone 3 task and make some error handling codes.  
20181049_OhnKim :  
20181228_YunyoungJung :

Common : Comment and suggest a better idea about other codes. (e.g Github activity, Slack communication)

## 4. Expected output

### The best top recommanded movies in the given user information and given movie genres

```
java -jar target/cse364-project-1.0-SNAPSHOT.jar

curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "F", "age": "15", "occupation": "gradstudent", "genre": "Adventure|Animation"}'
```

Sequentially execute above commands makes result like below:

```
[ {
  "imdb" : "https://www.imdb.com/title/tt0120762",
  "genre" : "Animation|Children's",
  "title" : "Mulan (1998)"
}, {
  "imdb" : "https://www.imdb.com/title/tt0110357",
  "genre" : "Animation|Children's|Musical",
  "title" : "Lion King, The (1994)"
}, {
...
}, {
  "imdb" : "https://www.imdb.com/title/tt0113497",
  "genre" : "Adventure|Children's|Fantasy",
  "title" : "Jumanji (1995)"
} ]
```

### The best top 'n' recommanded movies in the given movie title and movielist limit number

```
java -jar target/cse364-project-1.0-SNAPSHOT.jar

curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title": "Toy story (1995)", "limit": 10}'
```

Sequentially execute above commands makes result like below:

```
[ {
  "imdb" : "https://www.imdb.com/title/tt0108598",
  "genre" : "Animation|Comedy",
  "title" : "Wrong Trousers, The (1993)"
}, {
  "imdb" : "https://www.imdb.com/title/tt0058331",
  "genre" : "Children's|Comedy|Musical",
  "title" : "Mary Poppins (1964)"
}, {
...
}, {
  "imdb" : "https://www.imdb.com/title/tt0120595",
  "genre" : "Children's|Comedy",
  "title" : "Babe: Pig in the City (1998)"
} ]
```

### Input error handling


The error messages are returned as JSON type.
```
ex.) 
{    
   "error" : "Error Message"    
}
```
Part 1.
1. If the input format is not JSON type or incorrect data type, return "Please input the appropriate JSON format and data.".    
   `ex.) '{  : "F", "age": "25", "occupation": "Gradstudent", "genre": "Action|War"}'`    
   `'{"gender" : , "age": "25", "occupation": "Gradstudent", "genre": "Action|War"}'`
2. If input gender is wrong, return "Wrong gender input. Please try again with F for female or M for male."    
   `ex.) '{"gender": "Female", ""age"": "25", "occupation": "Gradstudent", "genre": "Action|War"}'`
3. If the input age is non-positive, return "Wrong age input. (Non-positive age) Please try again with appropriate age."    
   `ex.) '{"gender": "F", ""age"": "-1", "occupation": "Gradstudent", "genre": "Action|War"}'`
4. If the input age is not integer, return "Wrong age input. (Not an integer) Please try again with appropriate age."    
   `ex.) '{"gender": "F", ""age"": "Ten", "occupation": "Gradstudent", "genre": "Action|War"}'`
5. If the input occupation is not in the occupation list, return "Can't search inappropriate occupation, "(wrong occupation input)". Please try again with appropriate occupation."    
   `ex.) '{"gender": "F", ""age"": "25", "occupation": "Gradstu", "genre": "Action|War"}'`
6. If the input genre is not in the genre list, return "Can't search because there is inappropriate category. Please try again with appropriate category."    
   `ex.) '{"gender": "F", ""age"": "25", "occupation": "Gradstudent", "genre": "com"}'`

Part 2.
1. If the input format is not JSON type or incorrect data type, return "Please input the appropriate JSON format and data.".    
   `ex.) '{ : "Toy Story (1995)", "limit": 10}'`    
   `'{"title": , "limit": 10}'`
2. If the input title is wrong, return "Cannot find movies with your title input. Please try again with accurate movie title."    
   `ex.) '{"title": "Toy St (1995)", "limit": 10}'`
3. If the input limit is out of range, return "Wrong limit input. (Not in range) Please try again with appropriate limit that 1 to 700."    
   `ex.) '{"title": "Toy Story (1995)", "limit": -1}'`    
   `'{"title": "Toy Story (1995)", "limit": 701}'`
4. If the input age is not integer, return "Wrong limit input. (Not an integer) Please try again with appropriate limit."    
   `ex.) '{"title": "Toy Story (1995)", "limit": "ten"}`
5. If both title and limit is wrong, both error messages are returned.    
   `ex.) '{"title": "Toy St (1995)", "limit": -1}'`
