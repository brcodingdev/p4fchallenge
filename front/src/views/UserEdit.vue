<template>
  <CCard>
    <CCardHeader>
      <CIcon name="cil-user" />
      {{ this.user.id ? $t('edit_user') : $t('new_user') }}
    </CCardHeader>

    <CForm @submit.prevent="onSubmit(user)" validate>
      <CCardBody>
        <CRow>
          <CCol>
            <CInput
              :label="$t('username')"
              maxlength="200"
              required
              :disabled="user.id > 0"
              :value.sync="user.username"
              :oninvalid="`setCustomsValidity('${$t('username_required')}')`"
              oninput="setCustomValidity('')"
            />
          </CCol>
          <CCol>
            <CInput
              :label="$t('password')"
              maxlength="200"
              required
              type="password"
              :value.sync="user.password"
              :oninvalid="`setCustomValidity('${$t('password_required')}')`"
              oninput="setCustomValidity('')"
            />
          </CCol>
        </CRow>
      </CCardBody>

      <CCardFooter>
        <l-alert :errorMessage="errors"></l-alert>
        <CButton class="btn-save" type="submit" color="primary">{{
          $t('save')
        }}</CButton>
        <CButton color="primary" @click="goBack">{{ $t('cancel') }}</CButton>
      </CCardFooter>
    </CForm>
  </CCard>
</template>


<script>
import { mapGetters, mapState } from 'vuex';
import { FETCH_USER, SAVE_USER } from '@/store/actions.type';

import vSelect from 'vue-select';
import 'vue-select/dist/vue-select.css';
import Vue from 'vue';
Vue.component('v-select', vSelect);

export default {
  name: 'User',
  components: { vSelect },
  data: () => {
    return {
      user: {
        username: '',
        password: '',
        id: 0,
      }
    };
  },

  async mounted() {
    await this.fetchUser();
  },

  methods: {
    async fetchUser() {
      var id = this.$route.params.id;
      this.$store.state.user.errors = null;

      if (id && id > 0) {
        await this.$store.dispatch(FETCH_USER, id);
        this.globals.setFields(this.userGet, this.user);
        this.user.password = '';
      }
    },

    goBack() {
      this.$router.go(-1);
    },

    onSubmit(user) {
      this.$store
        .dispatch(SAVE_USER, user)
        .then(() => this.$router.push({ path: '/users?success=true' }));
    },
  },
  computed: {
    ...mapGetters(['userGet']),

    ...mapState({
      errors: (state) => {
        return state.user.errors;
      },
    }),
  },
};
</script>