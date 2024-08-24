export type Project = {
    id: number;
    name: string;
    description: string;
    status: string
    due: string;
}

type DateInfo = {
    createdAt: Date;
    deletedAt: Date;
}

// Этот тип отправляется на бэкэнд чтобы создать новый проект
export type ProjectCreateCommand = {
    name: string;
    description: string;
    startDate: Date;
    endDate: Date;
    status: string;
    ownerId: number;
    dateInfo: DateInfo;
    creatorEmail: string;
};


// Этот тип ожидается вернуться с сервера
export type ProjectQuery = {
    id: number;
    name: string;    
    description: string;    
    startDate: Date;
    endDate: Date;    
    status: string;    
    ownerId: number;    
    dateInfo: DateInfo;
}