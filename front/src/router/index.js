import Vue from 'vue';
import Router from 'vue-router';
import i18n from '@/i18n';

// Containers
const TheContainer = () => import('@/containers/TheContainer');

// Views
const Home = () => import('@/views/Home');
const Users = () => import('@/views/Users');
const UserEdit = () => import('@/views/UserEdit');
const Addresses = () => import('@/views/Addresses');
const AddressEdit = () => import('@/views/AddressEdit');
const Login = () => import('@/views/Login');
const Page404 = () => import('@/views/Page404');

Vue.use(Router);

export default new Router({
  mode: 'hash', // https://router.vuejs.org/api/#mode
  linkActiveClass: 'active',
  scrollBehavior: () => ({ y: 0 }),
  routes: configRoutes()
});

function configRoutes() {
  return [
    {
      path: "/app",
      redirect: "/home",
      name: "App",
      component: TheContainer,
      meta: { label: "Home" },

      children: [
        {
          path: "/home",
          name: "Home",
          component: Home,
          meta: {
            label: " ",
            requiresAuth: false,
          },
        },
        {
          path: "/users",
          component: {
            render(c) {
              return c("router-view");
            },
          },
          children: [
            {
              path: "/users",
              name: "Users",
              component: Users,
              meta: {
                requiresAuth: false,
                label: i18n.t("users"),
                permissions: ["LIST_USERS"],
              },
            },
            {
              path: ":id",
              name: "User",
              component: UserEdit,
              meta: {
                requiresAuth: false,
                label: i18n.t("user"),
                permissions: ["EDIT_USER"],
              },
            },
          ],
        },

        {
          path: "/addresses",
          component: {
            render(c) {
              return c("router-view");
            },
          },
          children: [
            {
              path: "/addresses",
              name: "Addresses",
              component: Addresses,
              meta: {
                requiresAuth: false,
                label: i18n.t("addresses"),
                permissions: ["LIST_USERS"],
              },
            },
            {
              path: ":id",
              name: "Address",
              component: AddressEdit,
              meta: {
                requiresAuth: false,
                label: i18n.t("address"),
                permissions: ["EDIT_USER"],
              },
            },
          ],
        },
      ],
    },
    {
      path: "/",
      redirect: "/login",
      name: "Login",
      component: Login,
      children: [
        {
          path: "login",
          component: Login,
          meta: {
            requiresAuth: false,
          },
        },
      ],
    },
    {
      path: "/404",
      name: "Page404",
      component: Page404,
      children: [
        {
          path: "404",
          component: Page404,
          meta: {
            requiresAuth: false,
          },
        },
      ],
    },
    { path: "*", redirect: "/404" },
  ];
}
