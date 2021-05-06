<template>
  <div class="home">
    <h1>{{ msg }}</h1>
    <h2>Willkommen {{ user.username }}</h2>
    <p>
      <button @click="getUsersNoAuth">
        Console Log Users (no Auth needed)
      </button>
    </p>
    <p>
      <button @click="getUsers">Console Log Users (Auth needed!!!)</button>
    </p>
    <table>
      <tr>
        <th>Id</th>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>E-Mail</th>
        <th>Password</th>
      </tr>
      <tr v-for="(item, index) in users" :key="index">
        <td>{{ item.id }}</td>
        <td>{{ item.firstname }}</td>
        <td>{{ item.lastname }}</td>
        <td>{{ item.email }}</td>
        <td>{{ item.password }}</td>
      </tr>
    </table>
  </div>
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
  name: "Home",

  setup() {
    const msg = ref("");
    const users = ref([]);

    const store = useStore();

    const user = store.state.user.user;

    const config = {
      headers: {
        Authorization: user.tokenType + " " + user.accessToken
      }
    };

    function getUsersNoAuth() {
      axios.get(API + "/api/showUsersNoAuth").then(res => {
        users.value = res.data;
        console.log(res.data);
      });
    }

    function getUsers() {
      axios.get(API + "/api/showUsers", config).then(res => {
        users.value = res.data;
        console.log(res.data);
      });
    }

    onBeforeMount(() => {
      axios
        .get(API + "/api/test", config)
        .then(res => {
          return res;
        })
        .then(data => (msg.value = data.data));
    });

    return {
      msg,
      users,
      user,
      getUsers,
      getUsersNoAuth
    };
  }
};
</script>

<style lang="scss" scoped>
.home {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

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
