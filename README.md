# unist_software_engineering

For group project CSE 364, Software Programming

## 1.  A short description of what you’ve finished for this milestone.
### Workflow

Initially, we created a git repository for this milestone. For multiple people to write code at the same time, we decided to use the Issue, branch, and Pull Request of git. First, we raised the Issue of what to develop, and write code on their own branch, than posted Pull Requests to repository before merge it. After that, the other team members suggested problems and errors of code and refactored it. In this process, we tested the codes using test cases.

### Project file tree
```
├── data contain .dat files
├── src
|   ├── main
|   	├── Java
|	    ├── com.data
|		└── Load.java     	 Reading data from .dat files
|	    ├── com.handlelist
|		└── HandleList.java      Handleing lists and get average rating
|	    └── com
|		└── HelloWorld.java      Main class
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
java -cp target/cse364-project-1.0-SNAPSHOT-jar-with-dependencies.jar HelloWorld "adventure" "educator"
```
## 3.  Roles of each member (i.e. who did what?)
20181228_YunyoungJung : Make unit test case. Suggest an algorithm idea to reduce execution time.
20181049_OhnKim : Improve overall execution, Handle some error cases (invalid input, spacing, lowercase, etc), Modificate the input method.

Common : Comment and suggest a better idea about other codes. (e.g Github activity, Slack communication)


## 4. Input error handling
1. If there are not two arguments, print "Inappropriate # of arguments!".
ex.) HelloWorld "adventure"
2. If Input category is not in the category list, print "No such categories".
ex.) HelloWorld "adventu" "lawyer"
3. If Input occupation is not in the occupation list, print "No such occupation".
ex.) HelloWorld "adventure" "astronaut"
4. If entered correctly but there is no corresponding ratings, print "No matched data"
ex.) HelloWorld "Action|Comedy|Crime|Horror|Thriller|Romance|Sci-Fi" "self-employed"
