<template>
	<el-main style="width: 80%; margin: auto; background-color: white">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item
				class="folder"
				index="2"
				label="buttonTrigger"
				style="border-bottom: none"
			>
				<el-tooltip
					class="item"
					effect="dark"
					content="hinzufügen"
					placement="bottom"
				>
					<fa @click="buttonTrigger.add = true" icon="folder-plus" size="3x" />
				</el-tooltip>
			</el-menu-item>
			<el-menu-item
				class="folder"
				index="3"
				label="buttonTrigger"
				style="border-bottom: none"
			>
				<router-link class="router-link-indexcard" to="favorites">
					<el-tooltip
						class="item"
						effect="dark"
						content="zu den Favoriten"
						placement="bottom"
					>
						<i class="el-icon-s-management"></i>
					</el-tooltip>
				</router-link>
			</el-menu-item>

			<el-menu-item class="headline" index="1" label="Topics" disabled>
				Deine Übersicht
			</el-menu-item>
		</el-menu>
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
							:to="{
								name: 'indexcards',
								params: { id: scope.row.id, topicName: scope.row.topicName }
							}"
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
							@click="openDialog(scope.$index, scope.row, 'change')"
							>Edit</fa
						>
					</el-tooltip>
				</template>
			</el-table-column>
			<el-table-column align="center" width="60" style="text-overflow=unset;">
				<template #default="scope">
					<el-popconfirm
						cancelButtonText="Nein"
						confirmButtonText="Ja"
						icon="el-icon-info"
						iconColor="red"
						title="Wirklich Thema und Karteikarten löschen?"
						@confirm="submitDelete(scope.$index, scope.row)"
					>
						<template #reference>
							<fa
								@mouseover="buttonTrigger.delete = true"
								icon="trash-alt"
							></fa>
							<el-tooltip
								v-if="buttonTrigger.delete"
								class="item"
								effect="dark"
								content="löschen"
								placement="bottom"
							>
								<fa icon="trash-alt"> </fa
							></el-tooltip>
						</template>
					</el-popconfirm>
				</template>
			</el-table-column>
		</el-table>

		<el-dialog
			v-if="buttonTrigger.rename"
			title="Themenbezeichnung überarbeiten"
			v-model="buttonTrigger.rename"
			width="30%"
			:show-close="false"
			:close-on-click-modal="false"
		>
			<el-form
				:model="form"
				ref="formCheck"
				:rules="rules"
				:status-icon="true"
				label-width="120px"
				label-position="top"
			>
				<el-form-item prop="topicName">
					<el-input
						v-model="form.topicName"
						maxlength="30"
						show-word-limit
					></el-input>
				</el-form-item>
				<el-form-item align="center">
					<el-button type="primary" @click="submitRenaming()"
						>Bestätigen</el-button
					>
					<el-button @click="resetForm()">Abbrechen</el-button>
				</el-form-item>
			</el-form>
		</el-dialog>

		<el-dialog
			v-if="buttonTrigger.add"
			title="Neues Thema"
			v-model="buttonTrigger.add"
			width="30%"
			:show-close="false"
			:close-on-click-modal="false"
		>
			<el-form
				:model="form"
				ref="formCheck"
				:rules="rules"
				:status-icon="true"
				label-width="120px"
				label-position="top"
			>
				<el-form-item prop="topicName">
					<el-input
						v-model="form.topicName"
						maxlength="40"
						show-word-limit
					></el-input>
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

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com/api"
		: "http://localhost:7000/api";

export default {
	topicName: "Topics",

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

		const formCheck = ref();

		const form = reactive({
			topicName: ""
		});

		const buttonTrigger = ref({
			add: false,
			rename: false,
			delete: false
		});

		const rules = {
			topicName: {
				required: true,
				message: "Bitte gib ein neues Thema ein",
				trigger: "blur"
			}
		};

		function resetForm() {
			// resettet nicht bei folgendem Szenario: Topicname bearbeiten => anschließend neues Topic erstellen, Name vorherigen Topics noch eingetragen??
			formCheck.value.resetFields();
			buttonTrigger.value.add = false;
			buttonTrigger.value.rename = false;
			form.topicName = "";
		}

		function removeTopic(index) {
			topics.value.splice(index, 1);
		}

		// function renameTopic() {
		// 	topics.value[dialog.index].topicName = form.topicName;
		// }

		function openDialog(index, row) {
			dialog.index = index;
			dialog.row = row;
			form.topicName = dialog.row.topicName;
			buttonTrigger.value.rename = true;
		}

		async function loadTopics() {
			console.log("load topics");
			axios
				.get(`${API}/topics/${user.id}`, config)
				.then((response) => {
					if (response.status.valueOf(200)) {
						console.log("topics loaded");
						topics.value = response.data;
					}
				})
				.catch((err) => {
					console.log(err);
				});
		}

		async function submitDelete(index, row) {
			console.log("deletes topic");
			let payload = [row.id];
			axios
				.post(`${API}/topics`, payload, config)
				.then((response) => {
					if (response.status.valueOf(200)) {
						removeTopic(index);
						console.log("topic is deleted");
					}
				})
				.catch((err) => {
					console.log(err);
				});
		}

		async function addTopic(topicName) {
			let payload = { id: 0, topicName: topicName };
			axios
				.post(`${API}/topics/${user.id}`, payload, config)
				.then((response) => {
					if (response.status.valueOf(200)) {
						topics.value.push(response.data);
						console.log("Topic was added");
					}
				})
				.catch((err) => {
					console.log(err);
				});
		}

		function submitAdding() {
			console.log("inside submit");
			formCheck.value.validate((valid) => {
				if (valid) {
					addTopic(form.topicName);
					resetForm();
				}
			});
		}

		async function getRenamedTopic() {
			let payload = { id: dialog.row.id, topicName: form.topicName };
			axios
				.put(`${API}/topics`, payload, config)
				.then((response) => {
					if (response.status.valueOf(200)) {
						topics.value[dialog.index].topicName = response.data.topicName;
						console.log("Topicname has changed");
					}
				})
				.catch((err) => {
					console.log(err);
				});
		}

		function submitRenaming() {
			console.log("inside submit");
			formCheck.value.validate((valid) => {
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
			submitDelete,
			submitAdding,
			submitRenaming,
			rules,
			addTopic,
			buttonTrigger,
			form,
			formCheck,
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

.el-icon-s-management:before,
.el-icon-s-management {
	color: $icon-color;
	font-size: xxx-large;

	&:hover {
		opacity: $icon-opacity;
	}
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

.el-popconfirm__main {
	background-color: red;
	display: flex !important;
	flex-direction: row-reverse !important;
	text-align: unset;
}
</style>
