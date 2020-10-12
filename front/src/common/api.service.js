import Vue from 'vue';
import router from '@/router';
import axios from 'axios';
import VueAxios from 'vue-axios';
import jwtService from '@/common/jwt.service';
import { API_URL } from '@/common/config';

const ApiService = {
  init() {
    Vue.use(VueAxios, axios);
    Vue.axios.defaults.baseURL = API_URL;
  },

  setHeader() {
    Vue.axios.defaults.headers.common[
      'Authorization'
    ] = `Bearer ${jwtService.getToken()}`;
  },

  query(resource, search) {
    var params = {};
    if (search) {
      if (search.filter) {
        params = search.filter;
      }

      if (search.limit) {
        params.size = search.limit;
      } else {
        search.size = 5;
      }
      if (search.page) {
        params.page = search.page - 1;
      }
    }

    return Vue.axios.get(resource, { params: params }).catch(error => {
      if (error.response && error.response.status === 404) {
        router.push({ path: '/404?un=true' });
        return;
      }
      throw new Error(`ApiService ${error}`);
    });
  },

  get(resource, id) {
    var path = id ? `/${id}` : '';
    return Vue.axios.get(`${resource}${path}`).catch(error => {
      if (error.response && error.response.status === 404) {
        router.push({ path: '/404?un=true' });
        return;
      }

      throw new Error(`ApiService ${error}`);
    });
  },

  post(resource, params) {
    return Vue.axios.post(`${resource}`, params);
  },

  update(resource, slug, params) {
    return Vue.axios.put(`${resource}/${slug}`, params);
  },

  put(resource, params) {
    return Vue.axios.put(`${resource}`, params);
  },

  delete(resource) {
    return Vue.axios.delete(resource).catch(error => {
      if (error.response && error.response.status === 404) {
        router.push({ path: '/404?un=true' });
        return;
      }
      throw new Error(`ApiService ${error}`);
    });
  }
};

export default ApiService;
