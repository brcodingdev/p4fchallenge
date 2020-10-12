<template>
  <CCard>
    <CCardHeader>
      <CIcon name="cil-grid" />
      {{ this.address.id ? $t('edit_address') : $t('new_address') }}
    </CCardHeader>

    <CForm @submit.prevent="onSubmit(address)" validate>
      <CCardBody>
        <CRow>
          <CCol>
            <CInput
              :label="$t('address')"
              maxlength="100"
              required
              :value.sync="address.address"
              :oninvalid="`setCustomValidity('${$t('address_required')}')`"
              oninput="setCustomValidity('')"
            />
          </CCol>

          <CCol>
            <CInput
              :label="$t('number')"
              maxlength="5"
              required
              :value.sync="address.number"
              :oninvalid="`setCustomValidity('${$t('number_required')}')`"
              oninput="setCustomValidity('')"
            />
          </CCol>
        </CRow>
        <CRow>
          <CCol>
            <CInput
              :label="$t('zip_code')"
              maxlength="10"
              required
              :value.sync="address.zipCode"
              :oninvalid="`setCustomValidity('${$t('zip_code_required')}')`"
              oninput="setCustomValidity('')"
            />
          </CCol>
          <CCol>
            <CInput
              :label="$t('address2')"
              maxlength="100"
              :value.sync="address.address2"
            />
          </CCol>
          <CCol>
            <CInput
              :label="$t('district')"
              maxlength="100"
              required
              :value.sync="address.district"
              :oninvalid="`setCustomValidity('${$t('district_required')}')`"
              oninput="setCustomValidity('')"
            />
          </CCol>
        </CRow>
        <CRow>
          <CCol>
            <CSelect
              :value.sync="address.state.id"
              :label="$t('state')"
              :options="stateOptions"
              @update:value="updateState"
              :placeholder="$t('select')"
            />
          </CCol>
        </CRow>

        <CRow>
          <CCol>
            <CSelect
              :value.sync="address.city.id"
              :label="$t('city')"
              required
              :options="cityOptions"
              :placeholder="$t('select')"
              :oninvalid="`setCustomValidity('${$t('city_required')}')`"
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
import {
  FETCH_ADDRESS,
  SAVE_ADDRESS,
  FETCH_STATES,
  FETCH_CITIES,
} from '@/store/actions.type';

import vSelect from 'vue-select';
import 'vue-select/dist/vue-select.css';
import Vue from 'vue';
Vue.component('v-select', vSelect);

export default {
  name: 'Address',
  components: { vSelect },
  data: () => {
    return {
      address: {
        id: 0,
        number: '',
        address: '',
        address2: '',
        zipCode: '',
        district: '',
        state: {
          id: 0,
          name: '',
        },
        city: {
          id: 0,
          name: '',
        },
      },

      stateOptions: [],
      cityOptions: []
    };
  },

  async mounted() {
    await this.fetchAddress();
    await this.fetchStates();
    if (this.address.state.id > 0) {
      await this.fetchCities(this.address.state.id);
    }
  },

  methods: {
    async fetchAddress() {
      var id = this.$route.params.id;
      this.$store.state.address.errors = null;

      if (id && id > 0) {
        await this.$store.dispatch(FETCH_ADDRESS, id);
        this.globals.setFields(this.addressGet, this.address);
      }
    },

    async fetchStates() {
      await this.$store.dispatch(FETCH_STATES);

      this.states.forEach((state) => {
        this.stateOptions.push({
          value: state.id,
          label: state.name,
        });
      });
    },

    async fetchCities(stateId) {
      await this.$store.dispatch(FETCH_CITIES, stateId);

      this.cities.forEach((city) => {
        this.cityOptions.push({
          value: city.id,
          label: city.name,
        });
      });
    },

    goBack() {
      this.$router.go(-1);
    },

    onSubmit(address) {
      this.$store
        .dispatch(SAVE_ADDRESS, address)
        .then(() => this.$router.push({ path: '/addresses?success=true' }));
    },


    async updateState(id) {
      await this.fetchCities(id);
    },

  },
  computed: {
    ...mapGetters([
      'addressGet',
      'states',
      'cities'
    ]),

    ...mapState({
      errors: (state) => {
        return state.address.errors;
      },
    }),
  },
};
</script>