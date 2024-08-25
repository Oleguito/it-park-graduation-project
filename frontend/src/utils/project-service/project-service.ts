import { Settings } from "@/constants/settings";
import { ProjectCreateCommand, ProjectQuery, UserResponse } from "@/types/project/project";
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