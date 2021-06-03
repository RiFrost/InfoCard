import axios from "axios";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com/api"
		: "http://localhost:7000/api";

export default {
	namespaced: true,
	state: {
		topic: {}
	},

	getters: {
		getIndexCards: (state) => {
			return state.topic.indexCards;
		},
		getTopic: (state) => {
			return state.topic;
		}
	},

	mutations: {
		setIndexCards(state, indexCards) {
			state.topic.indexCards = indexCards;
		},
		setTopic(state, { id, topicName }) {
			state.topic.id = id;
			state.topic.topicName = topicName;
		}
	},

	actions: {
		setTopic({ commit }, { id, topicName }) {
			if (id !== undefined) {
				commit("setTopic", { id, topicName });
			}
		},
		async loadIndexCards({ state, commit }, { config }) {
			console.log("load index cards");
			try {
				console.log("action: " + state.topic.id);
				let res = await axios.get(
					`${API}/indexcards/${state.topic.id}`,
					config
				);
				console.log(res.data);
				commit("setIndexCards", res.data);
				console.log("index cards loaded");
			} catch (e) {
				console.error(e);
			}
		}
	}
};
