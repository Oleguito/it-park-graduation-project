import { Settings } from "@/constants/settings";
import axios, { AxiosInstance } from "axios";
import https from "https";
import { TokenType, getDecodedToken } from "../token";
import { UserCreateQuery, UserQuery } from "@/types/UserQuery";
import jwt_decode from "jwt-decode";
import JwtParseData from "@/types/jwt";
import axiosInstance, { getAxiosInstance } from "../utilities/axiosInstance";

const myhttpsAgent = new https.Agent({
  rejectUnauthorized: false,
});

export const createUserInBackend = () => {
  const token = (JSON.parse(localStorage.getItem(`auth-tokens-${process.env.NODE_ENV}`)) as TokenType).access_token;
  const tokenData: JwtParseData = jwt_decode(token);

  console.log("tokenData: ", tokenData);

  const userQuery: UserCreateQuery = {
    fullName: tokenData.name,
    email: tokenData.email,
    login: tokenData.preferred_username,
    role: "user",
  };

//   var tokens: TokenType = JSON.parse(localStorage.getItem("token"));
//   var axiosInstance: AxiosInstance = getAxiosInstance(Settings.backend.userService.getUserCreateUrl());
//   console.log("Axios instance:", axiosInstance.interceptors)
//   console.log("Axios request:", axiosInstance.)
  const response = axiosInstance.post("", userQuery,
    // {
    // httpsAgent: myhttpsAgent,
    // headers: {
    //   "Content-Type": "application/json",
    // //   Authorization: `Bearer ${token}`,
    // }
    // ,
//   }
).then((response) => {
    console.log("createUserInBackend: ", response);
  })
  .catch((error) => {
    console.log(`createUserInBackend: ${error}`);
  });;

//   const response = axios
//     .post(Settings.backend.userService.getUserCreateUrl(), userQuery, {
//       httpsAgent: myhttpsAgent,
//       headers: {
//         "Content-Type": "application/json",
//         Authorization: `Bearer ${tokens.access_token}`,
//       },
//     })
//     .then((response) => {
//       console.log("createUserInBackend: ", response);
//     })
//     .catch((error) => {
//       console.log(`createUserInBackend: ${error}`);
//     });
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
