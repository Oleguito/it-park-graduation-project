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
    <div className="flex items-center mb-4">
      <input type="file" onChange={handleFileChange} className="w-[50%]" />
      <div className="w-[50%]">
        <Button
          onClick={handleUpload}
          disabled={!fileToUpload}
          className="w-1/2"
        >
          Добавить вложение
        </Button>
      </div>
    </div>
  );
};

export default FileUploadComponent;
