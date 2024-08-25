export const Settings = {
    appName: "Наше приложение",
    appDescription: "Крутой мегапроект на Next.js",
    keycloak: {
        baseUrl: `https://lemur-7.cloud-iam.com`,
        tokenUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/token`,
        authUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/auth`,
        introspectUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/token/introspect`,
        realm: "grad-project",
        clientId: `auth-service` as string,
        redirectUrl: `http://localhost:3000/test/redirect`,
        logoutUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/logout`,
        clientSecret: `7TCb2UhbgVpyh186oC6VMe9srakq16Bp`,
    },
    frontend: {
        url: "localhost:3000",
    },
    backend: {
        userService: {
            baseUrl: "https://localhost:8088",
            getUserCreateUrl: function () {
                return this.baseUrl + "/api/users/create";
            },
            getUserInfoUrl: function () {
                return this.baseUrl + "/api/users/all";
            },
            getUserSearchUrl: function () {
                return this.baseUrl + "/api/users/search";
            },
        },
        projectService: {
            baseUrl: "http://localhost:8087",
            getAllProjectsUrl: function () {
                return this.baseUrl + "/projects/all";
            },
            createProjectUrl: function () {
                return this.baseUrl + "/projects/add";
            },
            findUsersForProjectUrl: function (projectId: number) {
                return this.baseUrl + `/projects/all/${projectId}`;
            },
            addParticipantToProjectUrl: function () {
                return this.baseUrl + "/projects/add-participant";
            },
            removeParticipantToProjectUrl: function () {
                return this.baseUrl + "/projects/remove-participant";
            },
        },
    },
};
