<template>
	<el-main style="width: 80%;  margin: auto;">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item index="2" label="openPopup" style="float: right">
				<fa
					@click="openPopup.buttontrigger = true"
					icon="folder-plus"
					size="3x"
				/>

				<el-dialog
					v-if="openPopup.buttontrigger"
					title="Neues Thema"
					v-model="openPopup"
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
							<el-button type="primary" @click="submitForm()"
								>Bestätigen</el-button
							>
							<el-button @click="openPopup.buttontrigger = false"
								>Abbrechen</el-button
							>
						</el-form-item>
					</el-form>
				</el-dialog>
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
				empty-text="Du hast noch keine Themen angelegt"
				style="width: 100%;"
			>
				<el-table-column type="index" align="center" width="50">
				</el-table-column>
				<el-table-column prop="topicName" width="400"> </el-table-column>
				<el-table-column width="50">
					<template #default="scope">
						<fa
							icon="pen"
							size="2x"
							@click="handleEdit(scope.$index, scope.row)"
							>Edit</fa
						>
					</template>
				</el-table-column>
				<el-table-column align="left">
					<template #default="scope">
						<fa
							icon="trash-alt"
							size="2x"
							@click="handleDelete(scope.$index, scope.row)"
							>Delete</fa
						>
					</template>
				</el-table-column>
			</el-table>
		</el-scrollbar>
	</el-main>
</template>

<script>
import axios from "axios";
import { ref, reactive, onBeforeMount } from "vue";
import { useStore } from "vuex";
const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com"
		: "http://localhost:7000";

export default {
	name: "Test",

	components: {
		//    Popup: Popup,
	},

	methods: {
		handleEdit(index, row) {
			console.log(index, row);
		}
	},

	setup() {
		const topics = ref([]);
		const store = useStore();
		const user = store.state.user.user;
		const openPopup = ref({
			buttontrigger: false
		});

		const formEl = ref();
		const form = reactive({
			name: ""
		});

		const rules = {
			name: [
				{
					required: true,
					message: "Bitte gib einen neues Thema ein",
					trigger: "blur"
				}
			]
		};

		function sortTopics() {
			topics.value.sort((a, b) => a.id - b.id);
		}

		async function loadTopics() {
			console.log("Themen werden geladen");
			console.log(user);
			try {
				let response = await axios.get(
					API + "/api/topics/4028329c795c0f3201795c0f38510000"
				);
				console.log("Themen wurden geladen");
				topics.value = response.data;
				sortTopics();
				console.log(topics.value);
			} catch (e) {
				console.error(e);
			}
		}

		async function handleDelete(index, row) {
			console.log("Topic wird gelöscht");
			let payload = [{ id: row.id, topicName: row.topicName }];
			console.log(payload);
			try {
				let response = await axios.post(API + "/api/topics", payload);
				console.log("Topic wurde gelöscht");
				console.log(response.status);
			} catch (e) {
				console.error(e);
			}
			loadTopics();
		}

		async function addNewTopic(topicName) {
			console.log(topicName);
			let payload = { id: null, topicName: topicName };
			console.log(payload);
			try {
				let response = await axios.post(
					API + "/api/topics/4028329c795c0f3201795c0f38510000",
					payload
				);
				console.log("Thema wurde angelegt");
				console.log(response.data);
			} catch (e) {
				console.error(e);
			}
			loadTopics();
		}

		function submitForm() {
			console.log("inside submit");
			console.log(form.name);
			formEl.value.validate((valid) => {
				if (valid) {
					addNewTopic(form.name);
					openPopup.value.buttontrigger = false;
				}
			});
		}

		onBeforeMount(() => {
			return loadTopics();
		});

		return {
			openPopup,
			formEl,
			form,
			rules,
			loadTopics,
			topics,
			handleDelete,
			submitForm,
			addNewTopic
		};
	}
};
</script>

<style lang="scss" scoped>
.el-menu {
	background: #ffffff;
}
</style>
