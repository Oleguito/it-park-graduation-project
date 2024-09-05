"use client";

import { InvitationSearchResponse } from "@/types/invitation/invitation";
import { ProjectResponse } from "@/types/project/project";
import { removeProject } from "@/utils/project-service/project-controller";
import { getProjectsFromBackend } from "@/utils/project-service/project-service";
import { useEffect, useState } from "react";
import { Button } from "../ui/button";
import { useToast } from "../ui/use-toast";
import styles from "./styles.module.css";

const ProjectItem = ({
  props,
  callback,
}: {
  props: InvitationSearchResponse & ProjectResponse;
  callback?: () => void;
}) => {
  const [project, setProject] = useState({} as ProjectResponse);
  const { toast } = useToast();

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
          <div>ProjectItem</div>
          <div className="relative group inline-block">
            <Button onClick={deleteProjectHandler}>❌</Button>
            <span className="absolute left-1/2 transform -translate-x-1/2 bottom-full mb-2 hidden group-hover:block w-max px-2 py-1 text-sm text-white bg-black rounded-lg">
              Удалить проект
            </span>
          </div>
        </div>
        <div>
          <div className="font-bold w-1/5 inline-block">Title:</div>
          <div className="italic inline-block">{project.name}</div>
        </div>
        <div>
          <div className="font-bold w-1/5 inline-block">Status:</div>
          <div className="inline-block">{project.status}</div>
        </div>
        <div className={styles["project-item-upper-part"]}>
          <div className="font-bold w-1/4">Project id: {project.id}</div>
          <textarea
            value={project.description}
            className={`${styles["description-box"]} italic`}
            readOnly={true}
          />
        </div>
      </div>
    </>
  );
};

export default ProjectItem;
