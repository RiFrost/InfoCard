import { flushPromises, mount } from "@vue/test-utils";
import store from "@/store";
import router from "@/router";
import ElementPlus from "element-plus";
import Home from "@/views/Home.vue";

describe("Home Comp Tests", () => {
  let component;

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

  /**
  jest.mock("axios", () => {
    get: jest.fn(() => Promise.resolve({ data: userData }));
  });*/

  beforeEach(() => {
    component = mount(Home, {
      global: {
        plugins: [store, router, ElementPlus]
      }
    });
    jest.resetModules();
    jest.clearAllMocks();
  });

  it("is instantiated", () => {
    expect(component.vm).toBeTruthy();
  });

  it("simple dynamic rendering", async () => {
    expect(component.find(".el-table__empty-block").exists()).toBe(true);
    expect(component.find("p").text()).toMatch("1");
    await component.find(".button-no-auth").trigger("click");
    expect(component.find("p").text()).toMatch("2");
    await component.find(".button-no-auth").trigger("click");
    expect(component.find("p").text()).toMatch("3");
    await component.find(".button-no-auth").trigger("click");
    expect(component.find("p").text()).toMatch("4");
  });

  // could not get axios mock to work :/

  /**it("", async () => {
    const result = await component.vm.getUsersNoAuth();
    expect(result).toEqual({ data: userData });
  });*/
});
