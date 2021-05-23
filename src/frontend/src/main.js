import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "element-plus/lib/theme-chalk/index.css";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import ElementPlus from "element-plus";
import { VueCookieNext } from "vue-cookie-next";
import { library } from "@fortawesome/fontawesome-svg-core";
import {
	faFolderPlus,
	faPen,
	faTrashAlt,
} from "@fortawesome/free-solid-svg-icons";

library.add(faFolderPlus, faPen, faTrashAlt);

createApp(App)
	.use(store)
	.use(router)
	.use(ElementPlus)
	.use(VueCookieNext)
	.component("fa", FontAwesomeIcon)
	.mount("#app");
