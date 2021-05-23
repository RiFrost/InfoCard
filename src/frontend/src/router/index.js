import { createRouter, createWebHistory } from "vue-router";
import store from "../store";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Topics from "@/views/Topics.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/topics",
    name: "Topics",
    component: Topics,
    meta: {
      requieresLogin: true
    }
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach(async (to, from, next) => {
  const hasUser = store.getters["user/isAuthenticated"];
  const requieresLogin = to.matched.some(record => record.meta.requieresLogin);

  if (!hasUser && requieresLogin) next({ name: "Login" });
  else next();
});

export default router;
