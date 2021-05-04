<template>
	<div class="home">
		<h1>{{ msg }}</h1>
	</div>
</template>

<script>
import { ref, onMounted } from "vue";

const API =
	process.env.NODE_ENV === "production"
		? "https://infocard.herokuapp.com"
		: "http://localhost:7000";

export default {
	name: "Home",

	setup() {
		const msg = ref("");

		onMounted(() => {
			fetch(API + "/api/test")
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

<style lang="scss" scoped>
.home {
	display: flex;
	justify-content: center;
	align-items: center;

	h1 {
		font-weight: bolder;
	}
}
</style>
