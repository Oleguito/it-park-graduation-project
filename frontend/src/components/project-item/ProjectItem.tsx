"use client";

import { InvitationSearchResponse } from "@/types/invitation/invitation";
import { ProjectResponse } from "@/types/project/project";
import { removeProject } from "@/utils/project-service/project-controller";
import { getProjectsFromBackend } from "@/utils/project-service/project-service";
import { getDecodedToken } from "@/utils/token";
import { useEffect, useState } from "react";
import { Button } from "../ui/button";
import { useToast } from "../ui/use-toast";

const ProjectItem = ({
  props,
  callback,
}: {
  props: InvitationSearchResponse & ProjectResponse;
  callback?: () => void;
}) => {
  const [project, setProject] = useState({} as ProjectResponse);
  const { toast } = useToast();
  const [userEmail, setUserEmail] = useState("");

  useEffect(() => {
    getProjectsFromBackend({
      projectId: props.projectId,
    })
      .then((response: ProjectResponse[]) => {
        response.length > 0 ? setProject(response[0]) : null;
      })
      .catch((error) =>
        console.log("Error occuring while fetching Project info: ", error),
      );
  }, [project.id]);

  useEffect (() => {
    setUserEmail(getDecodedToken().email);
  }, [props.emailFrom]);

  const deleteProjectHandler = async () => {
    await removeProject(props.projectId)
      .then((response) => {
        toast({
          title: "Проект удален успешно!",
          description: "Вы успешно удалили проект",
        });
        callback && callback();
      })
      .catch((error) => {
        console.log("Error while deleting project: ", error);
        toast({
          title: "Ошибка!",
          description: "При попытке удалить проект произошла ошибка",
          duration: 3000,
          variant: "destructive",
        });
      });
  };

  return (
    <>
      <div>
        <div className="flex flex-row justify-between">
          <div></div>
          <div className="relative group inline-block">
            {userEmail === props.emailFrom && (
              <Button onClick={deleteProjectHandler}>❌</Button>
            )}
            <span className="absolute left-1/2 transform -translate-x-1/2 bottom-full mb-2 hidden group-hover:block w-max px-2 py-1 text-sm text-white bg-black rounded-lg">
              Удалить проект
            </span>
          </div>
        </div>
        <div className="mt-4 mb-4 flex">
          <div className="font-bold w-1/4 inline-block">Название проекта:</div>
          <div className="italic inline-block">{project.name}</div>
        </div>
        <div className="mt-4 mb-4 flex">
          <div className="font-bold w-1/4 inline-block">Статус проекта:</div>
          <div className="inline-block">{project.status}</div>
        </div>
        <div className={"flex mb-4"}>
          <div className="font-bold w-1/4">ID проекта:</div>
          <div>{project.id}</div>
        </div>
        <div className="flex mb-4">
          <div className="w-1/4">Описание проекта:</div>
          <textarea
            value={project.description}
            className={`italic w-3/4`}
            readOnly={true}
          />
        </div>
      </div>
    </>
  );
};

export default ProjectItem;
