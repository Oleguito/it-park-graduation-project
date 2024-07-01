export const Settings = {
    appName: "Наше приложение",
    appDescription: "Крутой мегапроект на Next.js",
    keycloak: {
        tokenUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/token`,
        authUrl: `https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/auth`,
        realm: "grad-project",
        clientId: "auth-service",
        redirectUrl: `http://localhost:3000/test/redirect`,
    },
    frontend: {
        url: "localhost:3000",
    },
};
