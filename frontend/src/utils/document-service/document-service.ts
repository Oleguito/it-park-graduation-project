import { Settings } from "@/constants/settings";
import axios, { AxiosResponse } from "axios";
import { getAxiosInstance } from "../utilities/axiosInstance";
import { FileAttachmentArray } from "@/types/document/document";

export const uploadFile = async (
  file: File,
  projectId: number,
  userId: number,
) => {
  const formData = new FormData();
  formData.append("file", file);

  try {
    const response = await axios.post(
      Settings.backend.documentService.fileUploadUrl(),
      formData,
      {
        params: {
          userId,
          projectId,
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

export const deleteFile = async (
  userId: number,
  projectId: number,
  fileName: string,
) => {
  return await getAxiosInstance(
    Settings.backend.documentService.fileDeleteUrl(),
  )
    .delete("", {
      params: {
        userId,
        projectId,
        fileName,
      },
    })
    .then((response: AxiosResponse) => response.data);
};
