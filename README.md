# unist_software_engineering

For group project CSE 364, Software Programming

## 1.  A short description of what you’ve finished for this milestone.
### Workflow

Initially, we created a git repository for this milestone. For multiple people to write code at the same time, we decided to use the Issue, branch, and Pull Request of git. First, we raised the Issue of what to develop, and write code on their own branch, than posted Pull Requests to repository before merge it. After that, the other team members suggested problems and errors of code and refactored it. In this process, we tested the codes using test cases.

### Project file tree
```
├── data     contain .dat files
├── src
|   ├── main
|   	├── Java
|	    ├── data
|		└── Load.java     	 Reading data from .dat files
|	    ├── handlelist
|		└── HandleList.java      Handleing lists and get average rating
|	    └── com
|		└── Main.java      Main class
|
|	└── test      Test files to test methods of classes
| 	
├── pom.xml        Including informations about this project
├── README.md
└── .gitignore
```

## 2. A description of how to run your program. An example of java command line will be good.

### 실행 환경

* ubuntu 20.04
* VIM - Vi IMproved 8.1
* git version 2.25.1
* openjdk 11.0.10
* Apache Maven 3.6.3

### 설치 및 실행
```
git clone https://github.com/samsara-ku/unist_software_engineering.git  
cd unist_software_engineering  
mvn install  
java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar com.Main "adventure" "educator"
```
## 3.  Roles of each member (i.e. who did what?)
20171013_YunhoeKu : Make Load class and refactoring Main class. Assist other teammates' working and initiating our project structure.
20171021_GeonUKim : Make HandleList class and enhancement. Write README file and some test cases.
20181228_YunyoungJung : Make unit test case. Suggest an algorithm idea to reduce execution time.
20181049_OhnKim : Improve overall execution, Handle some error cases (invalid input, spacing, lowercase, etc), Modificate the input method.

Common : Comment and suggest a better idea about other codes. (e.g Github activity, Slack communication)

## 4. Expected output
 #### The average rating score of all movies in the given category rated by the given occupation

```
java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar com.Main "adventure" "educator"
There are a total of \"%d\" movies that fit the requested category, and the average score is about \"%.2f\" points.
```

 ### Input error handling

1. If there are not two arguments, print "The number of arguments is not appropriate. Please use only 2 parameters, Categories and Occupation. (e.g Adventure Educator)".
ex.) com.Main "adventure"
2. If Input category is not in the category list, print "Can't search because there is inappropriate category. Please try again with appropriate category.".
ex.) com.Main "adventu" "lawyer"
3. If Input occupation is not in the occupation list, print "Can't search because it's an inappropriate occupation. Please try again with appropriate occupation.".
ex.) com.Main "adventure" "astronaut"
4. If both category and occupation are inappropriate , print "Can't search because there are inappropriate category and occupation. Please try again with appropriate category and occupation."
ex.) com.Main "adventu" "astronaut"
5. If entered correctly but there is no corresponding movies, print "Despite the correct category and occupation, nothing was found. Please try again with different category."
ex.) com.Main "Action|Comedy|Crime|Horror|Thriller|Romance" "self-employed"
6. If entered correctly and corresponding movies exist but there is no rating data, print "There are a total of # movies that fit the requested category, but the average score is 0 points due to no rating data. Please try again with another occupation."
