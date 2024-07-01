'use client'

import {sidebarVars} from "@/components/side-bar/sidebarVars";
import {usePathname} from "next/navigation";
import {Button} from "@/components/ui/button";
import Link from "next/link";
import {getTokens, getUserRole, isTokenInLS} from "@/utils/token";
import { useEffect, useState } from "react";

const isRouteActive = (pathname: string, url: string) => {
    // return pathname.includes(url)
    console.log("isRouteActive: ", url.includes(pathname));
    return url.includes(pathname);
}
export const SideBar = () => {

    const [isTokensExists, setIsTokenExists] = useState(false)

    const pathName = usePathname();
    console.log("pathName:", pathName)

    useEffect(() => {
         setIsTokenExists(Object.keys(getTokens()).length > 0);
    }, []);

    return (
        isTokensExists && (
            <div className="w-[15rem] flex flex-col gap-5 mr-36">
                {sidebarVars[getUserRole() as string][pathName].map(
                    (value, index) => (
                        <Link href={value.link} key={index}>
                            <Button
                                className="w-full"
                                variant={
                                    isRouteActive(pathName, value.link)
                                        ? "ghost"
                                        : "outline"
                                }
                                key={index}
                            >
                                {value.text}
                            </Button>
                        </Link>
                    )
                )}
            </div>
        )
    );

}
