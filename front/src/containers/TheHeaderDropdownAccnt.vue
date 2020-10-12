<template>
  <CDropdown inNav class="c-header-nav-items" placement="bottom-end" add-menu-classes="pt-0">
    <template #toggler>
      <CHeaderNavLink>
        <div class="c-avatar">
          <img src="img/user-icon.png" class="c-avatar-img" />
        </div>
      </CHeaderNavLink>
    </template>
    <CDropdownHeader tag="div" class="text-center" color="light">
      <strong>{{$t('settings')}}</strong>
    </CDropdownHeader>
    <CDropdownItem @click="logout">
      <CIcon name="cil-lock-locked" />
      {{$t('exit')}}
    </CDropdownItem>
  </CDropdown>
</template>

<script>
import { mapGetters } from 'vuex';
import { LOGOUT } from '@/store/actions.type';

export default {
  name: 'TheHeaderDropdownAccnt',
  data() {
    return {
      itemsCount: 42
    };
  },

  methods: {
    async logout() {
      await this.$store.dispatch(LOGOUT);
      this.$router.push({ name: 'Login' });
    }
  },

  computed: {
    ...mapGetters(['userLogged'])
  }
};
</script>

<style scoped>
.c-icon {
  margin-right: 0.3rem;
}
</style>