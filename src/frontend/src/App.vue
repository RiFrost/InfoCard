<template>
	<div>
		<my-header></my-header>
		<router-view />
	</div>
</template>

<script>
import { onBeforeMount } from "vue";
import { useStore } from "vuex";
import Header from "./components/Header";

export default {
	components: {
		"my-header": Header,
	},

	setup() {
		const store = useStore();

		onBeforeMount(() => {
			store.dispatch("user/checkCookies");
		});
	},
};
</script>

<style>
body {
	padding: 0;
	margin: 0;
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	background-color: #e3ecf8;
}
</style>
