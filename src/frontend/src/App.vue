<template>
  <div>
    <!--<my-header></my-header>-->
    <Header_new></Header_new>
    <router-view />
  </div>
</template>

<script>
import { onBeforeMount } from "vue";
import { useStore } from "vuex";
//import Header from "./components/Header";
import Header_new from "./components/Header_new.vue";

export default {
  components: {
    //"my-header": Header,
    Header_new
  },

  setup() {
    const store = useStore();

    onBeforeMount(() => {
      store.dispatch("user/checkCookies");
    });
  }
};
</script>

<style>
body {
  padding: 0;
  margin: 0;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  background-color: #e3ecf8;
}
</style>
