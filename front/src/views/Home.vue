<template>
  <div>
    <CCard>
      <CCardBody> Bem vindo, {{ userLogged.first_name }} </CCardBody>
    </CCard>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'Home',
  data() {
    return {};
  },
  computed: {
    ...mapGetters(['userLogged']),
  },
};
</script>
