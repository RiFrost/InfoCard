<template>
	<el-main style="width: 80%; margin: auto">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item
				class="go-back"
				index="2"
				label="backwards"
				style="border-bottom: none"
			>
				<router-link class="router-link-indexcard" to="topics">
					<el-tooltip
						class="item"
						effect="dark"
						content="zurück zu den Themen"
						placement="bottom"
					>
						<el-button class="go-back-button" icon="el-icon-caret-left"
							>Back</el-button
						>
					</el-tooltip>
				</router-link>
			</el-menu-item>
			<el-menu-item class="headline" index="1" label="IndexCard" disabled>
				Deine Favoriten
			</el-menu-item>
		</el-menu>

		<div id="flashcard-app" class="container">
			<ul class="card-list">
				<li
					class="list-item"
					v-on:click="toggleCard(index)"
					v-for="(card, index) in indexCards"
					v-bind:key="index"
				>
					<transition name="flip">
						<p v-bind:key="card.flipped" class="card">
							<el-scrollbar height="100%">
								{{
									card.flipped
										? "Frage: " + card.question
										: "Antwort: " + card.answer
								}}
							</el-scrollbar>
							<el-tooltip
								class="item"
								effect="dark"
								content="nicht mehr merken"
								placement="bottom"
							>
								<span v-on:click="removeFavoriteCard(index)" class="delete-card"
									>X</span
								>
							</el-tooltip>
						</p>
					</transition>
				</li>
			</ul>
		</div>
	</el-main>
</template>

<script>
import axios from "axios";
import { ref, onBeforeMount } from "vue";
import { useStore } from "vuex";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com/api"
		: "http://localhost:7000/api";

export default {
	name: "Favorites",

	setup() {
		const store = useStore();
		const user = store.state.user.user;
		const indexCards = ref([]);

		const config = {
			headers: {
				Authorization: user.tokenType + " " + user.accessToken
			}
		};

		function toggleCard(index) {
			console.log("inside toggleCard");
			indexCards.value[index].flipped = !indexCards.value[index].flipped;
		}

		async function removeFavoriteCard(index) {
			let payload = {
				id: indexCards.value[index].id,
				question: indexCards.value[index].question,
				answer: indexCards.value[index].answer,
				isFavored: false
			};
			axios
				.put(`${API}/indexcards`, payload, config)
				.then((response) => {
					if (response.status.valueOf(200)) {
						indexCards.value.splice(index, 1);
					}
				})
				.catch((err) => {
					indexCards.value[index].favored = !indexCards.value[index].favored;
					console.log(err);
				});
		}

		async function loadIndexCards() {
			console.log("load index cards");
			axios
				.get(`${API}/favorites/${user.id}`, config)
				.then((response) => {
					if (response.status.valueOf(200)) {
						indexCards.value = response.data;
						indexCards.value.forEach(function(element) {
							element.flipped = "false";
						});
						console.log("index cards loaded");
					}
				})
				.catch((err) => {
					console.log(err);
				});
		}

		onBeforeMount(() => {
			loadIndexCards();
		});

		return {
			indexCards,
			toggleCard,
			removeFavoriteCard
		};
	}
};
</script>

<style lang="scss" scoped>
$icon-color: #1d2231;
$icon-hover: pointer;
$icon-opacity: 0.7;
$icon-size: 1.5rem;

.el-main {
	padding: 0;
	-webkit-box-shadow: 4px 5px 15px 5px rgba(0, 0, 0, 0.09);
	box-shadow: 4px 5px 15px 5px rgba(0, 0, 0, 0.09);
	background-color: #fff;

	.empty-cards-text {
		text-align: center;
	}

	.el-menu-item {
		font-size: unset;
	}

	.el-menu-item.is-disabled.headline {
		float: left;
		font-size: 22px;
		font-weight: bold;
		cursor: context-menu;
		opacity: 1;
	}

	.el-menu-item.go-back {
		float: right;
		top: 50%;

		.el-button--default.go-back-button.item {
			background-color: #1d2231;
			color: #dfdfdf;
			font-size: 18px;

			&:hover {
				opacity: $icon-opacity;
			}
		}
	}
}

.card-list {
	padding-left: 0;
	display: flex;
	flex-flow: row wrap;
	justify-content: space-around;
	align-items: inherit;
}

.list-item {
	list-style-type: none;
	padding: 10px 10px;
	margin-bottom: 30px;
	transition: all 0.3s ease;
}

.container {
	max-width: 100%;
}

.card {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 480px;
	height: 240px;
	padding: 18px 20px;
	background-color: floralwhite;
	border-radius: 7px;
	margin: 5px;
	white-space: pre-wrap;
	line-height: 27px;
	cursor: pointer;
	position: relative;
	color: black;

	font-size: 16px;
	-webkit-box-shadow: 9px 10px 22px -8px rgba(209, 193, 209, 0.5);
	-moz-box-shadow: 9px 10px 22px -8px rgba(209, 193, 209, 0.5);
	box-shadow: 9px 10px 22px -8px rgba(209, 193, 209, 0.5);
	will-change: transform;
}

li:hover {
	transform: scale(1.1);
}

.delete-card {
	position: absolute;
	right: 0;
	top: 0;
	padding: 10px 15px;
	opacity: 0.4;
	color: #1d2231;
	transition: all 0.5s ease;
}

.delete-card:hover {
	opacity: 1;
	transform: rotate(360deg);
}

.flip-enter-active {
	transition: all 0.4s ease;
}

.flip-leave-active {
	display: none;
}

.flip-enter,
.flip-leave {
	transform: rotateY(180deg);
	opacity: 0;
}

/* Form */
.flashcard-form {
	position: relative;
}

label {
	font-weight: 200;
	color: #333;
	margin-right: 10px;
}

input {
	border-radius: 5px;
	border: 2px solid #eaeaea;
	padding: 10px;
	outline: none;
}

button {
	border-radius: 5px;
	border: 1px solid #87cb84;
	background-color: #87cb84;
	padding: 8px 15px;
	outline: none;
	font-size: 14px;
	font-weight: 700;
	color: #fff;
	cursor: pointer;
	transition: all 0.3s ease;
}

button:hover {
	background-color: #70a66f;
}
</style>
