// Types for your DTOs based on the Java classes

export type ProjectResponse = {
    id: number;
    name: string;
    description: string;
    startDate?: string;
    endDate: string;
    status: string; // assuming this to be a string, adapt as needed
    ownerId: number;
    dateInfo: DateInfo;
};

export type ProjectCreateCommand = {
    id: number;
    name: string;
    description: string;
    startDate?: string;
    endDate: string;
    status: string;
    ownerId: number;
    dateInfo: DateInfo;
    creatorEmail: string;
};

export type UserProjectCreateCommand = {
    email: string;
    projectId: number;
};

export type UserProjectDeleteCommand = {
    email: string;
    projectId: number;
};

export type InvitationMessageType = "invite" | "exclude";

export type InvitationMessage = {
    projectCreatorEmail: string;
    invitedUserEmail: string;
    invitationMessage: string;
    type: InvitationMessageType;
    key?: string; // Assuming UUID will be a string, adjust if needed
    projectId: number;
    projectTitle: string;
    projectStatus: string;
    projectDescription: string;
};

export type UserResponse = {
    email: string;
};

export type Project = {
    id: number;
    name: string;
    description: string;
    status: string;
    due: string;
};

export type DateInfo = {
    createdAt: Date;
    deletedAt: Date;
};

export type ProjectQuery = {
    id: number;
    name: string;
    description: string;
    startDate: Date;
    endDate: Date;
    status: string;
    ownerId: number;
    dateInfo: DateInfo;
};

export type UserProjectResponse = {
    id?: string;
    email?: string;
    project_id?: number;
};
