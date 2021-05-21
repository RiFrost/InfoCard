import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "element-plus/lib/theme-chalk/index.css";
import ElementPlus from "element-plus";
import { VueCookieNext } from "vue-cookie-next";

createApp(App)
	.use(store)
	.use(router)
	.use(ElementPlus)
	.use(VueCookieNext)
	.mount("#app");
