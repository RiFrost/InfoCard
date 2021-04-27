<template>
  <div class="header">
    <div class="logo_container">
      <img src="@/assets/logo.png" />
    </div>
    <div class="text_container">
      <h1>Infocard</h1>
      <p>The New Way Of Learning</p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
html,
body {
  padding: 0;
  margin: 0;
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
}

.header {
  width: 100%;
  height: 8pc;
  display: block;
  background: #1d2231;
  text-shadow: 4px 0px 2px rgba(0, 0, 0, 0.6);
  background-image: linear-gradient(to top, #515c7c 0%, #1d2231 91%);
  -webkit-box-shadow: 0px 6px 16px 3px rgba(0, 0, 0, 0.33);
  box-shadow: 0px 6px 16px 3px rgba(0, 0, 0, 0.33);
}

.logo_container {
  width: 265px;
  height: 100%;
  float: left;
  display: block;
  margin: 0 auto;
}

.logo_container img {
  position: relative;
  top: -20px;
  left: 10px;
  width: 190px;
  height: 160px;
}

.text_container {
  position: relative;
  left: -30px;
  top: -5px;
  display: inline-block;
  line-height: 70%;
  color: rgb(223, 223, 223);
  font-size: 28px;
}
</style>
