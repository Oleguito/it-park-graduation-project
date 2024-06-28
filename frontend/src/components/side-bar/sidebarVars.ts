type SidebarEntry = {
    text: string;
    link: string;
};

type SidebarMenu = Record<string, SidebarEntry[]>;

type SidebarVars = Record<string, SidebarMenu>;

export const sidebarVars = {
    'driver': {
        '/my': [
            {
                text: 'Профиль',
                link: '/my/profile'
            },
            {
                text: 'Штрафы',
                link: '/my/fees'
            },
            {
                text: 'Документы',
                link: '/my/documents'
            },
            {
                text: 'Настройки',
                link: '/my/settings'
            }
        ],
    }
} as SidebarVars