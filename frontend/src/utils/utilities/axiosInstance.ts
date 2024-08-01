import axios, { AxiosInstance, AxiosResponse } from "axios";
// import routes, { BASE_URL } from "./routes";
import {
  IAuthTokens,
  TokenRefreshRequest,
  applyAuthTokenInterceptor,
  getBrowserLocalStorage,
} from "axios-jwt";
import { Token } from "axios-jwt/dist/src/Token";
import { Settings } from "@/constants/settings";
import { TokenType } from "../token";

const axiosInstance = axios.create({
  baseURL: Settings.backend.userService.getUserCreateUrl(),
});

export type AuthConfig = {
  grant_type: string,
  refresh_token: string,
  client_secret: string,
  client_id: string,
};

const refreshTokens: TokenRefreshRequest = async (
  refreshToken: Token
): Promise<IAuthTokens | Token> => {
  var tokens: TokenType = JSON.parse(
    window.localStorage.getItem(`auth-tokens-${process.env.NODE_ENV}`)
  );

  console.log("Токены перед обновлением")

  var refreshConfig: AuthConfig = {
    grant_type: "refresh_token",
    refresh_token: tokens.refresh_token,
    client_id: Settings.keycloak.clientId,
    client_secret: Settings.keycloak.clientSecret,
  };

  console.log("Пытаемся обновить токены")

  return await axios
    .post(`${Settings.keycloak.tokenUrl}`, refreshConfig, {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
    .then((response: AxiosResponse<IAuthTokens>) => {
        console.log('Токены обновлены')
        return response.data
    });

};

// export function getAxiosInstance(baseURL: string): AxiosInstance {

//     var axiosInstance: AxiosInstance = axios.create({
//         baseURL,
//     })

//     applyAuthTokenInterceptor(axiosInstance, {
//         getStorage: getBrowserLocalStorage,
//         header: "Authorization",
//         headerPrefix: "Bearer ",
//         tokenExpireFudge: 1,
//         requestRefresh: refreshTokens,
//       });

//       return axiosInstance;

// }

applyAuthTokenInterceptor(axiosInstance, {
  getStorage: getBrowserLocalStorage,
  header: "Authorization",
  headerPrefix: "Bearer ",
  tokenExpireFudge: 1,
  requestRefresh: refreshTokens,
})

axiosInstance.interceptors.request.use(
    (config) => {
      console.log('Request config:', config);
      return config;
    },
    (error) => {
      console.error('Request error:', error);
      return Promise.reject(error);
    }
  );

export default axiosInstance;
