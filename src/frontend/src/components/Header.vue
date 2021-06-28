<template>
  <div class="nav">
    <div class="brand">
      <img src="@/assets/logo.png" />
      <div class="brand-name">
        <h1>InfoCard</h1>
        <p>The New Way Of Learning</p>
      </div>
    </div>
    <div class="nav-items">
      <el-menu
        :default-active="activeIndex"
        background-color="transparent"
        text-color="#000"
        class="el-menu-demo"
        mode="horizontal"
        :router="true"
      >
        <el-menu-item v-if="!isAuthenticated" index="/">Home</el-menu-item>
        <el-menu-item v-if="isAuthenticated" index="/topics">Home</el-menu-item>
        <el-menu-item index="/about">About</el-menu-item>
        <el-menu-item index="/contact">Contact</el-menu-item>
      </el-menu>
    </div>
    <div class="user-items">
      <div v-if="!isAuthenticated">
        <el-button @click="pushRoute('login')" round>Login</el-button>
        <el-button @click="pushRoute('register')" type="success" round
          >Register</el-button
        >
      </div>
      <div v-if="isAuthenticated">
        <el-dropdown trigger="click">
          <el-avatar
            class="user-icon"
            icon="el-icon-user-solid"
            :size="55"
          ></el-avatar>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                icon="el-icon-user-solid"
                @click="pushRoute('profile')"
                >Profile</el-dropdown-item
              >
              <el-dropdown-item
                icon="el-icon-s-tools"
                @click="pushRoute('settings')"
                >Settings</el-dropdown-item
              >
              <el-dropdown-item icon="el-icon-switch-button" @click="logout"
                >Logout</el-dropdown-item
              >
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
import { computed, onMounted, onUpdated, ref } from "vue";
import { useRouter } from "vue-router";
import store from "../store";
export default {
  setup() {
    const router = useRouter();
    const isAuthenticated = ref();

    onUpdated(() => {
      isAuthenticated.value = store.getters["user/isAuthenticated"];
    });

    function logout() {
      store.dispatch("user/logout");
    }

    // TODO Rethink
    const activeIndex = computed(() => {
      return router.currentRoute.value.path;
    });

    function pushRoute(route) {
      router.push(`/${route}`);
    }

    return {
      activeIndex,
      pushRoute,
      isAuthenticated,
      logout
    };
  }
};
</script>

<style lang="scss" scoped>
.nav {
  display: flex;
  flex-direction: row;
  background: #1d2231;
  padding: 0 2rem 0 2rem;
  justify-content: space-between;
  align-items: center;

  .brand {
    display: flex;
    flex-direction: row;
    width: 30%;

    img {
      width: 130px;
      height: 100px;
    }

    .brand-name {
      margin: auto 0 auto 0;
      color: rgb(223, 223, 223);
      font-size: 15px;

      h1 {
        margin: 0 !important;
      }
      p {
        margin: 0 !important;
      }
    }
  }

  .nav-items {
    .el-menu {
      border: none !important;

      .el-menu-item {
        color: rgb(223, 223, 223) !important;
        font-size: 18px;

        &:hover {
          border-bottom: 2px solid #409eff !important;
          background: none !important;
        }
      }
    }
  }

  .user-items {
    width: 30%;
    display: flex;
    flex-direction: row-reverse;

    .user-icon {
      cursor: pointer;
    }
  }
}
</style>
