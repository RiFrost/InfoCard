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
				style="border-bottom: none; padding-top: 6px;"
			>
				<el-tooltip
					class="item"
					effect="dark"
					content="hinzufügen"
					placement="bottom"
				>
					<fa
						@click="openPopup.buttontriggerNewTopic = true"
						icon="plus-square"
						size="3x"
					/>
				</el-tooltip>
			</el-menu-item>
			<el-menu-item
				class="go-back"
				index="2"
				label="backwards"
				style="border-bottom: none; padding-top: 6px;"
				><router-link class="router-link-indexcard" to="topics">
					<el-tooltip
						class="item"
						effect="dark"
						content="zurück zu den Themen"
						placement="bottom"
					>
						<fa icon="caret-square-left" size="3x" /> </el-tooltip
				></router-link>
			</el-menu-item>
			<el-menu-item class="headline" index="1" label="Topics" disabled>
				Deine Übersicht {{ $route.params.id }}
			</el-menu-item>
		</el-menu>
		<el-scrollbar max-height="550px">
			<el-table
				:data="tableData"
				:show-header="false"
				style="width: 100%; text-align: center;"
			>
				<el-table-column
					prop="date"
					label="Date"
					style="display: flex;justify-content: center; text-align: center;"
				>
					<div class="test">
						<el-card class="box-card">
							<template #header>
								<div class="card-header">
									<span>Card name</span>
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
				</el-table-column>
				<el-table-column prop="name" label="Name">
					<div class="test">
						<el-card class="box-card">
							<template #header>
								<div class="card-header">
									<span>Card name</span>
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
				</el-table-column>
			</el-table>
		</el-scrollbar>
	</el-main>
</template>

<script>
import axios from "axios";
import { ref, onBeforeMount } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com/api"
		: "http://localhost:7000/api";

export default {
	name: "Indexcards",
	data() {
		return {
			tableData: [
				{
					date: "2016-05-03",
					name: "Tom",
					address: "No. 189, Grove St, Los Angeles"
				},
				{
					date: "2016-05-02",
					name: "Tom",
					address: "No. 189, Grove St, Los Angeles"
				},
				{
					date: "2016-05-04",
					name: "Tom",
					address: "No. 189, Grove St, Los Angeles"
				},
				{
					date: "2016-05-01",
					name: "Tom",
					address: "No. 189, Grove St, Los Angeles"
				}
			]
		};
	},
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

		async function loadIndexCards() {
			console.log("load index cards");
			try {
				let response = await axios.get(
					`${API}/indexcards/${route.params.id}`,
					config
				);
				console.log(response.data);
				console.log("index cards loaded");
				indexCards.value = response.data;
				console.log(indexCards.value);
			} catch (e) {
				console.error(e);
			}
		}

		onBeforeMount(() => {
			return loadIndexCards();
		});
		return {
			indexCards
		};
	}
};
</script>

<style lang="scss" scoped>
$icon-color: #1d2231;
$icon-hover: pointer;
$icon-opacity: 0.7;

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

.test {
	display: flex;
	justify-content: center;
}

.card-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.text {
	font-size: 14px;
}

.item {
	margin-bottom: 18px;
}

.box-card {
	width: 480px;
}
</style>
