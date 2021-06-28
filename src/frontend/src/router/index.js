import { createRouter, createWebHistory } from "vue-router";
import store from "../store";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Topics from "@/views/Topics.vue";
import Indexcards from "@/views/Indexcards.vue";
import Favorites from "@/views/Favorites.vue";
import About from "@/views/About.vue";
import Contact from "@/views/Contact.vue";
import Profile from "@/views/Profile.vue";

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
  },
  {
    path: "/indexcards",
    name: "indexcards",
    component: Indexcards,
    meta: {
      requieresLogin: true
    }
  },
  {
    path: "/favorites",
    name: "Favorites",
    component: Favorites,
    meta: {
      requieresLogin: true
    }
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
    meta: {
      requieresLogin: true
    }
  },
  {
    path: "/about",
    name: "About",
    component: About
  },
  {
    path: "/contact",
    name: "Contact",
    component: Contact
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach(async (to, from, next) => {
  const hasUser = store.getters["user/isAuthenticated"];
  const requieresLogin = to.matched.some(record => record.meta.requieresLogin);
  const isRootPath = to.matched.some(record => record.path == "/");

  if (!hasUser && requieresLogin) next({ name: "Login" });
  else if (hasUser && isRootPath) next({ name: "Topics" });
  else next();
});

export default router;
