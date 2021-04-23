<template>
	<div class="home">
		<h1>{{ msg }}</h1>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";

const API =
	process.env.API_URL != undefined
		? process.env.API_URL
		: "http://localhost:7000";

export default {
	name: "Home",

	setup() {
		const msg = ref("");

		onMounted(() => {
			fetch(API + "/api")
				.then((res) => {
					return res.text();
				})
				.then((data) => (msg.value = data));
		});

		return {
			msg,
		};
	},
};
</script>

<style scoped>
.home {
	display: flex;
	justify-content: center;
	align-items: center;
}

h1 {
	font-weight: bolder;
}
</style>
