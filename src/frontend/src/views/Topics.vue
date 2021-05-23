<template>
	<el-main style="width: 80%;  margin: auto;">
		<el-menu
			class="el-menu-demo"
			mode="horizontal"
			text-color="#606266"
			active-text-color="#ffd04b"
		>
			<el-menu-item index="2" label="addTopics" style="float: right">
				<fa @click="addTopic = true" icon="folder-plus" size="3x" />

				<el-dialog title="Neues Thema" v-model="addTopic" width="30%" center>
					<el-input
						type="text"
						placeholder="Please input"
						v-model="topics.topicName"
						maxlength="20"
						show-word-limit
					>
					</el-input>
					<template #footer>
						<span class="dialog-footer">
							<el-button
								type="primary"
								@click="[(addTopic = false), addNewTopic()]"
								>Neues Thema erstellen</el-button
							>
							<el-button @click="addTopic = false">Abbrechen</el-button>
						</span>
					</template>
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
			<el-table :data="topics" :show-header="false" style="width: 100%;">
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
import { ref, onBeforeMount } from "vue";
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
		},
	},

	setup() {
		const topics = ref([]);
		const store = useStore();
		const user = store.state.user.user;
		console.log(user);
		var addTopic = ref(false);

		function sortTopics() {
			topics.value.sort((a, b) => a.id - b.id);
		}

		//    const input = ref("");

		async function loadTopics() {
			console.log("Themen werden geladen");
			console.log(user);
			try {
				let response = await axios.get(
					API + "/api/topics/4028329c795c0f3201795c0f38510000"
				);
				console.log("Themen wurden geladen");
				topics.value = response.data;
				// topics.value.sort(function(a, b) {
				// 	return a.id - b.id;
				// });
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

		function handleClose(done) {
			console.log("test");
			done();
			this.addTopic = false;
		}

		async function addNewTopic() {
			// console.log(input.value);
			console.log(topics.value.topicName);
			let payload = { id: null, topicName: topics.value.topicName };
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
			//      input.value = "";
			loadTopics();
		}

		onBeforeMount(() => {
			return loadTopics();
		});

		return {
			addTopic,
			loadTopics,
			topics,
			handleDelete,
			handleClose,
			//      input,
			addNewTopic,
		};
	},
};
</script>

<style lang="scss" scoped>
.el-menu {
	background: #ffffff;
}
</style>
