import { uploadFile } from "@/utils/document-service/document-service";
import React, { useState } from "react";
import { Button } from "../ui/button";

const FileUploadComponent: React.FC = () => {
    const [fileToUpload, setFileToUpload] = useState<File | null>(null);

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            setFileToUpload(event.target.files[0]);
            console.log(fileToUpload);
        }
    };

    const handleUpload = () => {
        if (fileToUpload) {
            uploadFile(fileToUpload);
        }
    };

    return (
        <div>
            <input type="file" onChange={handleFileChange} />
            <Button onClick={handleUpload} disabled={!fileToUpload}>
                Загрузить документ
            </Button>
        </div>
    );
};

export default FileUploadComponent;
