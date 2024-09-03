'use client'

import { InvitationSearchResponse } from "@/types/invitation/invitation";
import ProjectItem from "../project-item/ProjectItem";
import { Button } from "../ui/button";

const InvitationItem = (data: InvitationSearchResponse) => {
 
    const acceptInvitationHandler = () => {
        console.log("InvitationItem: acceptInvitationHandler: accept invitation");
    };

    const rejectInvitationHandler = () => {
        console.log("InvitationItem: rejectInvitationHandler: reject invitation");
    };

    return (
        <>
            <div>
                <ProjectItem props={data} />
                <div>
                    <Button className="m-1" onClick={acceptInvitationHandler}>Принять приглашение</Button>
                    <Button className="m-1" onClick={rejectInvitationHandler}>Отклонить приглашение</Button>
                </div>
            </div>
        </>
    );
};

export default InvitationItem;
