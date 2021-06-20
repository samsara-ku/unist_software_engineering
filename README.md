# unist_software_engineering

For group project CSE 364, Software Programming

## 1. Updates

- 06/20/2021: cse364-project-1.0-SNAPSHOT

## 2. Developers

- UNIST, YunhoeKu: Team Leader
- UNIST, GeonUKim: Team Member
- UNIST, OhnKim: Team Member
- UNIST, YunyoungJung: Team Member

## 3. How to Connect
Open web server
Mapping to `https://localhost:8080/`

## 4. Basic Interface
There are many movie posters in our website. You can scroll parallel at some parts and when you click the posters, you can move to other sites that you can see the movie information. 

### Today’s Movie
When you enter our web page, you are faced with a movie in the basic setting. The movie changes randomly every day to stimulate your interest.

### Top Rated
If you check the webpage from the top, you can see the top-rated movies tab. This shows the movies that people scored highest in our entire database. These are films that have been well-received by many people, so maybe you will like them too.

### Recently Hottest Movies
If you look at the second item, it shows recently hottest movies for a specific group. Even if it is not a group that belongs to you, you can recommend it to your friends and family through this.

## 5. Recommendation System
### User Filter Movies
You can input your information and receive recommendations for movies that are mostly watched by users similar with you. In gender input, word `M` means "male" and `F` means "female". After that, you can enter their age, occupation, and wanted genre. More than one genre of input is also possible. If the input is incorrectly received, an error message can be used to check which input is incorrect and can re-enter the information. You do not have to input all your information. If you left input to blank, the movie is selected regardless of that information. After that, press the search button to automatically recommend the best 10 movies to match the information you inputted.

### Movie Based Recommendations
There is another recommend system. If there are some impressive movies you’ve ever seen, and want to find similar movies, input the name and year of the movie. Then we can recommend the similar movies based on your input. After entering information about the movie, enter the number of movies you want to be recommended in Limit, and we will recommend a number of movies you input in Limit. If you left it blank, We will recommend a top 10 movie automatically.

## 6. Citation
- maven
- tomcat
- mongodb