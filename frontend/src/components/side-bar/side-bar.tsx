'use client'

import {sidebarVars} from "@/components/side-bar/sidebarVars";
import {usePathname} from "next/navigation";
import {Button} from "@/components/ui/button";
import Link from "next/link";
import {getTokens, getUserRole} from "@/utils/token";

const isRouteActive = (pathname: string, url: string) => {
    return pathname.includes(url)
}
export const SideBar = () => {
    const pathName = usePathname();

    const tokens = getTokens();

    const isTokensExists = Object.keys(tokens).length > 0;
    console.log(getUserRole())
    return (
        isTokensExists && (
            <div className='w-[15rem] flex flex-col gap-5 mr-36'>
                {
                    sidebarVars[getUserRole() as string][pathName]
                        .map((value, index) =>
                            <Link href={value.link} key={index}>
                                <Button className='w-full'
                                        variant={isRouteActive(pathName, value.link) ? 'ghost' : 'outline'} key={index}>
                                    {value.text}
                                </Button>
                            </Link>
                        )
                }
            </div>
        )
    );

}
