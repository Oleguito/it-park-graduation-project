import { Settings } from "@/constants/settings";
import { ProjectCreateCommand, ProjectQuery, UserProjectCreateCommand, UserResponse } from "@/types/project/project";
import axios from "axios";
import { getAxiosInstance } from "../utilities/axiosInstance";

export const createProjectOnBackend = async function (
    projectCreateCommand: ProjectCreateCommand
): Promise<ProjectQuery> {
    getAxiosInstance(Settings.backend.projectService.createProjectUrl())
        .post("", projectCreateCommand, {
            headers: {
                ContentType: "application/json",
            },
        })
        .then((response) => {
            console.log("createProjectOnBackend: ", response);
            return response.data;
        })
        .catch((error) => {
            console.log(`createProjectOnBackend: ${error}`);
        });
    return {} as ProjectQuery;
};


export const findUsersForProject = async (
    projectId: number
): Promise<UserResponse[]> => {
    return await axios
        .get(Settings.backend.projectService.findUsersForProjectUrl(projectId))
        .then((response) => {
            console.log("findUsersForProject: ", response);
            return response.data;
        })
        .catch((error) => {
            return "ERROR";
            console.log(`findUsersForProject error: `, error);
        });
};

export const addParticipantToProject = async (
    userProjecCreateCommand: UserProjectCreateCommand
): Promise<void> => {

    return await axios
        .post(
            Settings.backend.projectService.addParticipantToProjectUrl(), 
            userProjecCreateCommand,
            {

            }
        )
        .then((response) => {
            console.log("addParticipantToProject: SUCCESS");
        })
        .catch((error) => {
            console.log(`addParticipantToProject error: `, error);
        });
}

export const removeParticipantFromProject = async (
    userProjecDeleteCommand: UserProjectCreateCommand
): Promise<void> => {
    return await axios
        .post(
            Settings.backend.projectService.removeParticipantToProjectUrl(),
            userProjecDeleteCommand,
            {}
        )
        .then((response) => {
            console.log("removeParticipantFromProject: SUCCESS");
        })
        .catch((error) => {
            console.log(`removeParticipantFromProject error: `, error);
        });
};