"use client";

import { InvitationSearchResponse } from "@/types/invitation/invitation";
import { acceptInvitation } from "@/utils/invitation-service/invitation-service";
import ProjectItem from "../project-item/ProjectItem";
import { Button } from "../ui/button";
import { Toaster } from "../ui/toaster";
import { useToast } from "../ui/use-toast";

const InvitationItem = ({
  data,
  callback,
}: {
  data: InvitationSearchResponse;
  callback?: () => void;
}) => {
  const { toast } = useToast();

  const acceptInvitationHandler = async () => {
    const acceptResponse = await acceptInvitation({
      status: "ACCEPTED",
      invUUID: data.invUUID,
    })
      .then((response) => {
        toast({
          title: "Приглашение принято",
          description: "Ваше приглашение было принято",
          duration: 3000,
          variant: "default",
        });
        callback && callback();
      })
      .catch((error) => {
        console.log("Error while accepting invitation: ", error);
        toast({
          title: "Ошибка!",
          description: "При попытке принять приглашение произошла ошибка",
          duration: 3000,
          variant: "destructive",
        });
      });

    console.log("InvitationItem: acceptInvitationHandler: accept invitation");
  };

  const rejectInvitationHandler = async () => {
    const response = await acceptInvitation({
      status: "DECLINED",
      invUUID: data.invUUID,
    })
      .then((response) => {
        toast({
          title: "Приглашение отклонено",
          description: "Вы успешно отклонили приглашение",
          duration: 3000,
          variant: "default",
        });
        callback && callback();
      })
      .catch((error) => {
        console.log("Error while accepting invitation: ", error);
        toast({
          title: "Ошибка!",
          description: "При попытке отклонить приглашение произошла ошибка",
          duration: 3000,
          variant: "destructive",
        });
      });

    console.log("InvitationItem: rejectInvitationHandler: reject invitation");
  };

  return (
    <>
      <div>
        <ProjectItem props={data} />
        <div>
          <Button className="m-1" onClick={acceptInvitationHandler}>
            Принять приглашение
          </Button>
          <Button className="m-1" onClick={rejectInvitationHandler}>
            Отклонить приглашение
          </Button>
        </div>
      </div>
      <Toaster />
    </>
  );
};

export default InvitationItem;
