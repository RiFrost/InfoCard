<template>
  <div class="profile">
    <h1>Welcome back, {{ user.firstname }}</h1>
    <div class="profile-overview">
      <el-card class="box-card">
        <div class="user">
          <div class="settings"><i class="el-icon-s-tools"></i></div>
          <el-avatar
            class="user-icon"
            icon="el-icon-user-solid"
            :size="100"
          ></el-avatar>
          <div class="user-information">
            <span>
              {{ user.firstname }} {{ user.lastname }}
              <el-tag type="warning" effect="dark">
                Level 3
              </el-tag>
            </span>
            <el-progress
              :text-inside="true"
              :stroke-width="24"
              :percentage="45"
              status="success"
            ></el-progress>
          </div>
        </div>
      </el-card>
      <div class="achievements">
        <div class="ach-headline">
          <h3>Achievements</h3>
        </div>
        <div class="ach-trophies">
          <fa :icon="['fas', 'trophy']" size="3x" />
          <fa :icon="['fas', 'medal']" size="3x" />
          <fa :icon="['fas', 'trophy']" size="3x" />
          <fa :icon="['fas', 'medal']" size="3x" />
          <fa :icon="['fas', 'trophy']" size="3x" />
          <fa :icon="['fas', 'medal']" size="3x" />
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <div class="stats"></div>
  </div>
</template>

<script>
import { ref } from "vue";
import store from "../store";

export default {
  name: "Profile",

  setup() {
    const user = ref(store.state.user.user);

    return {
      user
    };
  }
};
</script>

<style lang="scss">
.profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 2rem 0;

  .profile-overview {
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    width: 100%;
    margin-right: 30%;

    .box-card {
      width: 700px;
      float: left;
      position: relative;

      .user {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;

        .user-icon {
          margin-right: 2rem;
        }

        .user-information {
          width: 80%;

          span {
            margin-bottom: 1rem;
          }
        }
      }

      .settings {
        position: absolute;
        top: 10px;
        right: 10px;
        cursor: pointer;
      }
    }

    .achievements {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      margin-left: 3rem;

      .ach-headline {
        display: flex;
        justify-content: center;
        font-size: 20px;
      }

      .ach-trophies {
        display: flex;
        justify-content: flex-start;
        margin-bottom: 2rem;

        svg {
          margin: 0 2rem;
          color: gold;
        }
      }
    }
  }
}
</style>
