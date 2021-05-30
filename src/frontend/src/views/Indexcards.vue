<template>
	<el-main style="width: 80%; margin: auto">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item
				class="add-indexcard"
				index="3"
				label="openPopup"
				style="border-bottom: none;"
			>
				<el-tooltip
					class="item"
					effect="dark"
					content="hinzufügen"
					placement="bottom"
				>
					<fa
						@click="openPopup.buttontriggerNewCard = true"
						icon="plus-square"
						size="3x"
						position="bottom-right"
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
			<el-menu-item class="headline" index="1" label="Topics" disabled>
				Deine Übersicht
			</el-menu-item>
		</el-menu>
		<el-scrollbar max-height="550px">
			<el-table
				:data="indexCards"
				:show-header="false"
				style="width: 100%; text-align: center;"
			>
				<el-table-column
					style="display: flex;justify-content: center; text-align: center;"
				>
					<template #default="scope">
						<div class="position-box-card">
							<el-card class="box-card">
								<template #header>
									<div class="card-header">
										Frage:
										<el-tooltip
											class="item"
											effect="dark"
											content="bearbeiten"
											placement="bottom"
										>
											<fa
												icon="pen"
												size="2x"
												@click="
													openDialog(scope.$index, scope.row, 'renameQuestion')
												"
												>Edit</fa
											>
										</el-tooltip>
									</div>

									<div
										class="text-question-answer"
										v-if="!openPopup.buttontriggerRenameQuestion"
									>
										<p>{{ scope.row.question }}</p>
									</div>
									<div>
										<el-form
											v-if="openPopup.buttontriggerRenameQuestion"
											:model="formRenameCard"
											ref="formEl"
											:rules="ruleRenameQuestion"
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
												<div class="button-arrange">
													<el-button
														type="success"
														icon="el-icon-check"
														@click="
															submitNewNameCard(
																formRenameCard.question,
																scope.row.answer
															)
														"
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
									<el-tooltip
										class="item"
										effect="dark"
										content="bearbeiten"
										placement="bottom"
									>
										<fa
											icon="pen"
											size="2x"
											@click="
												openDialog(scope.$index, scope.row, 'renameAnswer')
											"
											>Edit</fa
										>
									</el-tooltip>
								</div>

								<div
									class="text-question-answer"
									v-if="!openPopup.buttontriggerRenameAnswer"
								>
									<p class="pre-formatted">{{ scope.row.answer }}</p>
								</div>
								<div>
									<el-form
										v-if="openPopup.buttontriggerRenameAnswer"
										:model="formRenameCard"
										ref="formEl"
										:rules="ruleRenameAnswer"
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
											<div class="button-arrange">
												<el-button
													type="success"
													icon="el-icon-check"
													@click="
														submitNewNameCard(
															scope.row.question,
															formRenameCard.answer
														)
													"
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
									<el-tooltip
										class="item"
										effect="dark"
										content="merken"
										placement="bottom"
										><div class="favorite-icon">
											<i
												class="el-icon-star-off"
												v-if="!openPopup.buttontriggerAddFavorite"
												@click="addToFavorites(scope.$index, scope.row)"
											></i>

											<i
												class="el-icon-star-on"
												v-if="openPopup.buttontriggerAddFavorite"
												@click="openPopup.buttontriggerAddFavorite = false"
											></i></div
									></el-tooltip>

									<el-tooltip
										class="item"
										effect="dark"
										content="löschen"
										placement="bottom"
									>
										<fa
											icon="trash-alt"
											size="2x"
											@click="openDialog(scope.$index, scope.row, 'delete')"
											>Delete</fa
										>
									</el-tooltip>
								</div>
							</el-card>
						</div>

						<!-- <div class="test">
							<el-card class="box-card">
								<template #header>
									<div class="card-header">
										<span>{{ scope.row.question + 1 }}</span>
										<el-button class="button" type="text"
											>Operation button</el-button
										>
									</div>
								</template>
								<div v-for="o in 4" :key="o" class="text item">
									{{ "List item " + o }}
								</div>
							</el-card>
						</div> -->
					</template>
				</el-table-column>
				<!-- <el-table-column prop="name" label="Name">
					<template #default="scope">
						<div class="test">
							<el-card class="box-card">
								<template #header>
									<div class="card-header">
										<span>{{ scope.row.question }}</span>
										<el-button class="button" type="text"
											>Operation button</el-button
										>
									</div>
								</template>
								<div v-for="o in 4" :key="o" class="text item">
									{{ "List item " + o }}
								</div>
							</el-card>
						</div>
					</template>
				</el-table-column> -->
			</el-table>
		</el-scrollbar>

		<el-dialog
			v-if="openPopup.buttontriggerNewCard"
			title="Neue Karteikarte anlegen"
			v-model="openPopup.buttontriggerNewCard"
			width="50%"
			:show-close="false"
			:close-on-click-modal="false"
			style="font-weight: bold;"
		>
			<el-form
				:model="formNewCard"
				ref="formEl"
				:rules="rulesNewCard"
				:status-icon="true"
				label-width="120px"
				label-position="top"
			>
				<el-form-item prop="question">
					<div class="header-answer-question">Frage:</div>
					<el-input
						type="textarea"
						:autosize="{ minRows: 2 }"
						v-model="formNewCard.question"
						maxlength="100"
						show-word-limit
					></el-input
				></el-form-item>

				<el-form-item prop="answer">
					<div class="header-answer-question">Antwort:</div>
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
					<el-button type="primary" @click="submitNewIndexCard()"
						>Bestätigen</el-button
					>
					<el-button @click="resetForm()">Abbrechen</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>

		<el-dialog
			v-if="openPopup.buttontriggerDeleteCard"
			title="Karteikarte wirklich löschen?"
			v-model="openPopup.buttontriggerDeleteCard"
			width="30%"
			:show-close="false"
			:close-on-click-modal="false"
			center
		>
			<div style="text-align: center">
				<el-button type="primary" @click="submitDeleteIndexCard()" center
					>Bestätigen</el-button
				>
				<el-button @click="openPopup.buttontriggerDeleteCard = false" center
					>Abbrechen</el-button
				>
			</div>
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
		const user = store.state.user.user;

		const config = {
			headers: {
				Authorization: user.tokenType + " " + user.accessToken
			}
		};

		const indexCards = ref([]);
		const route = useRoute();
		const topicId = route.params.id;

		const openPopup = ref({
			buttontriggerNewCard: false,
			buttontriggerRenameQuestion: false,
			buttontriggerRenameAnswer: false,
			buttontriggerDeleteCard: false
		});

		const dialog = reactive({
			index: "",
			row: ""
		});

		const formEl = ref();
		const formNewCard = reactive({
			question: "",
			answer: ""
		});

		const formRenameCard = reactive({
			question: "",
			answer: ""
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

		const ruleRenameQuestion = {
			question: {
				required: true,
				message: "Bitte gib eine Frage ein",
				trigger: "blur"
			}
		};

		const ruleRenameAnswer = {
			answer: {
				required: true,
				message: "Bitte gib eine Antwort ein",
				trigger: "blur"
			}
		};

		function openDialog(index, row, specificPopup) {
			dialog.index = index;
			dialog.row = row;
			formRenameCard.question = dialog.row.question;
			formRenameCard.answer = dialog.row.answer;

			if (specificPopup == "delete") {
				openPopup.value.buttontriggerDeleteCard = true;
			} else if (specificPopup == "renameQuestion") {
				openPopup.value.buttontriggerRenameQuestion = true;
			} else if (specificPopup == "renameAnswer") {
				openPopup.value.buttontriggerRenameAnswer = true;
			}
		}

		function addToFavorites(index, row) {
			console.log("hier");
			dialog.index = index;
			dialog.row = row;
			openPopup.value.buttontriggerAddFavorite = true;
		}

		function resetForm() {
			formEl.value.resetFields();
			openPopup.value.buttontriggerNewCard = false;
			openPopup.value.buttontriggerRenameQuestion = false;
			openPopup.value.buttontriggerRenameAnswer = false;
			openPopup.value.buttontriggerAddFavorite = false;
		}

		function removeObjOfArray(arr, index) {
			var removeIndex = arr.value
				.map(function(item) {
					return item.id;
				})
				.indexOf(index);
			arr.value.splice(removeIndex, 1);
		}

		async function loadIndexCards() {
			console.log("load index cards");
			try {
				let response = await axios.get(`${API}/indexcards/${topicId}`, config);
				console.log(response.data);
				console.log("index cards loaded");
				indexCards.value = response.data;
				console.log(indexCards.value);
			} catch (e) {
				console.error(e);
			}
		}

		async function addNewTopic(question, answer) {
			let payload = { id: null, question: question, answer: answer };
			console.log(payload);
			try {
				let response = await axios.post(
					`${API}/indexcards/${topicId}`,
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

		function submitNewIndexCard() {
			console.log("inside submit");
			console.log(formNewCard.question, formNewCard.question);
			formEl.value.validate((valid) => {
				if (valid) {
					addNewTopic(formNewCard.question, formNewCard.answer).then(() => {
						resetForm();
					});
				}
			});
		}

		async function submitDeleteIndexCard() {
			console.log("deletes index card");
			console.log(dialog.row.id);

			let payload = [
				{
					id: dialog.row.id,
					question: dialog.row.question,
					answer: dialog.row.answer
				}
			];
			console.log(payload);
			try {
				let response = await axios.post(`${API}/indexcards`, payload, config);
				if (response.status === 200) {
					removeObjOfArray(indexCards, dialog.row.id);
					console.log("index card is deleted");
				}
			} catch (e) {
				console.error(e);
			}
			openPopup.value.buttontriggerDeleteCard = false;
		}

		function renameCard(index, question, answer) {
			var objIndex = indexCards.value.findIndex((obj) => obj.id == index);
			indexCards.value[objIndex].question = question;
			indexCards.value[objIndex].answer = answer;
		}

		async function getRenamedCard(question, answer) {
			let payload = {
				id: dialog.row.id,
				question: question,
				answer: answer
			};
			console.log(payload);
			try {
				let response = await axios.put(`${API}/indexcards`, payload, config);
				if (response.status === 200) {
					renameCard(dialog.row.id, question, answer);
					console.log("index card name has changed");
				}
			} catch (e) {
				console.error(e);
			}
		}

		function submitNewNameCard(question, answer) {
			console.log("inside submit");
			console.log(question);
			formEl.value.validate((valid) => {
				if (valid) {
					getRenamedCard(question, answer).then(() => {
						resetForm();
					});
				}
			});
		}

		onBeforeMount(() => {
			return loadIndexCards();
		});

		return {
			indexCards,
			openDialog,
			openPopup,
			submitDeleteIndexCard,
			formEl,
			formNewCard,
			rulesNewCard,
			submitNewIndexCard,
			submitNewNameCard,
			formRenameCard,
			ruleRenameQuestion,
			ruleRenameAnswer,
			resetForm,
			addToFavorites
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
	.fa-caret-square-left {
		color: $icon-color;
		&:hover {
			opacity: $icon-opacity;
		}
	}
}

.el-table {
	font-size: unset;
}

.el-icon-star-on:before,
.el-icon-star-off:before,
.fa-pen,
.fa-trash-alt {
	font-size: $icon-size;
	color: $icon-color;
	&:hover {
		opacity: $icon-opacity;
		cursor: $icon-hover;
	}
}

.header-answer-question {
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 18px;
	font-weight: bold;
}

.position-box-card {
	display: flex;
	justify-content: center;
}

.card-header {
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
}

.card-footer {
	display: flex;
	justify-content: flex-end;
	.favorite-icon {
		padding-right: 10px;
	}
}

.text {
	font-size: 16px;
}

.text-question-answer {
	word-break: normal;
}

.box-card {
	width: 480px;
}

.pre-formatted {
	white-space: pre-wrap;
}

.button-arrange {
	display: flex;
	justify-content: space-between;
}

.go-back-button {
	background-color: #1d2231;
	color: #dfdfdf;
	font-size: 18px;
	&:hover {
		opacity: $icon-opacity;
	}
}

.el-button--default.go-back-button.item {
	background-color: #1d2231;
	color: #dfdfdf;
}
</style>
