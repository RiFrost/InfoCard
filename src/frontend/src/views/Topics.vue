<template>
	<el-main style="width: 80%; margin: auto">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item
				class="folder"
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
						@click="openPopup.buttontriggerNewTopic = true"
						icon="folder-plus"
						size="3x"
					/>
				</el-tooltip>
			</el-menu-item>
			<el-menu-item class="headline" index="2" label="Topics" disabled>
				Deine Übersicht
			</el-menu-item>
		</el-menu>
		<el-scrollbar max-height="550px">
			<el-table
				:data="topics"
				empty-text="Es gibt noch keine Themen"
				style="width: 100%"
			>
				<el-table-column type="index" align="center" width="50">
				</el-table-column>

				<el-table-column
					type="link"
					class="text-column"
					label="Thema"
					prop="topicName"
					width="400"
				>
					<template #default="scope">
						<el-tooltip
							class="item"
							effect="dark"
							content="auswählen"
							placement="bottom"
						>
							<router-link
								class="router-link-indexcard"
								:to="{ name: 'indexcards', params: { id: scope.row.id } }"
								>{{ scope.row.topicName }}</router-link
							>
						</el-tooltip>
					</template>
				</el-table-column>

				<el-table-column
					class="text-column"
					label="Karteikartenanzahl"
					prop="indexCardCount"
					width="200"
					align="center"
				>
				</el-table-column>
				<el-table-column align="right">
					<template #default="scope">
						<el-tooltip
							class="item"
							effect="dark"
							content="bearbeiten"
							placement="bottom"
						>
							<fa
								icon="pen"
								size="2x"
								@click="openDialog(scope.$index, scope.row, 'change')"
								>Edit</fa
							>
						</el-tooltip>
					</template>
				</el-table-column>
				<el-table-column align="center" width="60" style="text-overflow=unset;">
					<template #default="scope">
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
					</template>
				</el-table-column>
			</el-table>
		</el-scrollbar>

		<el-dialog
			v-if="openPopup.buttontriggerChangeName"
			title="Themenbezeichnung überarbeiten"
			v-model="openPopup.buttontriggerChangeName"
			width="30%"
			:show-close="false"
			:close-on-click-modal="false"
		>
			<el-form
				:model="form"
				ref="formEl"
				:rules="rules"
				:status-icon="true"
				label-width="120px"
				label-position="top"
			>
				<el-form-item prop="name">
					<el-input
						v-model="form.name"
						maxlength="30"
						show-word-limit
					></el-input>
				</el-form-item>
				<el-form-item align="center">
					<el-button type="primary" @click="submitNewName()"
						>Bestätigen</el-button
					>
					<el-button @click="resetForm()">Abbrechen</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>

		<el-dialog
			v-if="openPopup.buttontriggerNewTopic"
			title="Neues Thema"
			v-model="openPopup.buttontriggerNewTopic"
			width="30%"
			:show-close="false"
			:close-on-click-modal="false"
		>
			<el-form
				:model="form"
				ref="formEl"
				:rules="rules"
				:status-icon="true"
				label-width="120px"
				label-position="top"
			>
				<el-form-item prop="name">
					<el-input
						v-model="form.name"
						maxlength="40"
						show-word-limit
					></el-input>
				</el-form-item>
				<el-form-item align="center">
					<el-button type="primary" @click="submitNewTopic()"
						>Bestätigen</el-button
					>
					<el-button @click="resetForm()">Abbrechen</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>

		<el-dialog
			v-if="openPopup.buttontriggerDeleteTopic"
			title="Thema inklusive Karteikarten wirklich löschen?"
			v-model="openPopup.buttontriggerDeleteTopic"
			width="30%"
			:show-close="false"
			:close-on-click-modal="false"
			center
		>
			<div style="text-align: center">
				<el-button type="primary" @click="submitDeleteTopic()" center
					>Bestätigen</el-button
				>
				<el-button @click="openPopup.buttontriggerDeleteTopic = false" center
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

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com/api"
		: "http://localhost:7000/api";

export default {
	name: "Topics",
	components: {
		//    Popup: Popup,
	},

	setup() {
		const topics = ref([]);
		const store = useStore();
		const user = store.state.user.user;

		const dialog = reactive({
			index: "",
			row: ""
		});

		const config = {
			headers: {
				Authorization: user.tokenType + " " + user.accessToken
			}
		};

		const formEl = ref();
		const form = reactive({
			name: ""
		});

		const openPopup = ref({
			buttontriggerNewTopic: false,
			buttontriggerChangeName: false,
			buttontriggerDeleteTopic: false
		});

		const rules = {
			name: {
				required: true,
				message: "Bitte gib ein neues Thema ein",
				trigger: "blur"
			}
		};

		function resetForm() {
			formEl.value.resetFields();
			openPopup.value.buttontriggerNewTopic = false;
			openPopup.value.buttontriggerChangeName = false;
		}

		function removeTopic(index) {
			var removeIndex = topics.value
				.map(function(item) {
					return item.id;
				})
				.indexOf(index);
			topics.value.splice(removeIndex, 1);
		}

		function renameTopic(index, name) {
			var objIndex = topics.value.findIndex((obj) => obj.id == index);
			topics.value[objIndex].topicName = name;
		}

		function openDialog(index, row, specificPopup) {
			dialog.index = index;
			dialog.row = row;
			if (specificPopup == "delete") {
				openPopup.value.buttontriggerDeleteTopic = true;
			} else if (specificPopup == "change") {
				openPopup.value.buttontriggerChangeName = true;
			}
		}

		async function loadTopics() {
			console.log("load topics");
			console.log(user);
			try {
				let response = await axios.get(`${API}/topics/${user.id}`, config);
				console.log("topics loaded");
				topics.value = response.data;
				console.log(topics.value);
			} catch (e) {
				console.error(e);
			}
		}

		async function submitDeleteTopic() {
			console.log("deletes topic");
			console.log(dialog.row.id);

			let payload = [{ id: dialog.row.id, topicName: dialog.row.topicName }];
			console.log(payload);
			try {
				let response = await axios.post(`${API}/topics`, payload, config);
				if (response.status === 200) {
					removeTopic(dialog.row.id);
					console.log("topic is deleted");
				}
			} catch (e) {
				console.error(e);
			}
			openPopup.value.buttontriggerDeleteTopic = false;
		}

		async function addNewTopic(name) {
			let payload = { id: null, topicName: name };
			console.log(payload);
			try {
				let response = await axios.post(
					`${API}/topics/${user.id}`,
					payload,
					config
				);
				console.log(response.data);
				topics.value.push(response.data);
				console.log("Topic was added");
			} catch (e) {
				console.error(e);
			}
		}

		function submitNewTopic() {
			console.log("inside submit");
			console.log(form.name);
			formEl.value.validate((valid) => {
				if (valid) {
					addNewTopic(form.name);
					resetForm();
				}
			});
		}

		async function getRenamedTopic() {
			console.log(form.name);
			let payload = { id: dialog.row.id, topicName: form.name };
			console.log(payload);
			try {
				let response = await axios.put(`${API}/topics`, payload, config);
				if (response.status === 200) {
					renameTopic(dialog.row.id, form.name);
					console.log("Topicname has changed");
				}
			} catch (e) {
				console.error(e);
			}
		}

		function submitNewName() {
			console.log("inside submit");
			formEl.value.validate((valid) => {
				if (valid) {
					getRenamedTopic().then(() => {
						resetForm();
					});
				}
			});
		}

		onBeforeMount(() => {
			return loadTopics();
		});

		return {
			loadTopics,
			topics,
			submitDeleteTopic,
			submitNewTopic,
			submitNewName,
			rules,
			addNewTopic,
			openPopup,
			form,
			formEl,
			openDialog,
			dialog,
			resetForm
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

.fa-pen,
.fa-trash-alt {
	font-size: $icon-size;
	color: $icon-color;
	&:hover {
		opacity: $icon-opacity;
		cursor: $icon-hover;
	}
}

.el-table {
	background: #ffffff;
	font-size: 17px;
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

.el-menu-item.folder {
	font-size: 20 !important;
	float: right;
	.fa-folder-plus {
		color: $icon-color;
		&:hover {
			opacity: $icon-opacity;
		}
	}
}

.router-link-indexcard {
	color: rgb(96, 98, 102);
	text-decoration: none;
	&:hover {
		font-weight: bold;
		opacity: $icon-opacity;
	}
}
</style>
