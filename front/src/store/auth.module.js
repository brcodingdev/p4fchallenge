import ApiService from '@/common/api.service';
import jwtService from '@/common/jwt.service';
import { LOGIN, LOGOUT, CHECK_AUTH, GET_USER_LOGGED } from './actions.type';
import {
  SET_AUTH,
  PURGE_AUTH,
  SET_ERROR,
  SET_USER_LOGGED
} from './mutations.type';

const state = {
  errors: null,
  user: {},
  isAuthenticated: !!jwtService.getToken(),
  userLogged: {}
};

const getters = {
  currentUser(state) {
    return state.user;
  },
  isAuthenticated(state) {
    return state.isAuthenticated;
  },
  userLogged(state) {
    return state.userLogged;
  }
};

const actions = {
  [LOGIN](context, credentials) {
    this.state.errors = null;
    return new Promise(resolve => {
      ApiService.post('login', credentials)
        .then(({ data }) => {
          context.commit(SET_AUTH, data);
          resolve(data);
        })
        .catch(({ response }) => {
          context.commit(SET_ERROR, 'Usuário ou senha inválido');
        });
    });
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [CHECK_AUTH](context) {
    if (!jwtService.getToken()) {
      context.commit(PURGE_AUTH);
    }
  },
  [GET_USER_LOGGED](context) {
    context.commit(SET_USER_LOGGED, jwtService.getUserLogged());
  }
};

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },
  [SET_AUTH](state, user) {
    state.isAuthenticated = true;
    state.user = user;
    jwtService.saveUserLogged(user);
  },
  [PURGE_AUTH](state) {
    state.isAuthenticated = false;
    state.user = {};
    jwtService.destroyToken();
  },
  [SET_USER_LOGGED](state, data) {
    state.userLogged = data;
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};
