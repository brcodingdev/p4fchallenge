<template>
  <div class="c-app">
    <TheSidebar />
    <div class="c-wrapper">
      <TheHeader />
      <div class="c-body">
        <main class="c-main">
          <CContainer fluid>
            <transition name="fade">
              <router-view></router-view>
            </transition>
          </CContainer>
        </main>
      </div>
      <TheFooter />
    </div>
  </div>
</template>

<script>
import TheSidebar from './TheSidebar';
import TheHeader from './TheHeader';
import TheFooter from './TheFooter';

export default {
  name: 'TheContainer',
  components: {
    TheHeader,
    TheSidebar,
    TheFooter
  },
  mounted() {
    this.$root.$on('validate', modelArr => {
      var model = modelArr[0];
      var att = modelArr[1];
      this.$set(model, att + '_validated', !model[att]);
    });
  }
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
.btn-list {
  float: left;
  margin-bottom: 10px;
}
</style>
