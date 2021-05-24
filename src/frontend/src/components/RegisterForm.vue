<template>
  <div class="main">
    <div class="main-header"></div>
    <div class="main-content">
      <!--<form action="" role="form" @submit.prevent="onSubmit">
        <img src="../assets/logo.png" />
        <h2>Register</h2>
        <div class="card">
          <input
            type="text"
            placeholder="Firstname"
            v-model="form.firstname"
            required
            name="firstname"
          />
          <input
            type="text"
            placeholder="Lastname"
            v-model="form.lastname"
            required
            name="lastname"
          />
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
          <button type="submit">Register</button>
        </div>
      </form>-->

      <el-form
        :model="form"
        ref="formEl"
        :rules="rules"
        :status-icon="true"
        label-width="120px"
      >
        <img src="../assets/logo.png" />
        <h2>Register</h2>
        <el-form-item label="Firstname" prop="firstname">
          <el-input v-model="form.firstname"></el-input>
        </el-form-item>
        <el-form-item label="Lastname" prop="lastname">
          <el-input v-model="form.lastname"></el-input>
        </el-form-item>
        <el-form-item label="E-Mail" prop="email">
          <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">Register</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from "vue";
import { useStore } from "vuex";

export default {
  name: "RegisterForm",
  components: {},

  setup() {
    const store = useStore();

    const formEl = ref();

    const form = reactive({
      firstname: "",
      lastname: "",
      email: "",
      password: ""
    });

    const rules = {
      firstname: [
        {
          required: true,
          message: "Please input your Firstname",
          trigger: "blur"
        }
      ],
      lastname: [
        {
          required: true,
          message: "Please input your Lastname",
          trigger: "blur"
        }
      ],
      email: [
        {
          required: true,
          message: "Please input email address",
          trigger: "blur"
        },
        {
          type: "email",
          message: "Please input correct email",
          trigger: ["blur", "change"]
        }
      ],
      password: [
        {
          required: true,
          message: "Please enter a Password",
          trigger: "blur"
        },
        {
          min: 6,
          message: "Must be 6 or longer",
          trigger: "blur"
        }
      ]
    };

    function submitForm() {
      console.log("inside submit");
      console.log();
      formEl.value.validate(valid => {
        if (valid) {
          store.dispatch("user/register", {
            firstname: form.firstname,
            lastname: form.lastname,
            email: form.email,
            password: form.password
          });
        } else {
          alert("Please correct your inputs");
        }
      });
    }

    return {
      form,
      rules,
      submitForm,
      formEl
    };
  }
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
