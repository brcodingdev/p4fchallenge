<template>
  <CSidebar fixed :minimize="minimize" :show.sync="show">
    
    <CRenderFunction flat :content-to-render="nav" />
  </CSidebar>
</template>

<script>
import {
  GET_USER_LOGGED,
} from '@/store/actions.type';
import navList from './_nav';
import { mapGetters } from 'vuex';

export default {
  name: 'TheSidebar',
  data() {
    return {
      minimize: false,
      nav: [],
      show: 'responsive'
    };
  },
  async mounted() {
    await this.acquireUserLogged();

    this.nav = this.getNavOptions();
    this.$root.$on('toggle-sidebar', () => {
      const sidebarOpened = this.show === true || this.show === 'responsive';
      this.show = sidebarOpened ? false : 'responsive';
    });
    this.$root.$on('toggle-sidebar-mobile', () => {
      const sidebarClosed = this.show === 'responsive' || this.show === false;
      this.show = sidebarClosed ? true : 'responsive';
    });
  },
  methods: {
    async acquireUserLogged() {
      await this.$store.dispatch(GET_USER_LOGGED);
    },
    getNavOptions() {
      var itemMenu;
      var permFound;
      //deep copy
      var cloneNavList = JSON.parse(JSON.stringify(navList));
      var children = cloneNavList[0]._children;

      if (!children) {
        return navList;
      }

      for (var i = 0; i < children.length; ++i) {
        itemMenu = children[i];
        if (itemMenu.role && itemMenu.role !== this.userLogged.role) {
            children.splice(i, 1);
            --i;
        }
      }
      return cloneNavList;
    }
  },

  computed: {
    ...mapGetters(['userLogged', 'isAuthenticated'])
  }
};
</script>
