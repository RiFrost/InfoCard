<template>
  <div class="app">
    <Header></Header>
    <router-view class="main-content" />
    <Footer></Footer>
  </div>
</template>

<script>
import { onBeforeMount } from "vue";
import { useStore } from "vuex";
import Header from "./components/Header";
import Footer from "@/components/Footer.vue";

export default {
  components: {
    //"my-header": Header,
    Header,
    Footer
  },

  setup() {
    const store = useStore();

    onBeforeMount(() => {
      store.dispatch("user/checkCookies");
    });
  }
};
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");

body {
  padding: 0;
  margin: 0;
  font-family: "Roboto", sans-serif;
  background-color: #e3ecf8;

  #app {
    .app {
      display: flex;
      width: 100%;
      flex-direction: column;
      justify-content: space-between;
      min-height: 100vh;

      .main-content {
        margin-bottom: auto;
      }
    }
  }
}
</style>
