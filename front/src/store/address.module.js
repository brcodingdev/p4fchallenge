import ApiService from "@/common/api.service";
import { FETCH_ADDRESSES, FETCH_ADDRESS, SAVE_ADDRESS } from "./actions.type";
import { SET_ADDRESSES, SET_ADDRESS, SET_ERROR } from "./mutations.type";

const API = "addresses";

const state = {
  addresses: [],
  totalCount: 0,
  addressGet: {},
  errors: "",
};

const actions = {
  async [FETCH_ADDRESSES](context, params) {
    ApiService.setHeader();
    const { data } = await ApiService.query(API, params);
    context.commit(SET_ADDRESSES, data);
    return data;
  },

  async [FETCH_ADDRESS](context, id) {
    ApiService.setHeader();
    const { data } = await ApiService.get(API, id);
    context.commit(SET_ADDRESS, data);
    return data;
  },

  async [SAVE_ADDRESS](context, address) {
    ApiService.setHeader();

    if (address.id) {
      return new Promise((resolve) => {
        ApiService.put(`${API}/${address.id}`, address)
          .then(({ data }) => {
            resolve(data);
          })
          .catch(({ response }) => {
            context.commit(SET_ERROR, response.data.errors);
          });
      });
    } else {
      return new Promise((resolve) => {
        ApiService.post(API, address)
          .then(({ data }) => {
            resolve(data);
          })
          .catch(({ response }) => {
            context.commit(SET_ERROR, response.data.errors);
          });
      });
    }
  },
};

const mutations = {
  [SET_ADDRESSES](state, data) {
    state.addresses = data.content;
    state.totalCount = data.totalElements;
  },
  [SET_ADDRESS](state, data) {
    state.addressGet = data;
  },
  [SET_ERROR](state, errors) {
    state.errors = errors;
  },
};

const getters = {
  addresses(state) {
    return state.addresses;
  },

  addressGet(state) {
    return state.addressGet;
  },

  totalCount(state) {
    return state.totalCount;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
