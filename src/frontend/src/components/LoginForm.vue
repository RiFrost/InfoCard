<template>
  <div class="main">
    <div class="main-header"></div>
    <div class="main-content">
      <el-form
        :model="form"
        ref="formEl"
        :rules="rules"
        :status-icon="true"
        label-width="90px"
        @keyup.enter="submitForm()"
      >
        <img src="../assets/logo.png" />
        <h2>Login</h2>
        <el-form-item label="E-Mail" prop="email" label-width="auto">
          <el-input v-model="form.email" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password" label-width="auto">
          <el-input
            v-model="form.password"
            show-password
            style="width: 200px"
          ></el-input>
        </el-form-item>
        <el-form-item label-width="auto">
          <el-button type="primary" @click="submitForm()">Login</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from "vue";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";

export default {
  name: "LoginForm",
  components: {},

  setup() {
    const store = useStore();
    const formEl = ref();

    const form = reactive({
      email: "",
      password: ""
    });

    function openErr(err) {
      ElMessage({
        message: err,
        type: "error"
      });
    }

    function submitForm() {
      store
        .dispatch("user/login", {
          email: form.email,
          password: form.password
        })
        .catch(() => {
          openErr("Bad Credentials");
        });
    }

    return {
      form,
      formEl,
      submitForm
    };
  }
};
</script>

<style lang="scss" scoped>
.main {
  display: flex;
  width: 100%;
  flex-direction: column;

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
        margin: 25px 0 25px 0;
      }

      h2 {
        text-transform: uppercase;
        margin: 0 30px 30px 30px;
        font-weight: bold;
        color: #1d2231;
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

      .el-button--primary {
        background-color: #1d2231;
        border-color: #1d2231;
        color: #dfdfdf;

        &:hover {
          opacity: 0.7;
        }
      }
    }
  }
}
</style>
