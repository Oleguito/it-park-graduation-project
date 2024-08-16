"use client";

import { Settings } from "@/constants/settings";
import JwtParseData from "@/types/jwt";
import { UserCreateQuery, UserQuery } from "@/types/UserQuery";
import { AxiosInstance } from "axios";
import https from "https";
import jwt_decode from "jwt-decode";
import { TokenType } from "../token";
import {
	dumbAxiosInstance,
	getAxiosInstance,
} from "../utilities/axiosInstance";

const myhttpsAgent = new https.Agent({
    rejectUnauthorized: false,
});

export const createUserInBackend = () => {
    const token = (
        JSON.parse(
            localStorage.getItem(`auth-tokens-${process.env.NODE_ENV}`)
        ) as TokenType
    ).accessToken;
    const tokenData: JwtParseData = jwt_decode(token);

    console.log("tokenData: ", tokenData);
    console.log(tokenData.exp * 1000 - new Date().getTime());

    const userQuery: UserCreateQuery = {
        fullName: tokenData.name,
        email: tokenData.email,
        login: tokenData.preferred_username,
        role: "user",
    };

    var axiosInstance: AxiosInstance = getAxiosInstance(
        Settings.backend.userService.getUserCreateUrl()
    );

    const response = axiosInstance
        .post("", userQuery, {
            httpsAgent: myhttpsAgent,
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((response) => {
            console.log("createUserInBackend: ", response);
        })
        .catch((error) => {
            console.log(`createUserInBackend: ${error}`);
        });

    return response;
};

export const createUserInBackend2 = () => {
    const token = (
        JSON.parse(
            localStorage.getItem(`auth-tokens-${process.env.NODE_ENV}`)
        ) as TokenType
    ).accessToken;
    const tokenData: JwtParseData = jwt_decode(token);

    console.log("tokenData: ", tokenData);
    console.log(tokenData.exp * 1000 - new Date().getTime());

    const userQuery: UserCreateQuery = {
        fullName: tokenData.name,
        email: tokenData.email,
        login: tokenData.preferred_username,
        role: "user",
    };

    var axiosInstance: AxiosInstance = dumbAxiosInstance;

    const response = axiosInstance
        .post("", userQuery, {
            httpsAgent: myhttpsAgent,
            headers: {
                "Content-Type": "application/json",
            },
        })
        .then((response) => {
            console.log("createUserInBackend: ", response);
        })
        .catch((error) => {
            console.log(`createUserInBackend: ${error}`);
        });

    return response;
};

export const fetchUserInfo = (): UserQuery[] => {

	 const myhttpsAgent = new https.Agent({
         rejectUnauthorized: false,
     });

    const token = (
        JSON.parse(
            localStorage.getItem(`auth-tokens-${process.env.NODE_ENV}`)
        ) as TokenType
    ).accessToken;
    const tokenData: JwtParseData = jwt_decode(token);

    console.log("tokenData: ", tokenData);
    console.log(tokenData.exp * 1000 - new Date().getTime());

    const userQuery: UserCreateQuery = {
        email: tokenData.email,
    };

    let result;
    getAxiosInstance(Settings.backend.userService.getUserSearchUrl())
        .post("", {
            email: tokenData?.email,
        }, {
			httpsAgent: myhttpsAgent,
			headers: {
				"Content-Type": "application/json",
			},
		})
        .then((response) => {
            console.log("fetchUserInfo inside: ", response);
            result = response.data;
        })
        .catch((error) => {
            console.log(`fetchUserInfo: ${error}`, error);
        });
    return result;
};
