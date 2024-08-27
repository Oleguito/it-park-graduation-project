import { Settings } from "@/constants/settings";
import axios from "axios";

export const uploadFile = async (file: File) => {
    const formData = new FormData();
    formData.append("file", file);

    try {
        const response = await axios.post(
            Settings.backend.documentService.fileUploadUrl(),
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            }
        );
        console.log(response.data);
    } catch (error) {
        console.error("There was an error uploading the file!", error);
    }
};
