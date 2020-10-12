<template>
  <div>
    <l-alert :successMessage="successMessage"></l-alert>

    <CCard>
      <CCardHeader>
        <CIcon name="cil-grid" />
        {{$t('addresses')}}
      </CCardHeader>
      <CCardBody>
        <CButton class="btn-cad" @click="createUserSetup" color="dark">
          <CIcon name="cil-plus" />
          &nbsp;{{$t('new_address')}}
        </CButton>

        <CDataTable
          hover
          striped
          :items="addresses"
          :loading="loading"
          :fields="fields"
          :itemsPerPageSelect="globals.itemsPerPageSelect"
          @row-clicked="rowClicked"
          @column-filter-input="filterUpdate"
          @pagination-change="paginationChanged"
          @update:activePage="paginationChanged"
          :columnFilterValue.sync="columnFilterValue"
          :itemsPerPage="itemsPerPage"
          columnFilter
          index-column
          clickable-rows
        >
          <template #city="data">
            <td>{{data.item.city.name}}</td>
          </template>

          <template #active="data">
            <td>
              <CBadge
                :color="globals.getBadge(data.item.active)"
              >{{globals.getActiveLabel(data.item.active)}}</CBadge>
            </td>
          </template>
        </CDataTable>

        <CPagination
          :activePage.sync="activePage"
          :pages.sync="totalPages"
          @update:activePage="activePageChanged"
          size="sm"
          align="center"
        />
      </CCardBody>
    </CCard>
  </div>
</template>

<script>
import i18n from '@/i18n';
import { mapGetters, mapState } from 'vuex';
import { FETCH_ADDRESSES } from '@/store/actions.type';

export default {
  name: 'Users',

  data: () => {
    return {
      activePage: 1,
      itemsPerPage: 5,
      columnFilterValue: {},
      sorterValue: {},
      totalPages: 0,
      loading: false,
      successMessage: '',
      fields: [
        { key: 'address', label: i18n.t('address'), filter: false },
        { key: 'number', label: i18n.t('number'), filter: false },
        { key: 'address2', label: i18n.t('address2'), filter: false },
        { key: 'district', label: i18n.t('district'), filter: false },
        { key: 'city', label: i18n.t('city'), filter: false },
        { key: 'zipCode', label: i18n.t('zip_code') }
      ],
      perPage: 5
    };
  },

  mounted() {
    this.fetchAddresses();
  },

  created() {
    this.showSuccessMessage();
  },

  methods: {
    showSuccessMessage() {
      if (this.$route.query.success) {
        this.successMessage = i18n.t('address_save_success');
      }
    },
    userLink(id) {
      return `addresses/${id}`;
    },

    createUserSetup() {
      this.$router.push({ path: 'addresses/0' });
    },

    rowClicked(item, index) {
      const userLink = this.userLink(item.id);
      this.$router.push({ path: userLink });
    },

    async fetchAddresses(columnFilterValue, sorterValue, activePage) {
      this.loading = true;
      await this.$store.dispatch(FETCH_ADDRESSES, {
        filter: columnFilterValue,
        sorter: sorterValue,
        limit: this.itemsPerPage,
        page: activePage
      });

      this.totalPages = Math.ceil(this.totalCount / this.itemsPerPage);
      this.loading = false;
    },

    filterUpdate(columnFilterValue) {
      this.fetchAddresses(columnFilterValue, this.sorterValue, this.activePage);
    },

    paginationChanged(itemsPerPage) {
      this.itemsPerPage = itemsPerPage;
      this.fetchAddresses(
        this.columnFilterValue,
        this.sorterValue,
        this.activePage
      );
    },

    activePageChanged(activePage) {
      this.fetchAddresses(this.columnFilterValue, this.sorterValue, activePage);
    }
  },
  computed: {
    ...mapGetters(['addresses', 'totalCount'])
  }
};
</script>
