'use client'

import { InvitationSearchResponse } from "@/types/invitation/invitation";
import { acceptInvitation } from "@/utils/invitation-service/invitation-service";
import ProjectItem from "../project-item/ProjectItem";
import { Button } from "../ui/button";

const InvitationItem = (data: InvitationSearchResponse) => {
 
    
    const acceptInvitationHandler = async () => {

        const acceptResponse = await acceptInvitation({
            status: "ACCEPTED",
            invUUID: data.invUUID
        })
        .catch(error => console.log("Error while accepting invitation: ", error))

        
         
        console.log("InvitationItem: acceptInvitationHandler: accept invitation");
    };

    const rejectInvitationHandler = async () => {

        const response = await acceptInvitation({
            status: "DECLINED",
            invUUID: data.invUUID,
        }).catch((error) =>
            console.log("Error while accepting invitation: ", error)
        );

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
