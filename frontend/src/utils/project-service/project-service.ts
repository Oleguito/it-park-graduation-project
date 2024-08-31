import { Settings } from "@/constants/settings";
import { ProjectCreateCommand, ProjectQuery, ProjectResponse, UserProjectCreateCommand, UserResponse } from "@/types/project/project";
import axios, { AxiosError, AxiosResponse } from "axios";
import { getAxiosInstance } from "../utilities/axiosInstance";
import { ProjectSearchQuery } from "@/types/project/projectsearchquery";
import { error } from "console";

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
            // console.log("createProjectOnBackend: ", response);
            return response.data;
        })
        .catch((error) => {
            console.log(`createProjectOnBackend: ${error}`);
        });
    return {} as ProjectQuery;
};

export const getProjectsFromBackend = async (query: ProjectSearchQuery): Promise<ProjectResponse[]> => {
    let instance = getAxiosInstance(
        Settings.backend.projectService.findProjectByQuery());
    return await instance.post("",
        query,
    ).then((response: AxiosResponse<ProjectResponse[]>) => {
        console.log("getProjectsFromBackend: ", response.data)
        return response.data
    });
};

export const findUsersForProject = async (
    projectId: number
): Promise<UserResponse[]> => {
    return await axios
        .get(Settings.backend.projectService.findUsersForProjectUrl(projectId))
        .then((response) => {
            // console.log("findUsersForProject: ", response);
            return response.data;
        })
        .catch((error) => {
            console.log(`findUsersForProject error: `, error);
            return "ERROR";
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
            // console.log("addParticipantToProject: SUCCESS");
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
            // console.log("removeParticipantFromProject: SUCCESS");
        })
        .catch((error) => {
            console.log(`removeParticipantFromProject error: `, error);
        });
};