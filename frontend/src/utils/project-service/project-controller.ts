import { Settings } from "@/constants/settings";
import {
	InvitationMessage,
	ProjectCreateCommand,
	ProjectResponse,
	UserProjectCreateCommand,
	UserProjectDeleteCommand,
	UserResponse,
} from "@/types/project/project";
import { AxiosInstance } from "axios";
import { getAxiosInstance } from "../utilities/axiosInstance";

// Define the Axios instance
const axiosInstance: AxiosInstance = getAxiosInstance(
    `${Settings.backend.projectService.baseUrl}/projects`
);

// API Communication Functions

export const getAllTest = async (): Promise<ProjectResponse[]> => {
    try {
        const response = await axiosInstance.get<ProjectResponse[]>("/alltest");
        return response.data;
    } catch (error) {
        console.error("Error fetching all test projects", error);
        throw error;
    }
};

export const createProject = async (
    projectCreateCommand: ProjectCreateCommand
): Promise<ProjectResponse> => {
    try {
        const response = await axiosInstance.post<ProjectResponse>(
            "/add",
            projectCreateCommand
        );
        return response.data;
    } catch (error) {
        console.error("Error creating new project", error);
        throw error;
    }
};

export const getAllProjects = async (): Promise<ProjectResponse[]> => {
    try {
        const response = await axiosInstance.get<ProjectResponse[]>("/all");
        return response.data;
    } catch (error) {
        console.error("Error fetching all projects", error);
        throw error;
    }
};

export const getByEmail = async (email: string): Promise<ProjectResponse[]> => {
  try {
    const response = await axiosInstance.get<ProjectResponse[]>("/find-by", {
      params: { email: email },
    });
    return response.data;
  } catch (error) {
    console.error("Error fetching all projects", error);
    throw error;
  }
};

export const getUsersForProjectId = async (
    projectId: number
): Promise<UserResponse[]> => {
    try {
        const response = await axiosInstance.get<UserResponse[]>(
            `/all/${projectId}`
        );
        return response.data;
    } catch (error) {
        console.error(`Error fetching users for projectId ${projectId}`, error);
        throw error;
    }
};

export const sendNotification = async (
    notificationMessage: InvitationMessage
): Promise<void> => {
    try {
        await axiosInstance.post("/message", notificationMessage);
    } catch (error) {
        console.error("Error sending notification", error);
        throw error;
    }
};

export const addParticipant = async (
    userProjectCreateCommand: UserProjectCreateCommand
): Promise<void> => {
    try {
        await axiosInstance.post("/add-participant", userProjectCreateCommand);
    } catch (error) {
        console.error("Error adding participant", error);
        throw error;
    }
};

export const removeParticipant = async (
    userProjectDeleteCommand: UserProjectDeleteCommand
): Promise<void> => {
    try {
        await axiosInstance.post(
            "/remove-participant",
            userProjectDeleteCommand
        );
    } catch (error) {
        console.error("Error removing participant", error);
        throw error;
    }
};

export const removeProject = async (
  id: number
): Promise<void> => {
  try {
    await axiosInstance.delete(`/remove-project/${id}`);
  } catch (error) {
    console.error("Error removing participant", error);
    throw error;
  }
};