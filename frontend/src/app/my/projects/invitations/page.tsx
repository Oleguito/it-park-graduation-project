"use client";

import InvitationItem from "@/components/invitation-item/InvitationItem";
import {
  InvitationSearchQuery,
  InvitationSearchResponse,
} from "@/types/invitation/invitation";
import { getInvitations } from "@/utils/invitation-service/invitation-service";
import { getDecodedToken, JwtPayloadType } from "@/utils/token";
import { useEffect, useState } from "react";

const ProjectInvitationsPage = () => {
  const [invitations, setInvitations] = useState(
    [] as InvitationSearchResponse[],
  );
  const [update, setUpdate] = useState(false);

  useEffect(() => {
    let decoded_token: JwtPayloadType = getDecodedToken();
    let query: InvitationSearchQuery = {
      emailTo: decoded_token.email,
      status: "SENT",
    };

    getInvitations(query)
      .then((response: InvitationSearchResponse[]) => setInvitations(response))
      .catch((error) =>
        console.log("Error while fetching my invitations: ", error),
      );
  }, [update]);

  return (
    <>
      <div className="w-full">
        {invitations ? (
          <>
            {invitations.length == 0 ? (
              <div>У вас нет приглашений</div>
            ) : (
              invitations.map((elem) => (
                <InvitationItem
                  key={elem.invUUID}
                  data={elem}
                  callback={() => {
                    setUpdate(!update);
                  }}
                />
              ))
            )}
          </>
        ) : (
          <div>Загрузка...</div>
        )}
      </div>
    </>
  );
};

export default ProjectInvitationsPage;
