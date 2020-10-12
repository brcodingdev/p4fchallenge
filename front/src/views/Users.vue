<template>
  <div>
    <l-alert :successMessage="successMessage"></l-alert>

    <CCard>
      <CCardHeader>
        <CIcon name="cil-user" />
        {{$t('users')}}
      </CCardHeader>
      <CCardBody>
        <CButton class="btn-cad" @click="createUserSetup" color="dark">
          <CIcon name="cil-plus" />
          &nbsp;{{$t('new_user')}}
        </CButton>

        <CDataTable
          hover
          striped
          :items="users"
          :loading="loading"
          :fields="fields"
          @row-clicked="rowClicked"
          index-column
          clickable-rows
        >
        </CDataTable>
      </CCardBody>
    </CCard>
  </div>
</template>

<script>
import i18n from '@/i18n';
import { mapGetters, mapState } from 'vuex';
import { FETCH_USERS } from '@/store/actions.type';

export default {
  name: 'Users',

  data: () => {
    return {
      loading: false,
      successMessage: '',
      fields: [
        { key: 'id', label: 'Id', filter: false },
        { key: 'username', label: i18n.t('username'), filter: false }
      ]
    };
  },

  mounted() {
    this.fetchUsers();
  },

  created() {
    this.showSuccessMessage();
  },

  methods: {
    showSuccessMessage() {
      if (this.$route.query.success) {
        this.successMessage = i18n.t('user_save_success');
      }
    },
    userLink(id) {
      return `users/${id}`;
    },

    createUserSetup() {
      this.$router.push({ path: 'users/0' });
    },

    rowClicked(item, index) {
      const userLink = this.userLink(item.id);
      this.$router.push({ path: userLink });
    },

    async fetchUsers(columnFilterValue, sorterValue, activePage) {
      this.loading = true;
      await this.$store.dispatch(FETCH_USERS);
      this.loading = false;
    },
  },
  computed: {
    ...mapGetters(['users'])
  }
};
</script>
