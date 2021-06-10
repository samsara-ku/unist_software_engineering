<template>
  <div v-if="loading" class="main_container">
    <!-- <div class="main_container"> -->
    <div class="main_header">
      <div class="header-col">
        <div class="logo">
          <img
            src="https://fontmeme.com/permalink/210609/7aab8d1b363ed000592aa5fd8a7cb245.png"
            alt="netflix-font"
            border="0"
            width="90"
          />
        </div>
      </div>
      <div class="header-col">
        <div>CSE 364</div>
      </div>
    </div>
    <div class="main_poster_container">
      <div
        class="main_poster"
        :style="{
          backgroundSize: 'contain',
          backgroundImage: 'url(' + mainPoster.poster + ')',
          backgroundPosition: 'center center',
          backgroundRepeat: 'no-repeat',
          marginLeft: '-100px',
          width: '20%',
          height: '400px',
        }"
      ></div>
      <div>
        <div class="poster-description">Today's Movie</div>
        <div class="poster-title">
          {{ this.mainPoster.title }}
          <div v-for="(e, i) in 5" :key="i" class="star">
            <span v-if="e < mainPoster.rating">⭑</span>
            <span v-else>⭒</span>
          </div>
        </div>
        <div class="poster-button-group">
          <button>Play</button>
          <button>My List</button>
        </div>
      </div>
    </div>
    <div class="main_content">
      <div class="top_movies">
        <div class="title">
          <h1>Top Rated</h1>
        </div>
        <div v-if="topMovies.length == 10" class="movies_container">
          <div v-for="(elem, index) in topMovies" :key="index" class="movie">
            <a :href="elem.link" target="_blank">
              <img
                :src="elem.poster != noImage ? elem.poster : noImageUrl"
                alt="No Image"
              />
            </a>
          </div>
        </div>
      </div>
      <div class="random_movies">
        <div class="title">
          <h1>
            Recently Hottest Movies for {{ Math.round(this.age / 10) * 10 }}'s
            {{ this.gender }}
          </h1>
        </div>
        <div v-if="randomMovies.length == 10" class="movies_container">
          <div v-for="(elem, index) in randomMovies" :key="index" class="movie">
            <a :href="elem.link" target="_blank">
              <img
                :src="elem.poster != noImage ? elem.poster : noImageUrl"
                alt="No Image"
              />
            </a>
          </div>
        </div>
      </div>
      <div class="recommend_movies">
        <div class="title">
          <h1>User Filter Movies</h1>
        </div>
        <div class="input-field">
          <div>
            Please put your gender(M or F):
            <input type="text" v-model="input_gender" />
          </div>
          <div>
            Please put your age: <input type="number" v-model="input_age" />
          </div>
          <div>
            Please put your occupation:
            <input type="text" v-model="input_occupation" />
          </div>
          <div>
            Please put your genre: <input type="text" v-model="input_genre" />
          </div>
        </div>
        <button class="input-button" @click="recommendMovie">Search</button>
        <div class="random_movies">
          <div v-if="recommend_loading" class="recommend_loading" />
          <div v-else>
            <div
              v-if="recommendMovies.length && !recommend_loading"
              class="movies_container"
            >
              <div
                v-for="(elem, index) in recommendMovies"
                :key="index"
                class="movie"
              >
                <a :href="elem.link" target="_blank">
                  <img
                    :src="elem.poster != noImage ? elem.poster : noImageUrl"
                    alt="No Image"
                  />
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div v-else class="lds-ring">
    <div></div>
    <div></div>
    <div></div>
    <div></div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Main",

  data() {
    return {
      loading: false,
      recommend_loading: false,
      mainPoster: "",
      topMovies: [],
      randomMovies: [],
      recommendMovies: [],
      noImage:
        "https://www.shutterstock.com/image-vector/no-image-available-sign-internet-web-261719003",
      noImageUrl:
        "https://image.shutterstock.com/image-vector/no-image-available-sign-internet-600w-261719003.jpg",
      gender: ["Female", "Male"],
      age: 0,
      input_gender: "",
      input_age: "",
      input_occupation: "",
      input_genre: "",
    };
  },

  methods: {
    async recommendMovie() {
      console.log(
        this.input_gender,
        this.input_age,
        this.input_occupation,
        this.input_genre
      );

      const {
        data,
        data: { error },
      } = await axios.get(
        `/users/recommendations?gender=${this.input_gender}&age=${this.input_age}&occupation=${this.input_occupation}&genre=${this.input_genre}`
      );

      this.recommend_loading = true;

      if (error) alert(error);
      else this.recommendMovies = data;

      setTimeout(() => {
        this.recommend_loading = false;
      }, 3000);
    },
  },

  async created() {
    const main_poster_movie = await axios.get(
      "movies/recommendations/?title=Toystory(1995)&limit=10"
    );
    this.mainPoster = main_poster_movie?.data[Math.floor(Math.random() * 10)];

    const top_movies = await axios.get("/topmovies");
    this.topMovies = top_movies.data;

    this.gender = this.gender[Math.round(Math.random())];
    this.age = Math.floor(Math.random() * 30 + 10);

    const random_movies = await axios.get(
      `users/recommendations?gender=${this.gender[0]}&age=${this.age}&occupation=&genre=`
    );
    this.randomMovies = random_movies?.data;

    this.loading = true;
  },
};
</script>

<style scoped>
.main_container {
  transform: all 0.5s;
  padding: 10px 50px;
  background-color: #111;
}

.main_header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
}

.main_poster_container {
  display: flex;
  justify-content: center;
  gap: 150px;
  margin-bottom: 10px;
}

.main_poster_container .poster-description {
  font-size: 3.5rem;
  font-weight: 800;
}

.main_poster_container .poster-title {
  display: flex;
  align-items: center;
  font-size: 2rem;
  margin: 30px 0;
}

.main_poster_container .poster-button-group button {
  cursor: pointer;
  color: #fff;
  outline: none;
  border: none;
  font-weight: 700;
  border-radius: 0.2vw;
  padding: 0.5rem 2rem;
  margin-right: 1rem;
  background-color: rgba(51, 51, 51, 0.5);
}

.main_poster_container .poster-button-group button:hover {
  color: #000;
  background-color: #e6e6e6;
  transition: all 0.2s;
}
.main_poster_container .poster-title .star {
  margin: 0 2px;
}

.main_poster_container .poster-title .star:nth-child(1) {
  margin-left: 15px;
}

.main_content {
  display: flex;
  flex-direction: column;
  gap: 30px;
  padding: 20px 60px;
  background-color: rgb(45, 45, 45);
}

.main_content .title {
  margin-bottom: 30px;
}

.main_content .movies_container {
  display: flex;
  gap: 15px;
  overflow-x: auto;
  overflow-y: hidden;
}

.main_content .movie {
  width: 200px;
  transition: all 450ms;
}

.main_content .movie:hover {
  transform: scale(1.08);
}

.main_content img {
  width: 200px;
  height: 250px;
}

.recommend_loading {
  background: transparent
    url("https://miro.medium.com/max/882/1*9EBHIOzhE1XfMYoKz1JcsQ.gif") center
    no-repeat;
  width: 300px;
  height: 400px;
  margin: 0 auto;
  opacity: 0.1;
}

.input-button {
  margin-bottom: 20px;
}

.lds-ring {
  display: inline-block;
  position: relative;
  width: 100%;
  height: 100%;
  background-color: black;
}

.lds-ring div {
  box-sizing: border-box;
  display: block;
  position: absolute;
  top: 40%;
  right: 45%;
  width: 128px;
  height: 128px;
  margin: 8px;
  border: 8px solid #fff;
  border-radius: 50%;
  animation: lds-ring 1.2s cubic-bezier(0.5, 0, 0.5, 1) infinite;
  border-color: #fff transparent transparent transparent;
}

.lds-ring div:nth-child(1) {
  animation-delay: -0.45s;
}

.lds-ring div:nth-child(2) {
  animation-delay: -0.3s;
}

.lds-ring div:nth-child(3) {
  animation-delay: -0.15s;
}

@keyframes lds-ring {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
