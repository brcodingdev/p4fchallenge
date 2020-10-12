import Vue from 'vue';
import Vuex from 'vuex';
import auth from './auth.module';
import user from './user.module';
import address from './address.module';
import state from './state.module';
Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    auth,
    user,
    address,
    state
  },
});
