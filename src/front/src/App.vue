<template>
  <div id="app">
    <Main />
  </div>
</template>

<script>
import Main from "./components/Main";

export default {
  name: "App",
  components: { Main },
};
</script>

<style>
body {
  margin: 0;
  color: white;
  font-family: BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu",
    "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif;
}

#app {
  height: 100vh;
}
</style>
