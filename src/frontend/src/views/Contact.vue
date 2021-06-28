<template>
  <div class="form-container">
    <el-card class="form-card">
      <template #header>
        <div class="card-header">
          <span>Contact Form</span>
        </div>
      </template>
      <el-form label-position="top" label-width="100px" :model="contactForm">
        <div class="first-row">
          <el-form-item label="Firstname">
            <el-input v-model="contactForm.firstname"></el-input>
          </el-form-item>
          <el-form-item label="Lastname">
            <el-input v-model="contactForm.lastname"></el-input>
          </el-form-item>
          <el-form-item label="E-Mail">
            <el-input v-model="contactForm.email"></el-input>
          </el-form-item>
        </div>
        <el-form-item label="Subject">
          <el-input v-model="contactForm.subject"></el-input>
        </el-form-item>
        <el-form-item label="Message">
          <el-input
            type="textarea"
            :autosize="{ minRows: 4 }"
            v-model="contactForm.message"
          ></el-input>
        </el-form-item>
        <el-form-item class="button-item">
          <el-button
            class="form-button"
            type="primary"
            round
            icon="el-icon-s-promotion"
            >Send</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
  </div>
  <p>
    sadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas dsa
  </p>
  <p>
    sadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas dsa
  </p>
  <p>
    sadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas
    dsasadsadsdasdasddsasd dasdasdassss asddasdas dasdasdassssdas dsa
  </p>
</template>

<script>
import { reactive } from "vue";
import { useStore } from "vuex";
export default {
  name: "Contact",

  setup() {
    const store = useStore();
    const user = store.state.user.user;

    const contactForm = reactive({
      firstname: user.firstname,
      lastname: user.lastname,
      email: user.email,
      subject: "",
      message: ""
    });

    return { contactForm };
  }
};
</script>

<style lang="scss">
.form-container {
  display: flex;
  justify-content: center;

  .form-card {
    width: 800px;

    .card-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .first-row {
      display: flex;
      flex-direction: row;
      justify-content: space-between;
    }

    .button-item {
      display: flex;
      flex-direction: row-reverse;
      margin: auto;

      .form-button {
        padding: 0 20px 0 15px;
      }
    }
  }
}
</style>
