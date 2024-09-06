// Import the required libraries
import { Settings } from "@/constants/settings";
import { CreateMessageCommand, MessageResponse } from "@/types/chat/chat";
import { Dispatch, SetStateAction } from "react";
import SockJS from "sockjs-client";
import {Client} from "@stomp/stompjs";

// Create a WebSocket connection
const socket = new SockJS(Settings.websocket.connectionURL);
export const stompClient = new Client({
  webSocketFactory: () => socket,
  reconnectDelay: 5000,
});;

// Connect to the WebSocket and subscribe to a specific chat
export const subscribeToChat = (
  chatId: number,
  messages: MessageResponse[],
  setMessages: Dispatch<SetStateAction<MessageResponse[]>>,
) => {
  stompClient.onConnect = (frame: any) => {
    stompClient.subscribe(
      `${Settings.websocket.subscribeURL}/${chatId}`,
      (message) => {
        let msg: CreateMessageCommand = JSON.parse(
          message.body,
        ) as CreateMessageCommand;
        setMessages((prevMessages) => [...prevMessages, msg]);
        console.log("Новое сообщение получено: ", msg)
      },
    );
    console.log(`Connected to chat: ${chatId}`);
  };
  stompClient.activate()
};

// Send a message to the chat with the specified identifier
export function sendMessage(chatId: number, message: CreateMessageCommand) {
  stompClient.publish({
    destination: `${Settings.websocket.sendMessageURL}/${chatId}`,
    body: JSON.stringify(message),
  });
  console.log(`Сообщение отправлено, chatId: ${chatId}`)
}

// Function to display the received message
function showMessage(message: any) {
  // Implement the logic to display the message
  console.log(message);
}
