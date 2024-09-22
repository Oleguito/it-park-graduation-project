'use client'
import { Settings } from '@/constants/settings';
import { JwtRefreshTokenType } from '@/types/jwt';
import { ResourceAccess } from '@/types/resourceaccess';
import { UserRole } from "@/types/user-role";
import axios from 'axios';
import jwt_decode from 'jwt-decode';


export const LOCAL_STORAGE_TOKEN_KEY = `auth-tokens-${process.env.NODE_ENV}`

export type TokenType = {
    accessToken: string
    refreshToken: string
}

export type JwtPayloadType = {
    exp: string;
    preferred_username: string;
    resource_access: ResourceAccess;
    email: string;
    name: string;
};


export const isTokenInLS = (): boolean => {
    const token = window.localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY)
    return token !== null && token !== undefined
}

export const saveTokenInLS = (token: TokenType) => {
    const convertedToken = JSON.stringify(token)
    window.localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY)
    window.localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, convertedToken)
}
export const checkIsTokenExpired = (token: string): boolean => {
    const decodedToken: any = jwt_decode(token)
    const now = new Date().getTime()
    const expirationDate = decodedToken.exp * 1000
    return now > expirationDate
}

export const updateToken = async () => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY)

    if (token) {
        const refreshToken = JSON.parse(token).refresh_token

        console.log(`refreshToken: ${refreshToken}`)

        const body = {
            grant_type: 'refresh_token',
            client_id: Settings.keycloak.clientId,
            refresh_token: refreshToken,
            client_secret: Settings.keycloak.clientSecret
        }
        console.log('Пытаюсь обновить токены')

        await axios
            .post<{access_token: string, refresh_token: string}>(
                Settings.keycloak.tokenUrl, 
                body, 
                {
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                })
            .then(data => {
                const tokens: TokenType = {
                    accessToken: data.data.access_token,
                    refreshToken: data.data.refresh_token,
                }
                saveTokenInLS(tokens)
            })
            .catch(error => {
                console.error(error)
                if (error.response.status === 400) {
                    // window.localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY)
                    // window.location.replace(window.location.origin);
                }
            })
    }
}



export const getTokens = (): TokenType => {
    return isTokenInLS() ? JSON.parse(localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY)!) : {}
}

export const getDecodedToken = (): JwtPayloadType => {
    // @ts-ignore
    return jwt_decode(getTokens().accessToken)
}

export const getOrCreateTokens = (): TokenType | null => {
    const tokens = getTokens()
    return tokens ? tokens : null
}

export function getUserRole(): UserRole | undefined {
    if (isTokenInLS()) {
        const decodedToken = getDecodedToken()
        
        const roles =
            decodedToken?.resource_access[Settings.keycloak.clientId]?.roles

        if (roles?.includes('user')) return 'user'
        if (roles?.includes('admin')) return 'admin'
    } else {
        console.log("токена нету в localstorage");
    }
}

export function signOut() {
    if (typeof window !== 'undefined') {
        let baseUrl
        if (window.location.host == Settings.frontend.url) {
            baseUrl = Settings.keycloak.baseUrl
        } else {
            baseUrl = window.location.host
        }
        window.localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY)
        window.sessionStorage.removeItem('code_verifier')
        window.sessionStorage.removeItem('state')

        let id_token = window.localStorage.getItem('id_token')
        const domain = Settings.keycloak.logoutUrl
        const KEYCLOAK_LOGOUT_URL = `${domain}?id_token_hint=${id_token}&post_logout_redirect_uri=${window.location.origin}/`

        window.location.assign(KEYCLOAK_LOGOUT_URL)
    } else {
        console.log("Window is undefined: Окна не видно");
    }
}

export function getDecodedRefreshToken(): JwtRefreshTokenType | null {
    const tokensLS = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
    const parsed = JSON.parse(tokensLS);
    // console.log("parsed: ", parsed);
    const refreshObject: JwtRefreshTokenType = jwt_decode(parsed.refreshToken);
    // console.log(refreshObject);
    return refreshObject;
}