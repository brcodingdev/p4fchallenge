<template>
  <CContainer
    class="d-flex align-items-center min-vh-100 justify-content-center"
  >
    <CRow>
      <CCol>
        <CCardGroup>
          <CCard class="p-5">
            <CCardBody>
              <l-alert :errorMessage="errors"></l-alert>

              <CForm @submit.prevent="onSubmit(username, password)" novalidate>
                <h1>Login</h1>
                <p class="text-muted">{{ $t('type_your_credentials') }}</p>
                <CInput
                  style="width: 300px"
                  v-model="username"
                  type="username"
                  required
                  was-validated
                  :invalid-feedback="$t('username_required')"
                  :placeholder="$t('username')"
                  :autocomplete="$t('username')"
                >
                  <template #prepend-content>
                    <CIcon name="cil-user" />
                  </template>
                </CInput>
                <CInput
                  style="width: 300px"
                  v-model="password"
                  :placeholder="$t('password')"
                  type="password"
                  required
                  was-validated
                  :invalid-feedback="$t('password_required')"
                  autocomplete="curent-password"
                >
                  <template #prepend-content>
                    <CIcon name="cil-lock-locked" />
                  </template>
                </CInput>
                <CRow>
                  <CCol col="6">
                    <CButton type="submit" color="primary" class="px-4">{{
                      $t('login')
                    }}</CButton>
                  </CCol>
                  <CCol col="6" class="text-right"> </CCol>
                </CRow>
              </CForm>
            </CCardBody>
          </CCard>
        </CCardGroup>
      </CCol>
    </CRow>
  </CContainer>
</template>

<script>
import { mapState, mapGetters } from 'vuex';
import { GET_USER_LOGGED } from '@/store/actions.type';
import { LOGIN } from '@/store/actions.type';

export default {
  name: 'Login',

  data() {
    return {
      username: '',
      password: '',
    };
  },
  mounted() {
    this.$store.state.auth.errors = null;
    this.redirectUserLogged();
  },

  methods: {
    async redirectUserLogged() {
      await this.$store.dispatch(GET_USER_LOGGED);
      if (this.userLogged && this.userLogged.token) {
        this.redirectToHome();
      }
    },

    onSubmit(username, password) {
      this.$store.state.user.errors = null;

      if (username && password) {
        this.$store
          .dispatch(LOGIN, { username, password })
          .then(() => this.redirectToHome());
      }
    },
    redirectToHome() {
      this.$router.push({ name: 'App' });
    },
  },

  computed: {
    ...mapGetters(['userLogged']),
    ...mapState({
      errors: (state) => {
        return state.auth.errors;
      },
    }),
  },
};
</script>
