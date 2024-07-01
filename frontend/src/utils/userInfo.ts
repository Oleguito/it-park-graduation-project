import { UserInfo } from '@/types/user-info'
import jwt_decode from 'jwt-decode'

export function getUserInfo() : UserInfo | null {
    let token = window.localStorage.getItem("id_token")

    if (token ) {
        return jwt_decode(token)
    }

    return null
}
