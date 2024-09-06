export type ChatMessageAlignment = "left" | "right";

export type ChatsListItem = {
    id?: number;
    title?: string;
    lastMessage?: string;
    lastMessageDate?: string;
    lastMessageAuthor?: string;
}

export type ChatMessageType = {
    message?: string;
    author?: string;
    date?: string;
    alignment?: ChatMessageAlignment;
}

export type ChatSearchQuery = {
    projectId?: number;
    projectIds?: number[];
    chatId?: number;
    chatIds?: number[];
}

export type ChatResponse = {
    chatId?: number;
    projectId?: number;
    projectName?: string;
}

export type MessageResponse = {
    chatId?:number;
    userId?:number;
    username?:string;
    sentAt?:Date;
    message?:string;
    uuid?:string;
}

export type CreateMessageCommand = {
    chatId?:number;
    userId?:number;
    username?:string;
    message?:string;
    uuid?:string;
}