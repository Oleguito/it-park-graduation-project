export default interface JwtParseData {
    exp: number;
    iat: number;
    jti: string;
    iss: string;
    sub: string;
    typ: string;
    azp: string;
    sid: string;
    acr: string;
    "allowed-origins": string[];
    realm_access: {
        roles: string[];
    };
    resource_access: {
        "auth-service": {
            roles: string[];
        };
    };
    scope: string;
    email_verified: boolean;
    name: string;
    preferred_username: string;
    given_name: string;
    family_name: string;
    email: string;
}

export type JwtIntrospectParseData = JwtParseData & {
    active: boolean;
}

export type JwtRefreshTokenType = {
    exp: number;
    iat: number;
    jti: string;
    iss: string;
    sub: string;
    typ: string;
    azp: string;
    sid: string;
    acr: string;
    "allowed-origins": string[];
    realm_access: {
        roles: string[];
    };
    resource_access: {
        "auth-service": {
            roles: string[];
        };
    };
    scope: string;
    email_verified: boolean;
    name: string;
    preferred_username: string;
    given_name: string;
    family_name: string;
    email: string;
}