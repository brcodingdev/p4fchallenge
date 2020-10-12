import Vue from "vue";
import App from "./App";
import router from "./router";
import store from "./store";
import CoreuiVue from "@coreui/vue";
import { iconsSet as icons } from "./assets/icons/icons.js";
import i18n from "./i18n";
import { CHECK_AUTH } from "./store/actions.type";
import ApiService from "./common/api.service";
import {
  dateTime,
  dateOnly,
  toRound,
  toPercentage,
  currency,
  error,
  customerLabel,
  sellerLabel,
} from "./common/filters";
import lAlert from "./components/l-alert";
//global components
Vue.component("l-alert", lAlert);

//configs
Vue.config.productionTip = false;
Vue.config.performance = true;

//filters
Vue.filter("currency", currency);
Vue.filter("dateTime", dateTime);
Vue.filter("dateOnly", dateOnly);
Vue.filter("toRound", toRound);
Vue.filter("toPercentage", toPercentage);
Vue.filter("error", error);
Vue.filter("customerLabel", customerLabel);
Vue.filter("sellerLabel", sellerLabel);

ApiService.init();

Vue.use(CoreuiVue);

// Ensure we checked auth before each page load.
router.beforeEach((to, from, next) => {
  store.dispatch(CHECK_AUTH).then(() => {
    if (!store.getters.isAuthenticated && to.meta.requiresAuth) {
      next("/login");
    } else {
      next();
    }
  });
});

//TODO: move globals to file
let globals = {
  pagProps: {
    align: "center",
    doubleArrows: false,
    previousButtonHtml: "prev",
    nextButtonHtml: "next",
  },
  itemsPerPage: 5,
  itemsPerPageSelect: true,
  dismissAlertSeconds: 10,
  activeOptions: [
    { value: "false", label: i18n.t("no") },
    { value: "true", label: i18n.t("yes") },
  ],
  getBadge: (active) => (active ? "success" : "danger"),
  getActiveLabel: (active) => (active ? i18n.t("yes") : i18n.t("no")),
  setFields(from, to) {
    for (let key in from) {
      if (from[key] instanceof Object && !(from[key] instanceof Array)) {
        this.setFields(from[key], to[key]);
      } else {
        Vue.set(to, key, from[key]);
      }
    }
  },
};

Vue.mixin({
  data() {
    return {
      globals: globals,
      isAdmin: () => {
        return (
          store.getters.userLogged &&
          store.getters.userLogged.role &&
          store.getters.userLogged.role == "ADMIN"
        );
      },

      hasPermission: (permission) => {
        var found = false;
        store.getters.rolePermissions.forEach((rolePermissions) => {
          if (permission === rolePermissions) {
            found = true;
            return;
          }
        });

        return found;
      },
    };
  },
});

new Vue({
  el: "#app",
  router,
  icons,
  template: "<App/>",
  i18n,
  store,
  components: {
    App,
  },
});
