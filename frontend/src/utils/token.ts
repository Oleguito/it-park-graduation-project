'use client'
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import queryString from 'query-string'
import {TOKEN_URL} from './authorizationLogic'
import {UserRole} from "@/types/user-role";
import { log } from 'console'
import { Settings } from '@/constants/constants'
import { ResourceAccess } from '@/types/resourceaccess'

const LOCAL_STORAGE_TOKEN_KEY = 'token'

export type TokenType = {
    access_token: string
    refresh_token: string
}

export type JwtPayloadType = {
    exp: string;
    preferred_username: string;
    resource_access: ResourceAccess;
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

        const body = {
            client_id: Settings.keycloak.clientId,
            grant_type: 'refresh_token',
            refresh_token: refreshToken,
        }
        console.log('Пытаюсь обновить токены')

        await axios
            .post<TokenType>(TOKEN_URL!, queryString.stringify(body), {
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
            })
            .then(data => {
                const tokens: TokenType = {
                    access_token: data.data.access_token,
                    refresh_token: data.data.refresh_token,
                }
                saveTokenInLS(tokens)
            })
            .catch(error => {
                console.error(error)
                if (error.response.status === 400) {
                    window.localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY)
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
    return jwt_decode(getTokens().access_token)
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
        const domain =
            process.env.REACT_APP_KEYCLOAK_LOGOUT_URL ||
            Settings.keycloak.logoutUrl
        const KEYCLOAK_LOGOUT_URL = `${domain}?id_token_hint=${id_token}&post_logout_redirect_uri=${window.location.origin}/`

        window.location.assign(KEYCLOAK_LOGOUT_URL)
    }
}
