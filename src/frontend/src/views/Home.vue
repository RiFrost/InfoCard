<template>
	<div class="home">
		<h1>{{ msg }}</h1>
		<h2>Willkommen {{ user.firstname }}</h2>
		<div class="buttons">
			<router-link to="/login"><el-button>Login</el-button></router-link>
			<router-link to="/register"><el-button>Register</el-button></router-link>
			<el-button @click="getUsersNoAuth">
				Console Log Users (no Auth needed)
			</el-button>
			<el-button @click="getUsers"
				>Console Log Users (Auth needed!!!)</el-button
			>
		</div>

		<el-table :data="users" style="width: 100%">
			<el-table-column prop="id" label="Id" width="400"> </el-table-column>
			<el-table-column prop="firstname" label="Firstname" width="180">
			</el-table-column>
			<el-table-column prop="lastname" label="Lastname" width="180">
			</el-table-column>
			<el-table-column prop="email" label="E-Mail" width="180">
			</el-table-column>
			<el-table-column prop="password" label="Password"> </el-table-column>
		</el-table>
	</div>
</template>

<script>
import axios from "axios";
import { ref, onBeforeMount } from "vue";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com"
		: "http://localhost:7000";

export default {
	name: "Home",

	setup() {
		const msg = ref("");
		const users = ref([]);

		const store = useStore();

		const user = store.state.user.user;

		const config = {
			headers: {
				Authorization: user.tokenType + " " + user.accessToken,
			},
		};

		function openErr(err) {
			ElMessage({
				message: err,
				type: "error",
			});
		}

		function openSuc() {
			ElMessage({
				message: "This is a success message",
				type: "success",
			});
		}

		function getUsersNoAuth() {
			axios
				.get(API + "/api/showUsersNoAuth")
				.then((res) => {
					openSuc();
					users.value = res.data;
				})
				.catch((error) => {
					openErr(error.response);
				});
		}

		function getUsers() {
			console.log(user);
			axios
				.get(API + "/api/showUsers", config)
				.then((res) => {
					openSuc();
					users.value = res.data;
				})
				.catch(() => {
					openErr("Unauthorized");
				});
		}

		onBeforeMount(() => {
			axios
				.get(API + "/api/test", config)
				.then((res) => {
					return res;
				})
				.then((data) => (msg.value = data.data));
		});

		return {
			msg,
			users,
			user,
			getUsers,
			getUsersNoAuth,
		};
	},
};
</script>

<style lang="scss" scoped>
.home {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;

	.buttons {
		margin: 0 0 20px 0;
		width: 30%;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-content: space-between;

		button {
			margin: 0 !important;
		}
	}

	h1 {
		font-weight: bolder;
	}

	table {
		th,
		td {
			border: 1px solid black;
			padding: 10px;
			text-align: center;
		}
	}
}
</style>
