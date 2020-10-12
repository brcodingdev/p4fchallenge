export default [
  {
    _name: "CSidebarNav",
    _children: [
      {
        _name: "CSidebarNavItem",
        name: "Home",
        to: "/home",
        icon: "cil-home",
      },

      {
        _name: "CSidebarNavItem",
        name: "Usuários",
        to: "/users",
        icon: "cil-user",
        role: 'ADMIN'
      },

      {
        _name: "CSidebarNavItem",
        name: "Endereços",
        to: "/addresses",
        icon: "cil-grid",
      },
    ],
  },
];
