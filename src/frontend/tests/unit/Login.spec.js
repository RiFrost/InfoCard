import { mount } from "@vue/test-utils";
import store from "@/store";
import router from "@/router";
import ElementPlus from "element-plus";
import Login from "@/views/Login.vue";
import LoginForm from "@/components/LoginForm.vue";

describe("Login Comp Tests", () => {
  let component = mount(Login, {
    global: {
      plugins: [store, router, ElementPlus]
    }
  });

  it("is instantiated", () => {
    expect(component.vm).toBeTruthy();
  });

  it("should render Header on mount", () => {
    expect(component.findComponent(LoginForm).exists()).toBe(true);
  });
});
