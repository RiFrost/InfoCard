<template>
  <div class="app">
    <Header></Header>
    <router-view />
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
body {
  padding: 0;
  margin: 0;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  background-color: #e3ecf8;
}
</style>
