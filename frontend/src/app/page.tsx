"use client";
import { Inter as FontSans } from "next/font/google";
import "./globals.css";

import { JwtRefreshTokenType } from "@/types/jwt";
import { authorize } from "@/utils/authorizationLogic";
import { getDecodedRefreshToken, getTokens, signOut } from "@/utils/token";
import { useEffect } from "react";

const fontSans = FontSans({
  subsets: ["latin"],
  variable: "--font-sans",
});

export default function RootLayout() {
  useEffect(() => {
    const refreshObject: JwtRefreshTokenType = getDecodedRefreshToken();

    // console.log(refreshObject);

    // console.log(refreshObject.exp * 1000 - Date.now());

    const expired = refreshObject.exp * 1000 - Date.now() < 0;

    if (expired) {
      signOut();
      authorize();
      return;
    }

    const tokens = getTokens();
    if (
      tokens === undefined ||
      tokens === null ||
      Object.keys(tokens).length === 0
    ) {
      authorize();
    }
  }, []);

  return (
    <>
      <h1> © Collaborative Project Management Tool</h1>
    </>
  );
}
