<template>
  <div class="footer">
    <div class="social-links">
      <div class="social-headline">
        Get connected with us on social networks!
      </div>
      <div class="social-icons">
        <fa :icon="['fab', 'facebook-f']" size="2x" />
        <fa :icon="['fab', 'twitter']" size="2x" />
        <fa :icon="['fab', 'google-plus-g']" size="2x" />
        <fa :icon="['fab', 'linkedin-in']" size="2x" />
        <fa :icon="['fab', 'instagram']" size="2x" />
      </div>
    </div>
    <div class="copyright">&copy; 2021 InfoCard</div>
  </div>
</template>

<script>
export default {
  name: "Footer"
};
</script>

<style lang="scss">
.footer {
  .social-links {
    background-color: rgb(248, 232, 140);
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    padding: 1rem;

    .social-headline {
      display: flex;
      justify-content: center;
      align-items: center;
      font-weight: bold;
    }

    .social-icons {
      svg {
        margin-left: 3rem;
        cursor: pointer;
      }
    }
  }

  .copyright {
    display: flex;
    justify-content: center;
    padding: 0.5rem;
    color: rgb(223, 223, 223);
    background-color: #1d2231;
  }
}
</style>
