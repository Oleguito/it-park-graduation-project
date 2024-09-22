type SidebarEntry = {
    text: string;
    link: string;
};

type SidebarMenu = Record<string, SidebarEntry[]>;

type SidebarVars = Record<string, SidebarMenu>;

export const sidebarVars = {
  user: {
    "/my": [
      {
        text: "Мои проекты",
        link: "/my/projects",
      },
      {
        text: "Добавить проект",
        link: "/my/projects/add",
      },

      {
        text: "Приглашения",
        link: "/my/projects/invitations",
      },
      {
        text: "Чат",
        link: "/my/chat",
      },
      // {
      //     text: "Аналитика",
      //     link: "/my/analytics",
      // },
      // {
      //     text: "Настройки",
      //     link: "/my/settings",
      // },
    ],
  },
} as SidebarVars;