import { Settings } from "@/constants/settings";
import axios, { AxiosResponse } from "axios";
import { getAxiosInstance } from "../utilities/axiosInstance";
import { FileAttachmentArray } from "@/types/document/document";

export const uploadFile = async (file: File) => {
  const formData = new FormData();
  formData.append("file", file);

  try {
    const response = await axios.post(
      Settings.backend.documentService.fileUploadUrl(),
      formData,
      {
        params: {
          userId: 1,
          projectId: 1,
        },
        headers: {
          "Content-Type": "multipart/form-data",
        },
      },
    );
    console.log(response.data);
  } catch (error) {
    console.error("There was an error uploading the file!", error);
  }
};

export const downloadListFiles = async (
  url: string,
  query: {
    projectId?: number;
    userId?: number;
  },
): Promise<FileAttachmentArray> => {
  return await getAxiosInstance(url)
    .post("", query)
    .then((response: AxiosResponse<FileAttachmentArray>) => response.data);
};
