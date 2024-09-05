import { uploadFile } from "@/utils/document-service/document-service";
import React, { useState } from "react";
import { Button } from "../ui/button";

export type Props = {
  projectId: number;
  userId: number;
  callback: () => void;
};

const FileUploadComponent: React.FC<Props> = ({ projectId, userId, callback }) => {
  const [fileToUpload, setFileToUpload] = useState<File | null>(null);

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setFileToUpload(event.target.files[0]);
      console.log(fileToUpload);
    }
  };

  const handleUpload = async () => {
    if (fileToUpload) {
      await uploadFile(fileToUpload, projectId, userId);
      callback && callback();
    }
  };

  return (
    <div>
      <input type="file" onChange={handleFileChange} />
      <Button onClick={handleUpload} disabled={!fileToUpload}>
        Добавить вложение
      </Button>
    </div>
  );
};

export default FileUploadComponent;
