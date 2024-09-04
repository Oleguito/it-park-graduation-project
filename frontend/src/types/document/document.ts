export type FileAttachment = {
    fileName: string;
    link: string;
    projectId: number;
    userId: number;
    createdAt: string; // ISO дата в виде строки
};

export type FileAttachmentArray = FileAttachment[];
