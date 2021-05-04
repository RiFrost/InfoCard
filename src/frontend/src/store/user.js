import router from "../router/index";
import axios from "axios";

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
		isAuthenticated: (state) => state.user.email,
	},
	mutations: {
		setUser(state, user) {
			state.user = user;
		},
	},
	actions: {
		register({ commit }, { firstname, lastname, email, password }) {
			let payload = {
				firstname: firstname,
				lastname: lastname,
				email: email,
				password: password,
			};
			return axios.post(API + "/api/register", payload).then((res) => {
				commit("setUser", res.data);
				if (res.data.email != null) {
					router.push({
						name: "Home",
					});
					console.log(res.data);
				}
			});
		},
		login({ commit }, { email, password }) {
			let payload = { email: email, password: password };
			return axios.post(API + "/api/login", payload).then((res) => {
				commit("setUser", res.data);
				if (res.data.email != null) {
					router.push({
						name: "Home",
					});
					console.log(res.data);
				}
			});
		},
		logout({ commit }) {
			return axios.post(API + "/api/logout").then((res) => {
				commit("setUser", res.data);
				console.log(res.data);
			});
		},
	},
};
