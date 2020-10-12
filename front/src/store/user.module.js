import ApiService from '@/common/api.service';
import {
  FETCH_USERS,
  FETCH_USER,
  SAVE_USER,
} from './actions.type';
import jwtService from '@/common/jwt.service';

import { SET_USERS, SET_USER, SET_ERROR } from './mutations.type';

const API = 'users';

const state = {
  users: [],
  userGet: {},
  errors: ''
};

const actions = {
  async [FETCH_USERS](context) {
    ApiService.setHeader();
    const { data } = await ApiService.query(API);
    context.commit(SET_USERS, data);
    return data;
  },

  async [FETCH_USER](context, id) {
    ApiService.setHeader();
    const { data } = await ApiService.get(API, id);
    context.commit(SET_USER, data);
    return data;
  },

  async [SAVE_USER](context, user) {
    ApiService.setHeader();

    if (user.id) {
      return new Promise(resolve => {
        ApiService.put(`${API}/${user.id}`, user)
          .then(({ data }) => {
            resolve(data);
          })
          .catch(({ response }) => {
            context.commit(SET_ERROR, response.data.errors);
          });
      });
    } else {
      return new Promise(resolve => {
        ApiService.post(API, user)
          .then(({ data }) => {
            resolve(data);
          })
          .catch(({ response }) => {
            context.commit(SET_ERROR, response.data.errors);
          });
      });
    }
  }
};

const mutations = {
  [SET_USERS](state, data) {
    state.users = data;
  },
  [SET_USER](state, data) {
    state.userGet = data;
  },
  [SET_ERROR](state, errors) {
    state.errors = errors;
  }
};

const getters = {
  users(state) {
    return state.users;
  },

  userGet(state) {
    return state.userGet;
  },
};

export default {
  state,
  actions,
  mutations,
  getters
};
