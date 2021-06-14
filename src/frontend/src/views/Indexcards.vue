<template>
	<el-main style="width: 80%; margin: auto;">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item
				class="add-indexcard"
				index="3"
				label="buttonTrigger"
				style="border-bottom: none;"
			>
				<el-tooltip
					class="item"
					effect="dark"
					content="hinzufügen"
					placement="bottom"
				>
					<fa
						@click="buttonTrigger.newCard = true"
						icon="plus-square"
						size="3x"
					/>
				</el-tooltip>
			</el-menu-item>
			<el-menu-item
				class="go-back"
				index="2"
				label="backwards"
				style="border-bottom: none;"
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
				Karteikarten zum Thema:
				{{ topic.topicName }}
			</el-menu-item>
		</el-menu>
		<div class="empty-cards-text" v-if="indexCards.length == 0">
			<h3>Es gibt noch keine Karteikarten</h3>
		</div>
		<div class="grid-container">
			<div
				class="inner-grid"
				v-for="(indexCard, index) in indexCards"
				v-bind:key="index"
			>
				<el-card class="box-card">
					<template #header>
						<div class="card-header">
							Frage:
							<div
								v-if="
									!buttonTrigger.renameQuestion && !buttonTrigger.renameAnswer
								"
							>
								<el-tooltip
									class="item"
									effect="dark"
									content="bearbeiten"
									placement="bottom"
								>
									<fa icon="pen" @click="openDialog(index, 'renameQuestion')">
										Edit
									</fa>
								</el-tooltip>
							</div>
							<div
								v-if="
									buttonTrigger.renameQuestion || buttonTrigger.renameAnswer
								"
							>
								<fa class="test" icon="pen" style="cursor: not-allowed;"
									>Edit</fa
								>
							</div>
						</div>
						<div
							class="text-question"
							v-if="
								!(
									buttonTrigger.renameQuestion &&
									card.indexCard.id == indexCard.id
								)
							"
						>
							<p>{{ indexCard.question }}</p>
						</div>
						<div>
							<el-form
								v-if="
									buttonTrigger.renameQuestion &
										(card.indexCard.id == indexCard.id)
								"
								:model="formRenameCard"
								ref="formCheck"
								:rules="rulesRenameQuestion"
								:status-icon="true"
								label-width="120px"
								label-position="top"
							>
								<el-form-item prop="question">
									<el-input
										v-model="formRenameCard.question"
										:autosize="{ minRows: 2 }"
										maxlength="100"
										show-word-limit
									></el-input
								></el-form-item>
								<el-form-item align="center">
									<div class="form-input-button-arrange">
										<el-button
											type="success"
											icon="el-icon-check"
											@click="submitRenaming()"
											circle
										></el-button>
										<el-button
											type="danger"
											icon="el-icon-close"
											@click="resetForm()"
											circle
										></el-button>
									</div>
								</el-form-item>
							</el-form>
						</div>
					</template>
					<div class="card-header">
						Antwort:
						<div
							v-if="
								!buttonTrigger.renameQuestion && !buttonTrigger.renameAnswer
							"
						>
							<el-tooltip
								class="item"
								effect="dark"
								content="bearbeiten"
								placement="bottom"
							>
								<fa icon="pen" @click="openDialog(index, 'renameAnswer')">
									Edit
								</fa>
							</el-tooltip>
						</div>
						<div
							class="not-clickable"
							v-if="buttonTrigger.renameQuestion || buttonTrigger.renameAnswer"
						>
							<fa icon="pen" style="cursor: not-allowed;">Edit</fa>
						</div>
					</div>
					<div
						class="text-answer"
						v-if="
							!(buttonTrigger.renameAnswer && card.indexCard.id == indexCard.id)
						"
					>
						<el-scrollbar height="100%">
							<p class="pre-formatted">{{ indexCard.answer }}</p></el-scrollbar
						>
					</div>
					<div>
						<el-form
							v-if="
								buttonTrigger.renameAnswer && card.indexCard.id == indexCard.id
							"
							:model="formRenameCard"
							ref="formCheck"
							:rules="rulesRenameAnswer"
							:status-icon="true"
							label-width="120px"
							label-position="top"
						>
							<el-form-item prop="answer">
								<el-input
									type="textarea"
									v-model="formRenameCard.answer"
									maxlength="400"
									:autosize="{ minRows: 4, maxRows: 6 }"
									show-word-limit
								></el-input
							></el-form-item>
							<el-form-item align="center">
								<div class="form-input-button-arrange">
									<el-button
										type="success"
										icon="el-icon-check"
										@click="submitRenaming()"
										circle
									></el-button>
									<el-button
										type="danger"
										icon="el-icon-close"
										@click="resetForm()"
										circle
									></el-button>
								</div>
							</el-form-item>
						</el-form>
					</div>
					<div class="card-footer">
						<div class="favorite-icon" v-if="!buttonTrigger.addFavorite">
							<el-tooltip
								class="item"
								effect="dark"
								content="merken"
								placement="bottom"
							>
								<i
									class="el-icon-star-off"
									@click="addToFavorites(indexCard)"
								></i>
							</el-tooltip>
						</div>
						<div class="favorite-icon" v-if="buttonTrigger.addFavorite">
							<el-tooltip
								class="item"
								effect="dark"
								content="nicht mehr merken"
								placement="bottom"
							>
								<i
									class="el-icon-star-on"
									@click="buttonTrigger.addFavorite = false"
								></i>
							</el-tooltip>
						</div>
						<el-popconfirm
							cancelButtonText="Nein"
							confirmButtonText="Ja"
							icon="el-icon-info"
							iconColor="red"
							title="Wirklich Thema und Karteikarten löschen?"
							@confirm="submitDelete(index)"
						>
							<template #reference>
								<fa icon="trash-alt"></fa>
							</template>
						</el-popconfirm>
					</div>
				</el-card>
			</div>
		</div>

		<!-- Popup for adding new index cards  -->
		<el-dialog
			v-if="buttonTrigger.newCard"
			title="Neue Karteikarte anlegen"
			v-model="buttonTrigger.newCard"
			width="40%"
			:show-close="false"
			:close-on-click-modal="false"
			style="font-weight: bold;"
		>
			<el-form
				:model="formNewCard"
				ref="formCheck"
				:rules="rulesNewCard"
				:status-icon="true"
				label-width="120px"
				label-position="top"
			>
				<el-form-item prop="question">
					<div class="header-text-answer-question">Frage:</div>
					<el-input
						type="textarea"
						:autosize="{ minRows: 2 }"
						v-model="formNewCard.question"
						maxlength="100"
						show-word-limit
					></el-input
				></el-form-item>
				<el-form-item prop="answer">
					<div class="header-text-answer-question">Antwort:</div>
					<el-input
						type="textarea"
						:autosize="{ minRows: 4, maxRows: 6 }"
						v-model="formNewCard.answer"
						maxlength="400"
						show-word-limit
					>
					</el-input>
				</el-form-item>
				<el-form-item align="center">
					<el-button type="primary" @click="submitAdding()"
						>Bestätigen</el-button
					>
					<el-button @click="resetForm()">Abbrechen</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>
	</el-main>
</template>

<script>
import axios from "axios";
import { ref, reactive, onBeforeMount } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com/api"
		: "http://localhost:7000/api";

export default {
	name: "Indexcards",

	setup() {
		const store = useStore();
		const route = useRoute();
		const user = store.state.user.user;
		const topic = store.state.topic.topic;
		const indexCards = ref([]);
		const card = ref({
			index: {},
			indexCard: {
				id: 0,
				question: "",
				answer: ""
			}
		});

		const config = {
			headers: {
				Authorization: user.tokenType + " " + user.accessToken
			}
		};

		const formCheck = ref();

		const formNewCard = reactive({
			question: "",
			answer: ""
		});

		const formRenameCard = reactive({
			question: "",
			answer: ""
		});

		const buttonTrigger = ref({
			newCard: false,
			renameQuestion: false,
			renameAnswer: false,
			deleteCard: false
		});

		const rulesNewCard = {
			question: [
				{
					required: true,
					message: "Bitte gib eine neue Frage ein",
					trigger: "blur"
				}
			],
			answer: [
				{
					required: true,
					message: "Bitte gib eine Antwort ein",
					trigger: "blur"
				}
			]
		};

		const rulesRenameQuestion = {
			question: {
				required: true,
				message: "Bitte gib eine Frage ein",
				trigger: "blur"
			}
		};

		const rulesRenameAnswer = {
			answer: {
				required: true,
				message: "Bitte gib eine Antwort ein",
				trigger: "blur"
			}
		};

		function openDialog(index, specificPopup) {
			card.value.index = index;
			card.value.indexCard = indexCards.value[index];
			console.log(card.value);
			if (specificPopup == "renameQuestion") {
				buttonTrigger.value.renameQuestion = true;
				formRenameCard.question = card.value.indexCard.question;
			} else if (specificPopup == "renameAnswer") {
				buttonTrigger.value.renameAnswer = true;
				formRenameCard.answer = card.value.indexCard.answer;
			}
		}

		// für spätere Funktion
		function addToFavorites(indexCard) {
			card.value.indexCard = indexCard;
			buttonTrigger.value.addFavorite = true;
		}

		function resetForm() {
			formCheck.value.resetFields();
			buttonTrigger.value.newCard = false;
			buttonTrigger.value.renameQuestion = false;
			buttonTrigger.value.renameAnswer = false;
		}

		function removeObjOfArray(index) {
			indexCards.value.splice(index, 1);
		}

		function renameCard() {
			if (formRenameCard.answer === "") {
				indexCards.value[card.value.index].question = formRenameCard.question;
			} else {
				indexCards.value[card.value.index].answer = formRenameCard.answer;
			}
		}

		async function addNewIndexCard(question, answer) {
			let payload = { id: 0, question: question, answer: answer };
			console.log(payload);
			try {
				let response = await axios.post(
					`${API}/indexcards/${topic.id}`,
					payload,
					config
				);
				console.log(response.data);
				indexCards.value.push(response.data);
				console.log("index card was added");
			} catch (e) {
				console.error(e);
			}
		}

		function submitAdding() {
			console.log("inside submit");
			console.log(formNewCard.question, formNewCard.question);
			formCheck.value.validate((valid) => {
				if (valid) {
					addNewIndexCard(formNewCard.question, formNewCard.answer).then(() => {
						resetForm();
					});
				}
			});
		}

		async function submitDelete(index) {
			console.log("inside submitDelete");
			let payload = [indexCards.value[index].id];
			console.log(payload);
			try {
				let response = await axios.post(`${API}/indexcards`, payload, config);
				console.log(response.data.ok);
				if (response.status.valueOf(200)) {
					console.log(response.data);
					removeObjOfArray(index);
					console.log("index card is deleted");
				}
			} catch (e) {
				console.error(e);
			}
			buttonTrigger.value.deleteCard = false;
		}

		async function getRenamedCard() {
			let payload = {
				id: card.value.indexCard.id,
				question: "",
				answer: ""
			};
			if (formRenameCard.question === "") {
				payload.question = card.value.indexCard.question;
				payload.answer = formRenameCard.answer;
			} else {
				payload.question = formRenameCard.question;
				payload.answer = card.value.indexCard.answer;
			}
			console.log(payload);
			try {
				let response = await axios.put(`${API}/indexcards`, payload, config);
				if (response.status.valueOf(200)) {
					renameCard();
					console.log("index card name has changed");
				}
			} catch (e) {
				console.error(e);
			}
		}

		function submitRenaming() {
			console.log("inside submitRenaming");
			formCheck.value.validate((valid) => {
				if (valid) {
					getRenamedCard().then(() => {
						resetForm();
					});
				}
			});
		}

		onBeforeMount(() => {
			store.dispatch("topic/setTopic", {
				id: route.params.id,
				topicName: route.params.topicName
			});
			store
				.dispatch("topic/loadIndexCards", {
					config: config
				})
				.then(() => {
					indexCards.value = store.getters["topic/getIndexCards"];
					console.log(indexCards.value);
					console.log(topic);
				});
		});

		return {
			indexCards,
			openDialog,
			buttonTrigger,
			submitDelete,
			formCheck,
			formNewCard,
			rulesNewCard,
			submitAdding,
			submitRenaming,
			formRenameCard,
			rulesRenameQuestion,
			rulesRenameAnswer,
			resetForm,
			addToFavorites,
			topic,
			card
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

	.el-menu-item.add-indexcard {
		float: right;
		top: 50%;
		.fa-plus-square {
			color: $icon-color;
			&:hover {
				opacity: $icon-opacity;
			}
		}
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

		.fa-caret-square-left {
			color: $icon-color;
			&:hover {
				opacity: $icon-opacity;
			}
		}
	}
}

.el-icon-star-off,
.el-icon-star-on,
.fa-pen,
.fa-trash-alt {
	font-size: $icon-size;
	color: $icon-color;
	&:hover {
		opacity: $icon-opacity;
		cursor: $icon-hover;
	}
}

.form-input-button-arrange {
	display: flex;
	justify-content: space-between;
}

.grid-container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;

	.inner-grid {
		flex: 0 0 calc(33.33% - 20px);
		padding: 20px;
		margin: 10px;
	}
}

// * {
// 	box-sizing: border-box;
// }

.box-card {
	width: 480px;
	height: 100%;
	background-color: floralwhite;
	word-break: normal;

	.card-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 18px;
		font-weight: bold;
	}

	.header-text-answer-question {
		display: flex;
		justify-content: space-between;
		align-items: center;
		font-size: 18px;
		font-weight: bold;
	}

	.el-card__body {
		display: flex;
		justify-content: space-between;
		align-items: center;

		.text-answer {
			height: 150px;

			.pre-formatted {
				white-space: pre-wrap;
			}
		}

		.card-footer {
			display: flex;
			justify-content: flex-end;

			.favorite-icon {
				padding-right: 10px;
			}
		}
	}
}
</style>
