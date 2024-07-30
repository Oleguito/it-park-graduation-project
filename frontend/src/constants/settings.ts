export const Settings = {
    appName: "Наше приложение",
    appDescription: "Крутой мегапроект на Next.js",
    keycloak: {
        baseUrl: `https://lemur-7.cloud-iam.com`,
        tokenUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/token`,
        authUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/auth`,
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
        projectService: {
            baseUrl: "http://localhost:8087",
            getAllProjectsUrl: function() {
                return this.baseUrl + "/projects/all";
            },
            createProjectUrl: function() {
                return this.baseUrl + "/projects/add";
            } 
        },
    },
};
