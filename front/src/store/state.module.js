import ApiService from "@/common/api.service";
import { FETCH_STATES, FETCH_CITIES } from "./actions.type";
import { SET_STATES, SET_CITIES } from "./mutations.type";

const API = "states";

const state = {
  states: [],
  cities: [],
};

const actions = {
  async [FETCH_STATES](context) {
    ApiService.setHeader();
    const { data } = await ApiService.query(API);
    context.commit(SET_STATES, data);
    return data;
  },

  async [FETCH_CITIES](context, stateId) {
    ApiService.setHeader();
    const { data } = await ApiService.query(`${API}/${stateId}/cities`);
    context.commit(SET_CITIES, data);
    return data;
  },
};

const mutations = {
  [SET_STATES](state, data) {
    state.states = data;
  },

  [SET_CITIES](state, data) {
    state.cities = data;
  },
};

const getters = {
  states(state) {
    return state.states;
  },
  cities(state) {
    return state.cities;
  },
};

export default {
  state,
  actions,
  mutations,
  getters,
};
