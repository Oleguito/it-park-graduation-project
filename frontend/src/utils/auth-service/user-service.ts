"use client";

import { Settings } from "@/constants/settings";
import axios, { AxiosInstance } from "axios";
import https from "https";
import { TokenType, getDecodedToken } from "../token";
import { UserCreateQuery, UserQuery } from "@/types/UserQuery";
import jwt_decode from "jwt-decode";
import JwtParseData from "@/types/jwt";
import { getAxiosInstance } from "../utilities/axiosInstance";
import { dumbAxiosInstance } from "../utilities/axiosInstance";

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

export const fetchUserInfo = () => {
  const myhttpsAgent = new https.Agent({
    rejectUnauthorized: false,
  });
  const response = axios
    .post(
      Settings.backend.userService.getUserSearchUrl(),
      {
        languages: [
          {
            language: "en",
            level: "c3",
          },
        ],
      },
      {
        httpsAgent: myhttpsAgent,
        headers: {
          Authorization: `Bearer ${localStorage.getItem("id_token")}`,
          "Content-Type": "application/json",
        },
      }
    )
    .then((response) => {
      console.log("fetchUserInfo inside: ", response);
    })
    .catch((error) => {
      console.log(`fetchUserInfo: ${error}`);
    });
  return response;
};
