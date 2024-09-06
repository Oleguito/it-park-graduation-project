import { Settings } from "@/constants/settings";
import {
  ChatResponse,
  ChatSearchQuery,
  MessageResponse,
} from "@/types/chat/chat";
import { getAxiosInstance } from "@/utils/utilities/axiosInstance";
import { AxiosResponse } from "axios";

export const getChats = async (
  query: ChatSearchQuery,
): Promise<ChatResponse[]> => {
  const axios = getAxiosInstance(Settings.backend.chatService.getChatsUrl());
  return await axios
    .post("", query)
    .then((response: AxiosResponse<ChatResponse[]>) => response.data);
};

export const getMessages = async (chatId: number): Promise<MessageResponse[]> => {
  const axios = getAxiosInstance(Settings.backend.chatService.getMessagesUrl());
  return await axios
    .get("", { params: { chatId: chatId } })
    .then((response: AxiosResponse<MessageResponse[]>) => {
      return response.data;
    });
};
