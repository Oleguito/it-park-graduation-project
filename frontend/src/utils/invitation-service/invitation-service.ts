import { Settings } from "@/constants/settings";
import {
    CreateInvitationCommand,
    InvitationSearchQuery,
    SetInvitationStatusCommand,
} from "@/types/invitation/invitation";
import { getAxiosInstance } from "../utilities/axiosInstance";

/* СОЗДАНИЕ ПРИГЛАШЕНИЯ */

export const createInvitation = async (
    createInvitationCommand: CreateInvitationCommand
) => {
    const axios = getAxiosInstance(
        Settings.backend.invitationService.createInvitationUrl()
    );

    try {
        const response = await axios.post("", createInvitationCommand);
        console.log("Invitation created successfully!", response.data);
    } catch (error) {
        console.error("Error creating invitation:", error);
    }
};

// // Пример данных для создания приглашения
// const createInvitationCommand = {
//     emailTo: "exampleTo@mail.com",
//     emailFrom: "exampleFrom@mail.com",
//     status: "PENDING",
//     projectId: 123,
//     invUUID: "some-unique-uuid",
// };

/* ПОИСК ПРИГЛАШЕНИЙ */

export const getInvitations = async (query: InvitationSearchQuery) => {
    const axios = getAxiosInstance(
        Settings.backend.invitationService.findInvitationUrl()
    );
    try {
        const response = await axios.post("", query);
        console.log("Invitations retrieved successfully!", response.data);
        return response.data;
    } catch (error) {
        console.error("Error retrieving invitations:", error);
    }
};

// // Пример данных для поиска приглашений
// const invitationSearchQuery = {
//     emailTo: "exampleTo@mail.com",
//     emailFrom: "exampleFrom@mail.com",
//     status: "PENDING",
//     projectId: 123,
//     invUUID: "some-unique-uuid",
// };

/* ПРИНЯТИЕ/ОТКЛОНЕНИЕ ПРИГЛАШЕНИЯ */

export const acceptInvitation = async (
    acceptInvitationCommand: SetInvitationStatusCommand
) => {
    const axios = getAxiosInstance(
        Settings.backend.invitationService.acceptInvitationUrl()
    );
    try {
        const response = await axios.patch("", acceptInvitationCommand);
        console.log("Invitation status updated successfully!", response.data);
    } catch (error) {
        console.error("Error updating invitation status:", error);
    }
};

// // Пример данных для изменения статуса приглашения
// const acceptInvitationCommand = {
//     invUUID: "some-unique-uuid",
//     status: "ACCEPTED", // или "DECLINED"
// };

/* УДАЛЕНИЕ ПРИГЛАШЕНИЯ */

export const deleteInvitation = async (deleteInvitationCommand) => {
    const axios = getAxiosInstance(
        Settings.backend.invitationService.declineInvitationUrl()
    );
    try {
        const response = await axios.delete("", {
            data: deleteInvitationCommand,
        });
        console.log("Invitation deleted successfully!", response.data);
    } catch (error) {
        console.error("Error deleting invitation:", error);
    }
};

// // Пример данных для удаления приглашения
// const deleteInvitationCommand = {
//     invUUID: "some-unique-uuid",
//     emailFrom: "exampleFrom@mail.com",
// };
