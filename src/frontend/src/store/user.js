import router from "../router/index";
import axios from "axios";
import { VueCookieNext } from "vue-cookie-next";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com"
		: "http://localhost:7000";

export default {
	namespaced: true,
	state: {
		user: {},
	},
	getters: {
		isAuthenticated: (state) => {
			return (
				VueCookieNext.isCookieAvailable("userToken") &&
				VueCookieNext.isCookieAvailable("userId") &&
				VueCookieNext.isCookieAvailable("userTokenType") &&
				state.user.id &&
				state.user.accessToken &&
				state.user.tokenType
			);
		},
	},
	mutations: {
		setUser(state, user) {
			state.user = user;
		},
	},
	actions: {
		checkCookies({ commit }) {
			if (
				!VueCookieNext.isCookieAvailable("userToken") ||
				!VueCookieNext.isCookieAvailable("userId") ||
				!VueCookieNext.isCookieAvailable("userTokenType")
			) {
				return;
			}
			let user = {
				accessToken: VueCookieNext.getCookie("userToken"),
				id: VueCookieNext.getCookie("userId"),
				tokenType: VueCookieNext.getCookie("userTokenType"),
			};
			commit("setUser", user);
		},
		register({ commit }, { firstname, lastname, email, password }) {
			let payload = {
				firstname: firstname,
				lastname: lastname,
				email: email,
				password: password,
			};
			return axios.post(`${API}/api/register`, payload).then((res) => {
				commit("setUser", res.data);
				if (res.status == 200) {
					router.push({
						name: "Home",
					});
					console.log(res.data);
				}
			});
		},
		login({ commit }, { email, password }) {
			let payload = { email: email, password: password };
			return axios
				.post(`${API}/api/login`, payload)
				.then((res) => {
					commit("setUser", res.data);
					if (res.data.email != null) {
						VueCookieNext.setCookie("userToken", res.data.accessToken, {
							expire: "1d",
							path: "/",
						});
						VueCookieNext.setCookie("userId", res.data.id, {
							expire: "1d",
							path: "/",
						});
						VueCookieNext.setCookie("userTokenType", res.data.tokenType, {
							expire: "1d",
							path: "/",
						});
						router.push({
							name: "Home",
						});
						console.log(res.data);
					}
				})
				.catch((error) => {
					throw error;
				});
		},
		logout({ commit }) {
			return axios.post(`${API}/api/logout`).then((res) => {
				commit("setUser", res.data);
				console.log(res.data);
			});
		},
	},
};
