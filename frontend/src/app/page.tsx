"use client";
import { Inter as FontSans } from "next/font/google";
import "./globals.css";

import { authorize } from "@/utils/authorizationLogic";
import { getTokens, signOut } from "@/utils/token";
import { useEffect } from "react";

const fontSans = FontSans({
    subsets: ["latin"],
    variable: "--font-sans",
});

export default function RootLayout() {
    useEffect(() => {

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
