type SidebarEntry = {
    text: string;
    link: string;
};

type SidebarMenu = Record<string, SidebarEntry[]>;

type SidebarVars = Record<string, SidebarMenu>;

export const sidebarVars = {
    'user': {
        '/my': [
            {
                text: 'Проекты',
                link: '/my/projects'
            },
            {
                text: 'Задачи',
                link: '/my/tasks'
            },
            {
                text: 'Документы',
                link: '/my/documents'
            },
            {
                text: 'Чат',
                link: '/my/chat'
            },
            {
                text: 'Аналитика',
                link: '/my/analytics'
            },
            {
                text: 'Настройки',
                link: '/my/settings'
            }
        ],
    }
} as SidebarVars