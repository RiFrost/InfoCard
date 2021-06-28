import { mount } from "@vue/test-utils";
import store from "@/store";
import JestMock from "jest-mock";
import router from "@/router";
import ElementPlus from "element-plus";
import Home from "@/views/Home.vue";

const userData = [
  {
    id: "402832947955d58d017955d5de080000",
    firstname: "Richard",
    lastname: "Frost",
    email: "ri.frost@web.de",
    password: "$2a$10$X6RWO6UAmPrHnfQ4nfudaOlvGIUMtw71SeKdg5KiqbDi8LauVdaDy",
    roles: [{ id: 1, name: "ROLE_USER" }]
  },
  {
    id: "2c91a73a799a22b901799a23611d0000",
    firstname: "Maria",
    lastname: "Admin",
    email: "test123@mail.de",
    password: "$2a$10$03DchKz1427Q0CKlSMlje.SKnjiYZ6ZcUFGf2yda2ZESgQLggTTyu",
    roles: [{ id: 1, name: "ROLE_USER" }]
  }
];

describe("Home Comp Tests", () => {
  let component = mount(Home, {
    methods: {
      getUsersNoAuth: JestMock.fn().mockResolvedValueOnce(userData)
    },
    global: {
      plugins: [store, router, ElementPlus]
    }
  });

  it("is instantiated", () => {
    expect(component.vm).toBeTruthy();
  });

  /**it("", async () => {
    expect(component.find(".el-table__empty-block").exists()).toBe(true);
    component.find(".button-no-auth").trigger("click");
    await component.vm.$nextTick();
    expect(component.find(".el-table__empty-block").exists()).toBe(false);
  });*/
});
