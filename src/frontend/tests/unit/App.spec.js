import { shallowMount } from "@vue/test-utils";
import store from "@/store";
import router from "@/router";
import App from "@/App.vue";
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

describe("App Comp Tests", () => {
  let component = shallowMount(App, {
    global: {
      plugins: [store, router]
    }
  });
  
  it("is instantiated", () => {
    expect(component.vm).toBeTruthy();
  });

  it("should render Header on mount", () => {
    expect(component.findComponent(Header).exists()).toBe(true);
  });

  it("should render Footer on mount", () => {
    expect(component.findComponent(Footer).exists()).toBe(true);
  });
});
