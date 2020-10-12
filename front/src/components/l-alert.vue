<template>
  <div>
    <CRow>
      <CCol>
        <CAlert
          :show.sync="errorCountDown"
          closeButton
          color="danger"
          fade
          v-show="errorMessage"
        >{{errorMessage}}</CAlert>
      </CCol>
    </CRow>
    <CRow>
      <CCol>
        <CAlert
          :show.sync="successCountDown"
          closeButton
          color="success"
          fade
          v-show="successMessage"
        >{{successMessage}}</CAlert>
      </CCol>
    </CRow>
  </div>
</template>

<script>
export default {
  name: 'alert',

  data() {
    return {
      dismissSecs: 10,
      errorCountDown: 10,
      successCountDown: 10
    };
  },

  props: {
    errorMessage: {
      type: String
    },
    successMessage: {
      type: String
    }
  },

  watch: {
    errorMessage(newVal) {
      if (newVal) {
        this.errorCountDown = this.dismissSecs;
      }
    },
    successMessage(newVal) {
      if (newVal) {
        this.successCountDown = this.dismissSecs;
      }
    }
  }
};
</script>
