export type SetInvitationStatusCommand = {

    emailTo?: string;
    invUUID?: string;
    status?: string;
    emailFrom?: string;

}


export type CreateInvitationCommand = {
    emailTo?: string
    emailFrom?: string
    projectId?: number
}

export type InvitationSearchQuery = {
    emailTo?: string;
    emailFrom?: string;
    status?: string;
    projectId?: number;
    invUUID?: string;
};

export type InvitationSearchResponse = {
    emailTo?: string;
    emailFrom?: string;
    status?: string;
    projectId?: number;
    invUUID?: string;
};
