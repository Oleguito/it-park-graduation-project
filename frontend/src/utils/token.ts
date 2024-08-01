'use client'
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import queryString from 'query-string'
import {UserRole} from "@/types/user-role";
import { Settings } from '@/constants/settings'
import { ResourceAccess } from '@/types/resourceaccess'
import JwtParseData from '@/types/jwt';


const LOCAL_STORAGE_TOKEN_KEY = 'token'

export type TokenType = {
    access_token: string
    refresh_token: string
}

export type JwtPayloadType = {
    exp: string;
    preferred_username: string;
    resource_access: ResourceAccess;
    email: string;
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
            .post<TokenType>(
                Settings.keycloak.tokenUrl, 
                body, 
                {
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
        const domain = Settings.keycloak.logoutUrl
        const KEYCLOAK_LOGOUT_URL = `${domain}?id_token_hint=${id_token}&post_logout_redirect_uri=${window.location.origin}/`

        window.location.assign(KEYCLOAK_LOGOUT_URL)
    } else {
        console.log("Window is undefined: Окна не видно");
    }
}

// export function introspectTokenIsActive(): boolean {
//     const accessToken = getTokens().access_token
    
//     return false
// }

// export function postToIntrospectUrl1() {
//     axios
//         .post(
//             "https://lemur-7.cloud-iam.com/auth/realms/grad-project/protocol/openid-connect/token/introspect",
//             queryString.stringify({
//                 token: "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1VmsyMEV5RFJQVl9rbU5KM2pBaHZtUDdieDhMRUxmM0FTY09jRXVqV05ZIn0.eyJleHAiOjE3MjI0NTEyNDcsImlhdCI6MTcyMjQzMzI0NywiYXV0aF90aW1lIjoxNzIyNDMzMTE4LCJqdGkiOiI0YjgxMzVmMC0zYzliLTQ4MjAtYTkwZi1jZjU1ZDE5MGI1OGQiLCJpc3MiOiJodHRwczovL2xlbXVyLTcuY2xvdWQtaWFtLmNvbS9hdXRoL3JlYWxtcy9ncmFkLXByb2plY3QiLCJzdWIiOiI1NjhiMzBmYi1lMzhlLTQ4NjMtYjExOC03NjhhNWRjZTRhMzIiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhdXRoLXNlcnZpY2UiLCJzaWQiOiJlNWVkZWUyMS1lZmMzLTRmYTUtYTkxMS0zNGFkMDAxYWNmMWMiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbImRlZmF1bHQtcm9sZXMtZ3JhZC1wcm9qZWN0Il19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYXV0aC1zZXJ2aWNlIjp7InJvbGVzIjpbImFkbWluIiwidXNlciJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6Ik9sZWd1aXRvIFN3YWdidWNrcyIsInByZWZlcnJlZF91c2VybmFtZSI6Im9sZWd1aXRvIiwiZ2l2ZW5fbmFtZSI6Ik9sZWd1aXRvIiwiZmFtaWx5X25hbWUiOiJTd2FnYnVja3MiLCJlbWFpbCI6InRlbnpvcmlhdG9yQHJhbWJsZXIucnUifQ.RsUuzo6m_MphDYg7ynzYaJCuEb-G-ockjRTAtKIyshty7rwpHohQs61UzSdPIPCx3mM2reQlwDGr-uHdTVkEsSPTglWlvi-ZSUENF13czlU19cGirNrBwAXMHJ-cLUjzuRhbdacwcpSZeSoNmhUcx6Wh_RospNaMLislzof9HOTaJsRLabXyo7L_BbLM9Ayqo-agACTgd2W3YMTXvPURIsqpxtHp_BYZ0SIBjXC5kLOo3F0G2T4t1xJPfhGeEKQjDwA-HqaU0NlhMkQkDKFXwy8XiiqnhP90MvHMk5R-5Xcx7bhTctVuUwG3Jh1EiBCxOy5dFRPBleiuoUp7OMqZVw",
//                 client_id: "auth-service",
//                 client_secret: "7TCb2UhbgVpyh186oC6VMe9srakq16Bp",
//             }),
//             {
//                 headers: {
//                     "Content-Type": "application/x-www-form-urlencoded",
//                     Accept: "*/*",
//                 },
//             }
//         )
//         .then((response) => {
//             console.log("postToIntrospectUrl: ", response);
//         })
//         .catch((error) => {
//             console.log("postToIntrospectUrl: ", error);
//         });
// }



// export function getAccessTokenAndRefreshIfExpired(): string {


//     const accessToken = getTokens().access_token
//     const active = introspectTokenIsActive()
//     // console.log(`active: ${active}`);
    
//     // if(checkIsTokenExpired(accessToken)) {
//     //     updateToken()
//     // }

//     // if(!active) {
//     //     updateToken();
//     // }

//     return getTokens().access_token
// }