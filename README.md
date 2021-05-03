# unist_software_engineering

For group project CSE 364, Software Programming

## 1.  Project description 
### Workflow

The way of using github is same as milestone1. we make issues and branches of some topics and Pull Requests to repository before merge it. We seperate the project to two teams;team1 for algorithm of part1 & part2 and team2 for unit test. After making the algorithm of part1 and part2, team2 reviews that and edit to proper way. Then, team2 makes unit test and team1 write input error handling and README.md file. each teams reviews other teams' outcomes.

### Project file tree
```
├── data     contain .dat files
├── src
|   ├── main
|   |   └── Java
|   |       ├── data
|   |       |      Load.java          Reading data from .dat files
|   |       |      LoadLink.java      Reading links of movie
|   |       |      LoadUser.java      Select proper users with input conditions.
|   |       ├── handlelist
|   |       |      HandleList.java    Handleing lists and get average rating
|   |       |      BestMovie.java     Recommanding the top 10 best movies. This contains main algorithm of Milestone2
|   |       └── com
|   |              Main.java          Main class
|   |
|   └── test       Test files to test methods of classes
| 	
├── pom.xml        Including informations about this project
├── README.md
└── .gitignore
```
### Brief explanation of recommendation algorithm

In `LoadUser.java`, Select proper users of input criteria and put them to user list. When the user information is not same as criteria, only 1/4 of them(randomly) is putted in user list with their *weight* value which denoting *similarity* with input criteria.    
In this situation, users *weight* value means that **How much this user is close(=similar) to input user**. *weight*=1 means the user has same criteria with input user, and when the *weight* is close to 0, *similarity* between the user and the input user becomes lower. (ie. When occupation or age of user is different from input user, weight value becomes lower.)    
The reason that why we use only 1/4 of *similar* users is that when we use the whole *similar* users, impact on results of whole users are so big that the datas of users' ratings who have same criteria with input user become small. Because of this, the results could be different with same inputs.    
After that, select top 10 movies in `BestMovie.java`. To recommand movies, select top 30 most viewed movies first, and then select 10 top movies according to its average rating.

## 2. How to run program?

### Environments

* ubuntu 20.04
* VIM - Vi IMproved 8.1
* git version 2.25.1
* openjdk 11.0.10
* Apache Maven 3.6.3

### Install and Execute
```
git clone https://github.com/samsara-ku/unist_software_engineering.git  
cd unist_software_engineering  
mvn install  
java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar com.Main "F" "25" "Gradstudent"
java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar com.Main "F" "25" "Gradstudent" "Drama"
```
## 3.  Roles of each member
20171013_YunhoeKu : Refactor written codes of two members, write relevancy algorithm and LoadUser and LoadLink test.    
20171021_GeonUKim : Write unit tests for BestMovie and Main class. Enhancement method to get rating data.    
20181049_OhnKim : Make LoadUser, LoadLink, BestMovie class for solving Part 1, Handle some input error cases.    
20181228_YunyoungJung : Make algorithm with genre arguments about Part2. Write README.md file.    

Common : Comment and suggest a better idea about other codes. (e.g Github activity, Slack communication)

## 4. Expected output
 #### The best top 10 recommanded movies in the given user information and given movie genres

```
java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar com.Main "gender" "age" "occupation" "genres"
1. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.
2. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.
...
10. %s (http://www.imdb.com/title/tt%s) : %d watched and got %.2f ratings.
```

 ### Input error handling

This explains only milestone2 error handling. Error handling of milestone 1 is also contained in the code.
1. If the number of arguments are wrong, print "The number of arguments is not appropriate. Please use 2, 3, or 4 parameters. (Categories, Occupation | Gender, Age, Occupation | Gender, Age, Occupation, Genre)".
`ex.) com.Main "adventure"`
2. If input gender is wrong, print "Wrong gender input. Please try again with F for female or M for male."
`ex.) com.Main "Female" "25" "Gradstudent"`
3.  If the input age is non-positive, print "Wrong age input. (Non-positive age) Please try again with appropriate age."
`ex.) com.Main "F" "0" "Gradstudent"`
4. If the input age is not integer, print "Wrong age input. (Not an integer) Please try again with appropriate age."
`ex.) com.Main "F" "Ten" "Gradstudent"`
5. If the input occupation is not in the occupation list, print "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation."
`ex.) com.Main "F" "25" "Gradstu"`
6. If the input genre is not in the genre list, print "Can't search because there is inappropriate category. Please try again with appropriate category."
`ex.) com.Main "F" "25" "Gradstudent" "com"`