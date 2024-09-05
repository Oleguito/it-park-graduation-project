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