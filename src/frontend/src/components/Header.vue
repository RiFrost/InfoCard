<template>
	<div class="header">
		<img src="@/assets/logo.png" />
		<div class="a">
			<h1>InfoCard</h1>
			<p>The New Way Of Learning</p>
		</div>
		<div class="b">
			<a href="#" class="myButton">Login</a>
		</div>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.header img {
	position: absolute;
	top: -20px;
	left: -20px;
	width: 230px;
	height: 200px;
}

div.a {
	position: relative;
	top: 0px;
	left: 200px;
	line-height: 80%;
	color: rgb(223, 223, 223);
	font-size: 30px;
}

.header {
	padding: 1px;
	background: #1d2231;
	text-shadow: 4px 0px 2px rgba(0, 0, 0, 0.6);
	background-image: linear-gradient(to top, #515c7c 0%, #1d2231 91%);
	-webkit-box-shadow: 0px 6px 16px 3px rgba(0, 0, 0, 0.33);
	box-shadow: 0px 6px 16px 3px rgba(0, 0, 0, 0.33);
}

.myButton {
	box-shadow: 0px 10px 14px -13px #ffffff;
	background: linear-gradient(to bottom, #ededed 5%, #dfdfdf 100%);
	background-color: #ededed;
	border-radius: 8px;
	display: inline-block;
	cursor: pointer;
	color: #777777;
	font-family: Arial;
	font-size: 20px;
	font-weight: bold;
	padding: 13px 32px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}
.myButton:hover {
	background: linear-gradient(to bottom, #dfdfdf 5%, #ededed 100%);
	background-color: #dfdfdf;
}
.myButton:active {
	position: relative;
	top: 1px;
}

div.b {
	position: absolute;
	top: 55px;
	left: 1350px;
}
</style>
