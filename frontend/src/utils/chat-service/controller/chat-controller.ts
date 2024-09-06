import { Settings } from "@/constants/settings";
import { ChatResponse, ChatSearchQuery } from "@/types/chat/chat";
import { getAxiosInstance } from "@/utils/utilities/axiosInstance";

export const getChats = async (
  query: ChatSearchQuery,
): Promise<ChatResponse[]> => {
  const axios = getAxiosInstance(Settings.backend.chatService.getChatsUrl());
  return await axios.post("", query).then(response => response.data);
};

export const getMessages = async (chatId: number): Promise<ChatResponse> => {
  const axios = getAxiosInstance(Settings.backend.chatService.getMessagesUrl());
  return await axios
    .get("", { params: { chatId: chatId } })
    .then((chatResponse) => {
      return chatResponse.data;
    });
};
