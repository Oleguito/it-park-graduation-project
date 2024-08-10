'use client'

import React from "react";

import css from "./styles.module.css";
import ProjectItem from "../project-item/ProjectItem";
import { Props } from "../project-item/ProjectItem";
import { Button } from "../ui/button";

const InvitationItem = () => {
    const props: Props = {
        id: 2934804023,
        description: `Описание проекта \n Это страница с ПРОЕКТАМИ!!`,
        title: "Вот такое название мзфк",
        status: "NEW"
    };


    const acceptInvitationHandler = () => {
        console.log("InvitationItem: acceptInvitationHandler: accept invitation");
    };

    const rejectInvitationHandler = () => {
        console.log("InvitationItem: rejectInvitationHandler: reject invitation");
    };

    return (
        <>
            <div>
                <ProjectItem props={props} />
                <div>
                    <Button className="m-1" onClick={acceptInvitationHandler}>Принять приглашение</Button>
                    <Button className="m-1" onClick={rejectInvitationHandler}>Отклонить приглашение</Button>
                </div>
            </div>
        </>
    );
};

export default InvitationItem;
