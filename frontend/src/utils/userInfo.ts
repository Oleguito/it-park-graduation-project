import jwt_decode from 'jwt-decode'

export function getUserInfo() {
    let token = window.localStorage.getItem("id_token")
    console.log("наш токен")
    console.log(token)

    if (token ) {
        //@ts-ignore
        console.log(jwt_decode(token))
        return jwt_decode(token)
    }

    return null
}
