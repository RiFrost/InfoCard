<template>
	<div class="main">
		<div class="main-header"></div>
		<div class="main-content">
			<form action="" role="form" @submit.prevent="onSubmit">
				<img src="../assets/logo.png" />
				<h2>Login</h2>
				<div class="card">
					<input
						type="email"
						placeholder="E-Mail"
						v-model="form.email"
						required
						name="email"
					/>
					<input
						type="password"
						placeholder="Password"
						v-model="form.password"
						required
						name="password"
					/>
					<a class="pw-link" href="/login">Forgot password?</a>
					<button type="submit">Login</button>
					<label>
						<input type="checkbox" checked="checked" /> Remember Me
					</label>
				</div>
			</form>
		</div>
	</div>
</template>

<script>
import { reactive } from "vue";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { VueCookieNext } from "vue-cookie-next";

export default {
	name: "LoginForm",
	components: {},

	setup() {
		const store = useStore();

		const form = reactive({
			email: "",
			password: "",
		});

		function openErr(err) {
			ElMessage({
				message: err,
				type: "error",
			});
		}

		function onSubmit() {
			store
				.dispatch("user/login", {
					email: form.email,
					password: form.password,
				})
				.then(() => {
					VueCookieNext.setCookie("TestKey", "TestValue", {
						expire: "3min",
						path: "/",
					});
				})
				.catch(() => {
					openErr("Bad Credentials");
				});
		}

		return {
			form,
			onSubmit,
		};
	},
};
</script>

<style lang="scss" scoped>
.main {
	display: flex;
	width: 100%;
	flex-direction: column;

	.main-header {
		width: auto;
		height: 5.5rem;
	}

	.main-content {
		display: flex;
		width: auto;
		height: 100%;

		form {
			display: flex;
			flex-direction: column;
			align-items: center;
			margin: auto;
			width: 300px;

			img {
				height: 200px;
				width: 250px;
				margin: 35px 0 50px 0;
			}

			h2 {
				text-transform: uppercase;
				margin: 0 30px 30px 30px;
				font-weight: bold;
			}

			.card {
				display: flex;
				flex-direction: column;
				align-items: center;
				width: 100%;
				padding: 0 0 20px 0;
				box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);

				input {
					margin: 30px 0 0 0;
					padding: 5px;
					width: 80%;
				}

				.pw-link {
					text-decoration: none;
					margin-top: 0.5rem;
				}

				button {
					font-weight: bold;
					width: 80%;
					margin: 10px 0 5px 0;
					border: none;
					color: #fff;
					padding: 5px;
					cursor: pointer;
					background-color: blue;
				}

				.router {
					display: flex;
					text-decoration: none;
					justify-content: center;
					width: 100%;
				}

				label {
					display: flex;
					width: 100%;
					justify-items: space-between;
					cursor: pointer;

					input {
						margin: 5px 0 0 0;
						width: 75px;
						cursor: pointer;
					}
				}
			}
		}
	}
}
</style>
