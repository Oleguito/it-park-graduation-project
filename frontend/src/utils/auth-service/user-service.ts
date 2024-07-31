import { Settings } from "@/constants/settings";
import axios from "axios";
import https from "https";
import { getDecodedToken } from "../token";

const myhttpsAgent = new https.Agent({
    rejectUnauthorized: false,
});

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