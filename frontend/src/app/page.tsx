"use client";
import { Inter as FontSans } from "next/font/google";
import "./globals.css";

import { authorize } from "@/utils/authorizationLogic";
import { getTokens, LOCAL_STORAGE_TOKEN_KEY, signOut } from "@/utils/token";
import { useEffect } from "react";

const fontSans = FontSans({
    subsets: ["latin"],
    variable: "--font-sans",
});

export default function RootLayout() {
    useEffect(() => {

        const tokensLS = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);

        console.log("tokensLS: ", tokensLS);

        // const refreshToken = JSON.parse(tokensLS).refresh_token

        // const refreshObject = jwt_decode(refreshToken);

        // console.log(refreshObject);
        

        console.log("we are here")
        const tokens = getTokens();
        if (
            tokens === undefined ||
            tokens === null ||
            Object.keys(tokens).length === 0
        ) {
            authorize();
        } else {
            signOut();
            authorize();
        }
    }, []);

    return (
      <>
        <h1> © Collaborative Project Management Tool</h1>
      </>
    );
}
