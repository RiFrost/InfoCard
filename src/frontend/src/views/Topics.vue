<template>
	<el-main style="width: 80%;  margin: auto;">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item index="2" label="openPopup" style="float: right">
				<el-tooltip
					class="item"
					effect="dark"
					content="Neues Thema anlegen"
					placement="bottom"
				>
					<fa
						@click="openPopup.buttontriggerNewTopic = true"
						icon="folder-plus"
						size="3x"
					/>
				</el-tooltip>
			</el-menu-item>
			<el-menu-item
				index="1"
				label="Topics"
				style="float: left; font-size: 20px; font-weight: bold"
			>
				Deine Themen
			</el-menu-item>
		</el-menu>
		<el-scrollbar max-height="480px">
			<el-table
				:data="topics"
				:show-header="false"
				empty-text="Es gibt noch keine Themen"
				style="width: 100%;"
			>
				<el-table-column type="index" align="center" width="50">
				</el-table-column>
				<el-table-column prop="topicName" width="400"> </el-table-column>
				<el-table-column width="50">
					<template #default="scope">
						<el-tooltip
							class="item"
							effect="dark"
							content="Themenbezeichnung bearbeiten"
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
				<el-table-column align="left">
					<template #default="scope">
						<el-tooltip
							class="item"
							effect="dark"
							content="Thema löschen"
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
					<el-button @click="openPopup.buttontriggerChangeName = false"
						>Abbrechen</el-button
					>
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
						maxlength="30"
						show-word-limit
					></el-input>
				</el-form-item>
				<el-form-item align="center">
					<el-button type="primary" @click="submitNewTopic()"
						>Bestätigen</el-button
					>
					<el-button @click="openPopup.buttontriggerNewTopic = false"
						>Abbrechen</el-button
					>
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
			<div style="text-align:center;">
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

		const openPopup = ref({
			buttontriggerNewTopic: false,
			buttontriggerChangeName: false,
			buttontriggerDeleteTopic: false
		});

		const formEl = ref();
		const form = reactive({
			name: ""
		});

		const rules = {
			name: {
				required: true,
				message: "Bitte gib einen neues Thema ein",
				trigger: "blur"
			}
		};

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
			console.log("Themen werden geladen");
			console.log(user);
			try {
				let response = await axios.get(`${API}/topics/${user.id}`, config);
				console.log("Themen wurden geladen");
				topics.value = response.data;
				console.log(topics.value);
			} catch (e) {
				console.error(e);
			}
		}
		async function submitDeleteTopic() {
			console.log("Topic wird gelöscht");
			let payload = [{ id: dialog.row.id, topicName: dialog.row.topicName }];
			console.log(payload);
			try {
				let response = await axios.post(`${API}/topics`, payload, config);
				console.log("Topic wurde gelöscht");
				console.log(response.status);
			} catch (e) {
				console.error(e);
			}
			loadTopics();
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
				console.log("Thema wurde angelegt");
				console.log(response.data);
			} catch (e) {
				console.error(e);
			}
			loadTopics();
		}

		function submitNewTopic() {
			console.log("inside submit");
			console.log(form.name);
			formEl.value.validate((valid) => {
				if (valid) {
					addNewTopic(form.name);
					form.name = "";
					openPopup.value.buttontriggerNewTopic = false;
				}
			});
		}

		async function getRenamedTopic() {
			console.log(form.name);
			let payload = { id: dialog.row.id, topicName: form.name };
			console.log(payload);
			try {
				let response = await axios.put(`${API}/topics`, payload, config);
				console.log("Topicname has changed");
				console.log(response.status);
				loadTopics();
			} catch (e) {
				console.error(e);
			}
		}

		function submitNewName() {
			console.log("inside submit");
			formEl.value.validate((valid) => {
				if (valid) {
					getRenamedTopic();
					form.name = "";
					openPopup.value.buttontriggerChangeName = false;
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
			dialog
		};
	}
};
</script>
<style lang="scss" scoped>
.el-menu {
	background: #ffffff;
}
</style>
