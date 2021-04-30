import router from "../router/index";

export default {
	namespaced: true,
	state: {
		user: {},
	},
	getters: {
		isAuthenticated: (state) => state.user.username,
	},
	mutations: {
		setUser(state, user) {
			state.user = user;
		},
	},
	actions: {
		login({ commit }, { username, password }) {
			return fetch
				.post(
					"api/login",
					{},
					{
						params: {
							username,
							password,
						},
					}
				)
				.then((res) => {
					commit("setUser", res.data);
					if (res.data.username != null) {
						router.push({
							name: "Dashboard",
						});
					}
				});
		},
		logout({ commit }) {
			return fetch.post("api/logout").then((res) => {
				commit("setUser", res.data);
				console.log(res.data);
			});
		},
	},
};
