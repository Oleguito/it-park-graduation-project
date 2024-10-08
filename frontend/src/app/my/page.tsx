"use client"


import DoubleList from "@/components/doubleList/doubleList";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { UserInfo } from '@/types/user-info';
import { getUserInfo } from "@/utils/userInfo";
import { useEffect, useState } from "react";

const MyPage = () => {

    const [userInfo, setUserInfo] = useState({} as UserInfo)

    useEffect(() => {
      const ui = getUserInfo();
      ui && setUserInfo(ui);

    //   const tokensLS = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);

    //   const parsed = JSON.parse(tokensLS);

    //   console.log("parsed: ", parsed);

    //   const refreshObject: JwtRefreshTokenType = getDecodedRefreshToken();

    //   console.log(refreshObject);

    //   console.log(refreshObject.exp * 1000 - Date.now());


    }, [])

    

    return (
        <>
            <div className={"flex w-full justify-between items-center h-full"}>
                <Avatar
                    className={
                        "w-[128px] h-[128px] flex justify-center items-center mr-8 ml-8"
                    }
                >
                    <AvatarImage
                        src="https://github.com/shadcn.png"
                        alt="@shadcn"
                    />
                    <AvatarFallback>CN</AvatarFallback>
                </Avatar>
                <div className={"flex justify-around w-full flex-col"}>
                    <DoubleList
                        listData={[
                            {
                                label: "Фамилия имя отчество",
                                content: `${userInfo.name}`,
                            },
                            {
                                label: "Почта",
                                content: `${userInfo.email}`,
                            },
                            {
                                label: "Логин",
                                content: `${userInfo.preferred_username}`,
                            },
                        ]}
                    />
                    <DoubleList
                        listData={[
                            {
                                label: "Номер ВУ",
                                content: "12321321312",
                            },
                            {
                                label: "Телеграм",
                                content: "https://t.me/ilublukontour228",
                            },
                            {
                                label: "Телефон",
                                content: "+7 (960) 558-78-77",
                            },
                        ]}
                    />
                </div>
                {/* <div className={"h-full flex items-start"}>
                    <Button size={"icon"} variant={"outline"}>
                        <SettingsIcon />
                    </Button>
                </div> */}
            </div>
        </>
    );
}

export default MyPage
