"use client"


import DoubleList from "@/components/doubleList/doubleList";
import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar";
import {Button} from "@/components/ui/button";
import {SettingsIcon} from "lucide-react";
import {UserInfo} from '@/types/user-info'
import {getUserInfo} from "@/utils/userInfo";
import { useEffect, useState } from "react";
import jwt_decode from 'jwt-decode';
import JwtParseData from "../../types/jwt";


const MyPage = () => {

    const [userInfo, setUserInfo] = useState({} as UserInfo)

    useEffect(() => {
        const ui = getUserInfo();
        ui && setUserInfo(ui);
    }, [])

    

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
                <div className={"h-full flex items-start"}>
                    <Button size={"icon"} variant={"outline"}>
                        <SettingsIcon />
                    </Button>
                </div>
            </div>
            <button onClick={() => {
                const accessTokenFromLS = localStorage.getItem("id_token");
                console.log(`accessTokenFromLS: ${accessTokenFromLS}`);

                const accessToken = jwt_decode<JwtParseData>(
                    "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1VmsyMEV5RFJQVl9rbU5KM2pBaHZtUDdieDhMRUxmM0FTY09jRXVqV05ZIn0.eyJleHAiOjE3MjI0MzM0NjAsImlhdCI6MTcyMjQxNTQ2MCwiYXV0aF90aW1lIjoxNzIyNDE1NDU4LCJqdGkiOiJiZDAzNmRkMi1iNzc4LTRmZmQtYTM0Mi03Mzc4OTAwNmI3MDIiLCJpc3MiOiJodHRwczovL2xlbXVyLTcuY2xvdWQtaWFtLmNvbS9hdXRoL3JlYWxtcy9ncmFkLXByb2plY3QiLCJhdWQiOiJhdXRoLXNlcnZpY2UiLCJzdWIiOiI1NjhiMzBmYi1lMzhlLTQ4NjMtYjExOC03NjhhNWRjZTRhMzIiLCJ0eXAiOiJJRCIsImF6cCI6ImF1dGgtc2VydmljZSIsInNpZCI6ImY3MzEyNDBkLTJjNGYtNDI3MS1iMTI3LWI2YzM4NDc3ZjdkNiIsImF0X2hhc2giOiJzZXNSRTcxZ0lYTTlNT1JVOE15Z3FnIiwiYWNyIjoiMSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiT2xlZ3VpdG8gU3dhZ2J1Y2tzIiwicHJlZmVycmVkX3VzZXJuYW1lIjoib2xlZ3VpdG8iLCJnaXZlbl9uYW1lIjoiT2xlZ3VpdG8iLCJmYW1pbHlfbmFtZSI6IlN3YWdidWNrcyIsImVtYWlsIjoidGVuem9yaWF0b3JAcmFtYmxlci5ydSJ9.KoAa21L1VRIsaKytKyDOep0BtSaOXhqqzoleQ9sT-f_xuoM0uj6n6dh3fwPqjn_De7kBZFfm1RYq9J3bUG9dHRvGquNkEwt8epjPtmUf6fNDCodfuLEj280DkRXAqxgIDhmaJqKrWdVcpPfL3HmEc-qS5ua7TMFvUBfYTWgEZ_pwbP-aLJu0X0aAJvw-MbZP5vg--Ug34S8HVi4eslYhlU3V-G_gSiAk2AcjVd835z4X5URmpTQYXEqYAUXVuXA85d5ErLY7xfrgJjaXzN1PZ5sJ_9fkuZ1J_QvMhKj4d4twxx3NzFUmHaYKtvcXgJegs4Inf082vrsiPP1jmKVitg"
                );
                console.log(`accessToken parsed:`, accessToken);
                console.log("resource_access: ", accessToken['resource_access']);
            }}>sdhjfslkdjf</button>
        </>
    );
}

export default MyPage
