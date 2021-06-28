import { shallowMount } from "@vue/test-utils";
import store from "@/store";
import router from "@/router";
import ElementPlus from "element-plus";
import Header from "@/components/Header.vue";

describe("Header Comp Tests", () => {
  let component = shallowMount(Header, {
    global: {
      plugins: [store, router, ElementPlus]
    }
  });

  const user = {
    getters: {
      isAuthenticated: true
    }
  };

  const $store = {
    state: {},
    mutations: {},
    actions: {},
    modules: {
      user
    }
  };

  it("is instantiated", () => {
    expect(component.vm).toBeTruthy();
  });

  /**it("is logged in", async () => {
    const mocked_comp = shallowMount(Header, {
      data() {
        return {
          isAuthenticated: true
        };
      },
      global: {
        plugins: [router],
        mocks: $store
      }
    });

    await expect(mocked_comp.find(".logged-in").exists()).toBe(true);
  });*/
});
