import { Settings } from "@/constants/settings";
import axios from "axios";
import https from "https";
import { getDecodedToken } from "../token";
import { UserCreateQuery, UserQuery } from "@/types/UserQuery";
import jwt_decode from "jwt-decode";
import JwtParseData from "@/types/jwt";

const myhttpsAgent = new https.Agent({
    rejectUnauthorized: false,
});



export const createUserInBackend = () => {
    
    const token = localStorage.getItem("id_token");
    const tokenData: JwtParseData = jwt_decode(token);

    console.log("tokenData: ", tokenData);

    const userQuery: UserCreateQuery = {
        fullName: tokenData.name,
        email: tokenData.email,
        login: tokenData.preferred_username,
        role: "user",
    }
    
    const response = axios
        .post(Settings.backend.userService.getUserCreateUrl(), userQuery, {
            httpsAgent: myhttpsAgent,
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1VmsyMEV5RFJQVl9rbU5KM2pBaHZtUDdieDhMRUxmM0FTY09jRXVqV05ZIn0.eyJleHAiOjE3MjI1NDI5ODAsImlhdCI6MTcyMjUyNDk4MCwiYXV0aF90aW1lIjoxNzIyNTI0OTc5LCJqdGkiOiI4NDBlN2ZkYy0zZmE3LTRhMWQtYWZkMi1hNDVjOTJiM2I3ZTYiLCJpc3MiOiJodHRwczovL2xlbXVyLTcuY2xvdWQtaWFtLmNvbS9hdXRoL3JlYWxtcy9ncmFkLXByb2plY3QiLCJhdWQiOiJhdXRoLXNlcnZpY2UiLCJzdWIiOiI1NjhiMzBmYi1lMzhlLTQ4NjMtYjExOC03NjhhNWRjZTRhMzIiLCJ0eXAiOiJJRCIsImF6cCI6ImF1dGgtc2VydmljZSIsInNpZCI6ImVlZjZkZTdiLTU4YjQtNGIxOC04MDZjLWNmMjg1NmJkNmY3NiIsImF0X2hhc2giOiJFbFc4UHpCTHJKY0wxMFJBVXA1MXpBIiwiYWNyIjoiMSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiT2xlZ3VpdG8gU3dhZ2J1Y2tzIiwicHJlZmVycmVkX3VzZXJuYW1lIjoib2xlZ3VpdG8iLCJnaXZlbl9uYW1lIjoiT2xlZ3VpdG8iLCJmYW1pbHlfbmFtZSI6IlN3YWdidWNrcyIsImVtYWlsIjoidGVuem9yaWF0b3JAcmFtYmxlci5ydSJ9.KMCOadeXJx8d0A5rE4BujVe3mkPksNtQMUbR8OI02kAC9PlooFrDXJcd2Vez6JhG3yc9vwjxVZ717BkDFkixRTMEBLoYOgGfVQf3N-7EEiI4VLbg5wCQ384hrhHKAsDz5GdP9Y4PqqFVvWPUMwO0yompKc60eGMMGCdrxmgh-jI4p5nosZ36amhUFfDS_qxCXGc3SS5pxz-w4aHrPoYeSOELDV3LNn9rSco-EzCD9QdYTY9ehl59xU8TSm8DuyYhnAr1VIvz1by7_cazy6z-DMeBGFqXM7Pn1OJdEyUR9cfSgQVcVjrilmbftGDnzC9LpkOvoe2lE-Vdd844jlmqYw`,
            },
        })
        .then((response) => {
            console.log("createUserInBackend: ", response);
        })
        .catch((error) => {
            console.log(`createUserInBackend: ${error}`);
        });
    return response;
}

export const fetchUserInfo = () => {
    const myhttpsAgent = new https.Agent({
        rejectUnauthorized: false,
    });
    const response = axios.post(
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
    ).then((response) => {
       console.log("fetchUserInfo inside: ", response); 
    })
    .catch((error) => {
        console.log(`fetchUserInfo: ${error}`);
    });
    return response;
};