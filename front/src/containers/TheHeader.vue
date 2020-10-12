<template>
  <CHeader fixed with-subheader light>
    <CToggler in-header class="ml-3 d-lg-none" v-c-emit-root-event:toggle-sidebar-mobile />
    <CToggler in-header class="ml-3 d-md-down-none" v-c-emit-root-event:toggle-sidebar />
    <div class="mx-auto d-lg-none" height="46" />
    <CHeaderNav class="d-md-down-none mr-auto"></CHeaderNav>
    <CHeaderNav class="mr-4">
      <CCard style="border: 0; font-size: 11px; height:18%">
        <CRow>
          <CCol>
            <strong>{{userLogged.name ? userLogged.name.toUpperCase() : ''}}</strong>
          </CCol>
        </CRow>
       
      </CCard>
      <TheHeaderDropdownAccnt />
    </CHeaderNav>
    <CSubheader class="px-3">
      <CBreadcrumbRouter class="border-0" />
    </CSubheader>
  </CHeader>
</template>

<script>
import { mapGetters } from 'vuex';
import TheHeaderDropdownAccnt from './TheHeaderDropdownAccnt';

export default {
  name: 'TheHeader',
  components: {
    TheHeaderDropdownAccnt
  },
  data() {
    return {
      itemsCount: 42
    };
  },
  computed: {
    ...mapGetters(['userLogged'])
  }
};
</script>
