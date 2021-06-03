<template>
  <div class="form-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>Login</span>
        </div>
      </template>
      <LoginForm />
    </el-card>
  </div>
</template>

<script>
import LoginForm from "@/components/LoginForm.vue";

export default {
  name: "Login",
  components: {
    LoginForm
  },

  setup() {}
};
</script>

<style></style>
