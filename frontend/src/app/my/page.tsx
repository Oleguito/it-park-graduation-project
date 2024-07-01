"use client"
import DoubleList from "@/components/doubleList/doubleList";
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar";
import {Button} from "@/components/ui/button";
import {SettingsIcon} from "lucide-react";
import {UserInfo} from '@/types/user-info'
import {getUserInfo} from "@/utils/userInfo";
import { useState } from "react";


const MyPage = () => {

    const [userInfo, setUserInfo] = useState({} as UserInfo)

    return (
        <>
            <div>
                <p>- Общая информация о пользователе</p>
                <p>- Список текущих проектов пользователя</p>
                <p>- Доступ к функциям управления профилем</p>
            </div>
            <div className={"flex w-full justify-between items-center h-full"}>
                <Avatar
                    className={
                        "w-[128px] h-[128px] flex justify-center items-center"
                    }
                >
                    <AvatarImage
                        src="https://github.com/shadcn.png"
                        alt="@shadcn"
                    />
                    <AvatarFallback>CN</AvatarFallback>
                </Avatar>
                <div className={"flex justify-around w-full"}>
                    <DoubleList
                        listData={[
                            {
                                label: "Фамилия имя отчество",
                                content: `${getUserInfo()?.name}`,
                            },
                            {
                                label: "Почта",
                                content: `${getUserInfo()?.email}`,
                            },
                            {
                                label: "Логин",
                                content: `${getUserInfo()?.preferred_username}`,
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
                                label: "Телеграмм",
                                content: "https://t.me/ilublukontour228",
                            },
                            {
                                label: "Телефон",
                                content: "+7 (960) 558-78-77",
                            },
                        ]}
                    />
                </div>
                <div className={"h-full flex items-start"}>
                    <Button size={"icon"} variant={"outline"}>
                        <SettingsIcon />
                    </Button>
                </div>
            </div>
        </>
    );
}

export default MyPage
