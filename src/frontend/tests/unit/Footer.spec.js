import { mount } from "@vue/test-utils";
import Footer from "@/components/Footer.vue";

describe("Footer COmp Tests", () => {
  let component = mount(Footer);

  it("is instantiated", () => {
    expect(component.vm).toBeTruthy();
  });

  it("should say headline", () => {
    expect(component.find(".social-headline").text()).toBe(
      "Get connected with us on social networks!"
    );
  });

  it("should contain copyright", () => {
    expect(component.find(".copyright").text()).toBe("© 2021 InfoCard");
  });
});
