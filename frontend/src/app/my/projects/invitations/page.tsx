'use client'

import InvitationItem from "@/components/invitation-item/InvitationItem";
import { InvitationSearchQuery, InvitationSearchResponse } from "@/types/invitation/invitation";
import { getInvitations } from "@/utils/invitation-service/invitation-service";
import { getDecodedToken, JwtPayloadType } from "@/utils/token";
import React, { useEffect, useState } from "react";


const ProjectInvitationsPage = () => {

    const [invitations, setInvitations] = useState([] as InvitationSearchResponse[])

    useEffect(() => {
        let decoded_token: JwtPayloadType = getDecodedToken();
        let query: InvitationSearchQuery = {
            emailTo: decoded_token.email,
            status: "SENT"
        }

        getInvitations(query)
            .then((response: InvitationSearchResponse[]) => setInvitations(response))
            .catch(error => console.log("Error while fetching my invitations: ", error))
    }, [])

    return (
        <> {invitations ?
            <>
                {invitations.map(elem => <InvitationItem key={elem.invUUID} {...elem} />)}

                {/* <div>ProjectInvitationsPage</div>
                <InvitationItem />
                <InvitationItem />
                <InvitationItem /> */}
            </>
            :
            <div>Загрузка...</div>}
        </>
    );
};

export default ProjectInvitationsPage;
