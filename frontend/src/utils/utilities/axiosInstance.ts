"use client";

import { Settings } from "@/constants/settings";
import axios, { AxiosInstance, AxiosResponse } from "axios";
import {
	IAuthTokens,
	TokenRefreshRequest,
	applyAuthTokenInterceptor,
	getBrowserLocalStorage,
} from "axios-jwt";
import { Token } from "axios-jwt/dist/src/Token";
import { authorize } from "../authorizationLogic";
import { TokenType } from "../token";

export type AuthConfig = {
    grant_type: string;
    refresh_token: string;
    client_secret: string;
    client_id: string;
};

const refreshTokens: TokenRefreshRequest = async (
    refreshToken: Token
): Promise<IAuthTokens | Token> => {
    var tokens: TokenType = JSON.parse(
        window.localStorage.getItem(`auth-tokens-${process.env.NODE_ENV}`)
    );

    var refreshConfig: AuthConfig = {
        grant_type: "refresh_token",
        refresh_token: tokens.refreshToken,
        client_id: Settings.keycloak.clientId,
        client_secret: Settings.keycloak.clientSecret,
    };

    var srchParams: URLSearchParams = new URLSearchParams(refreshConfig);

    let result;

    try {
        result = await axios
            .post(`${Settings.keycloak.tokenUrl}`, srchParams, {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
            })
            .then(
                (
                    response: AxiosResponse<{
                        access_token: string;
                        refresh_token: string;
                    }>
                ) => {
                    console.log("Токены обновлены");
                    return {
                        accessToken: response.data.access_token,
                        refreshToken: response.data.refresh_token,
                    };
                }
            );
    } catch (error) {
        console.log("Токен не обновлен");
        authorize();
    }

    return result;
};

export function getAxiosInstance(baseURL: string): AxiosInstance {
    var axiosInstance: AxiosInstance = axios.create({
        baseURL,
    });

    applyAuthTokenInterceptor(axiosInstance, {
        getStorage: getBrowserLocalStorage,
        header: "Authorization",
        headerPrefix: "Bearer ",
        tokenExpireFudge: 1,
        requestRefresh: refreshTokens,
    });

    return axiosInstance;
}
