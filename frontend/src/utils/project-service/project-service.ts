import { ProjectCreateCommand, ProjectQuery } from "@/types/project/project";
import { getAxiosInstance } from "../utilities/axiosInstance";
import { Settings } from "@/constants/settings";

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
